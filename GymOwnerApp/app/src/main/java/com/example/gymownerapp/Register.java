package com.example.gymownerapp;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Register extends AppCompatActivity {

    ImageView userimg;
    Button save;

    TextView clkplan;
    TextView planname,planprice,plandur;
    EditText ownername,gymname,username,email,phonenum,useraddr,userdate;
    String imageURL;
    Uri uri;
    Spinner spinnerData;
    DatabaseReference databaseReference;
    List<String> dataList;
    ArrayAdapter<String> spinnerAdapter;
    String PLAN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userimg = findViewById(R.id.uploadImage);
        save = findViewById(R.id.saveButton);
        ownername = findViewById(R.id.ownname);
        gymname = findViewById(R.id.gymname);
        username = findViewById(R.id.uploadTopic);
        email = findViewById(R.id.usermail);
        phonenum = findViewById(R.id.userphn);
        useraddr = findViewById(R.id.useraddr);
        clkplan=findViewById(R.id.showplan);
        spinnerData =findViewById(R.id.myspinner);
        planname=findViewById(R.id.regpname);
        plandur=findViewById(R.id.regpduration);
        planprice=findViewById(R.id.regpprice);
        userdate=findViewById(R.id.userregisdate);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            userimg.setImageURI(uri);
                        } else {
                            Toast.makeText(Register.this, "No Image Selected", Toast.LENGTH_SHORT);
                        }
                    }
                }
        );
        userimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        userdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get current date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                // Create DatePickerDialog and set initial date to current date
                DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int monthOfYear, int dayOfMonth) {
                                // Set the selected date in the EditText field (userdate)
                                userdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + selectedYear);
                            }
                        }, year, month, day);
                // Show the DatePickerDialog
                datePickerDialog.show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        dataList = new ArrayList<>();
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerData.setAdapter(spinnerAdapter);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("GymPlan");

        // Retrieve data from Firebase Database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AddPlan data=snapshot.getValue(AddPlan.class);
                    dataList.add(data.getPlanname());
                }
                spinnerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(Register.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
        spinnerData.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = parent.getItemAtPosition(position).toString();
                DatabaseReference mref=FirebaseDatabase.getInstance().getReference().child("GymPlan");

                mref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot mychilddata:snapshot.getChildren()){
                            AddPlan data=mychilddata.getValue(AddPlan.class);
                            if(data.getPlanname().equals(selectedValue)){
                                planname.setText(data.getPlanname());
                                plandur.setText(data.getPlanduration());
                                planprice.setText(data.getPlanprice());
                                PLAN = selectedValue; // Assign the selected plan name to the PLAN variable
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Register.this, "Select Gym Plan", Toast.LENGTH_SHORT).show();
            }
        });

    }
        public void saveData(){
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
                    .child(uri.getLastPathSegment());
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            builder.setCancelable(false);
            builder.setView(R.layout.progress_layout);
            AlertDialog dialog = builder.create();
            dialog.show();

            storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete());
                    Uri urlImage = uriTask.getResult();
                    imageURL = urlImage.toString();
                    uploadData();
                    dialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                }
            });
        }
        public void uploadData(){

            String uname= username.getText().toString();
            String oname= ownername.getText().toString();
            String gymName= gymname.getText().toString();
            String uem= email.getText().toString();
            String uphno= phonenum.getText().toString();
            String uaddr= useraddr.getText().toString();
            String uplan= PLAN.toString();
            String udate=userdate.getText().toString();

            DataClass dataClass = new DataClass(imageURL,oname,gymName,uname,uem,uphno,uaddr,uplan,udate);
            String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
            Log.d(TAG, "onBindViewHolder: 33333333 "+ currentuser);
            FirebaseDatabase.getInstance().getReference("Customers").child(currentuser).child(uname)
                    .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Register.this, "Saved", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
}