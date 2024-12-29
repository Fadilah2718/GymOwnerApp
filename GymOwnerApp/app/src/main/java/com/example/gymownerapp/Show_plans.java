package com.example.gymownerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Show_plans extends AppCompatActivity {
    Button addplan;
    PlanAdapter myAdapter;
    ArrayList<AddPlan> list;
    RecyclerView recyclerView;

    @Override
    protected void onStart() {
        super.onStart();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new PlanAdapter(this, list);
        recyclerView.setAdapter(myAdapter);
        list.clear();
        DatabaseReference mref=FirebaseDatabase.getInstance().getReference().child("GymPlan");

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child: snapshot.getChildren()){

                    AddPlan data = child.getValue(AddPlan.class);
                    list.add(data);

                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_plans);


        recyclerView=findViewById(R.id.recyclerViewPlans);

        addplan=findViewById(R.id.buttonAddPlans);
        addplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Show_plans.this, "Add new Plan", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder=new AlertDialog.Builder(Show_plans.this);
                View dialogView = getLayoutInflater().inflate(R.layout.activity_gym_plan_adder,null);

                EditText pname=dialogView.findViewById(R.id.gymplanaddername);
                EditText pamt = dialogView.findViewById(R.id.gymplanadderprice);
                EditText pduration=dialogView.findViewById(R.id.gymplanadderduration);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();

                dialogView.findViewById(R.id.addplanbtn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("GymPlan");
                        String plname=pname.getText().toString();
                        String plamt =pamt.getText().toString();
                        String pldur = pduration.getText().toString();
                        //if else check for empty

                        AddPlan plandata = new AddPlan(plname,plamt,pldur);
                        mref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String id = mref.push().getKey();
                                mref.child(id).setValue(plandata);
                                Toast.makeText(Show_plans.this, "Plan Added Successfully", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    }
                });


                dialog.show();

            }
        });

    }
}