package com.example.gymownerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    ImageView registerBTN,profileBTN,attendanceBTN,planBTN,usergraphBTN,paymentBTN;
    Button logoutBTN,equipmentBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        registerBTN=findViewById(R.id.homeregisterbtn);
        profileBTN=findViewById(R.id.homeprofilebtn);
        attendanceBTN=findViewById(R.id.homeattendancerbtn);
        planBTN=findViewById(R.id.homegymplanbtn);
        usergraphBTN=findViewById(R.id.homeusergrapgbtn);
        paymentBTN=findViewById(R.id.homepaymentbtn);
        logoutBTN=findViewById(R.id.homelogoutbtn);
        equipmentBTN=findViewById(R.id.homeequipbtn);
        auth=FirebaseAuth.getInstance();

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });

        planBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(getApplicationContext(),Show_plans.class);
                startActivity(move);
            }
        });

        // for logout
        user=auth.getCurrentUser();
        if(user==null){
            Intent intent=new Intent(getApplicationContext(),ALoginPage.class);
            startActivity(intent);
            finish();
        }
        logoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(),ALoginPage.class);
                startActivity(intent);
                finish();
            }
        });

        profileBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m= new Intent(getApplicationContext(), ViewProfiles.class);
                startActivity(m);
            }
        });

        attendanceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent att = new Intent(getApplicationContext(),Attendance.class);
                startActivity(att);
            }
        });

        usergraphBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent graph = new Intent(getApplicationContext(),BarGraph.class);
                startActivity(graph);
            }
        });

        paymentBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent payuser = new Intent(getApplicationContext(), PaymentDue.class);
                startActivity(payuser);
            }
        });
        equipmentBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent payuser = new Intent(getApplicationContext(),Show_Equipment.class);
                startActivity(payuser);
            }
        });
    }
}