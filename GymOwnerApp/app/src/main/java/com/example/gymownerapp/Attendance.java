package com.example.gymownerapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Attendance extends AppCompatActivity {
    RecyclerView recycleAtt;
    ArrayList<Attendance_recycle> list;
    AttendanceAdapter attadapter;
    DatabaseReference dbref;
    Button saveBTN;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onStart() {
        super.onStart();
        recycleAtt.setHasFixedSize(true);
        recycleAtt.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list = new ArrayList<>();
        attadapter= new AttendanceAdapter(this.getApplicationContext(),list);
        recycleAtt.setAdapter(attadapter);
        attadapter.setOnItemClickListener(position -> changeStatus(position));
        /*attadapter.setOnItemClickListener(new AttendanceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String status = getSta
            }
        });*/
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbref = FirebaseDatabase.getInstance().getReference("Customers").child(currentuser);
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren() ){
                    Attendance_recycle dataClass = itemSnapshot.getValue(Attendance_recycle.class);
                    Log.d(TAG, "onDataChange:#######3 "+dataClass.getUserName());
                    list.add(dataClass);
                }
                attadapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void changeStatus(int position){
        Attendance_recycle attendance_recycle=list.get(position);
        String st = attendance_recycle.getStatus();
        if(st.equals("P")){ st="A";}
        else st="P";
        list.get(position).setStatus(st);
        attadapter.notifyItemChanged(position);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        saveBTN=findViewById(R.id.attsavebtn);
        recycleAtt=findViewById(R.id.attendancerecycle);

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUsersWithStatusP();
            }
        });

    }
    /*private void saveUsersWithStatusP() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("PUsers");

        for (Attendance_recycle item : list) {
            if ("P".equals(item.getStatus())) {
                String currentDateAndTime = getCurrentDateAndTime(); // Implement this method to get the current date and time
                DatabaseReference childRef = databaseRef.push(); // Generate a unique key for each record
                childRef.setValue(item);
                childRef.child("timestamp").setValue(currentDateAndTime); // Optionally, you can store the timestamp as well
            }
        }
    }*/
    private void saveUsersWithStatusP() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("PUsers");

        String currentDate = getCurrentDate(); // Implement this method to get the current date

        DatabaseReference dateRef = databaseRef.child(currentDate);

        dateRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (Attendance_recycle item : list) {
                        if ("P".equals(item.getStatus())) {
                            DatabaseReference childRef = dateRef.child(snapshot.getKey());
                            childRef.setValue(item);
                        }
                    }
                } else {
                    for (Attendance_recycle item : list) {
                        if ("P".equals(item.getStatus())) {
                            DatabaseReference childRef = dateRef.push(); // Generate a unique key for each record under the current date
                            childRef.setValue(item);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date currentDate = new Date();
        return sdf.format(currentDate);
    }

}
