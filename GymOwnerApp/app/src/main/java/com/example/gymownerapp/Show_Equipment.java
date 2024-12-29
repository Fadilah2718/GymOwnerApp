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

public class Show_Equipment extends AppCompatActivity {
    Button addplan;
    EquipAdapter myAdapter;
    ArrayList<AddEquip> list;
    RecyclerView recyclerView;
    @Override
    protected void onStart() {
        super.onStart();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new EquipAdapter(this, list);
        recyclerView.setAdapter(myAdapter);
        list.clear();
        DatabaseReference mref=FirebaseDatabase.getInstance().getReference().child("Equipments");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child: snapshot.getChildren()){
                    AddEquip data = child.getValue(AddEquip.class);
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
        setContentView(R.layout.activity_show_equipment);
        recyclerView=findViewById(R.id.recyclerEquip);
        addplan=findViewById(R.id.buttonAddEquips);
        addplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Show_Equipment.this, "Add new Equipment", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder=new AlertDialog.Builder(Show_Equipment.this);
                View dialogView = getLayoutInflater().inflate(R.layout.equipment_adder,null);
                EditText pname=dialogView.findViewById(R.id.equipaddername);
                EditText pamt = dialogView.findViewById(R.id.equipadderduration);
                EditText pduration=dialogView.findViewById(R.id.equipadderprice);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialogView.findViewById(R.id.addequipbtn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Equipments");
                        String plname=pname.getText().toString();
                        String plamt =pamt.getText().toString();
                        String pldur = pduration.getText().toString();
                        //if else check for empty
                        AddEquip plandata = new AddEquip(plname,pldur,plamt);
                        mref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String id = mref.push().getKey();
                                mref.child(id).setValue(plandata);
                                Toast.makeText(Show_Equipment.this, "Equipment Added Successfully", Toast.LENGTH_SHORT).show();
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