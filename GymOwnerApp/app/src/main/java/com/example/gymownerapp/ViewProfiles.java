package com.example.gymownerapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewProfiles extends AppCompatActivity {

    RecyclerView myrecyle;
    ArrayList<Profile_recycle> list;
    ProfileAdapter adapter;
    DatabaseReference databaseReference;


    @Override
    protected void onStart() {
        super.onStart();
        myrecyle.setHasFixedSize(true);
        myrecyle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list = new ArrayList<>();
        adapter=new ProfileAdapter(this.getApplicationContext(),list);
        myrecyle.setAdapter(adapter);

        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customers").child(currentuser);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren() ){
                    Profile_recycle dataClass = itemSnapshot.getValue(Profile_recycle.class);
                    Log.d(TAG, "onDataChange:#######3 "+dataClass.getUserName());
                    list.add(dataClass);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profiles);

        myrecyle=findViewById(R.id.profilerecycle);
    }
}