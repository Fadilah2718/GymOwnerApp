package com.example.gymownerapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder>{

    Context context;
    ArrayList<Profile_recycle> list;


    public ProfileAdapter(Context context, ArrayList<Profile_recycle> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);

        return  new ViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Profile_recycle data = list.get(position);

//
        Glide.with(context).load(data.getDataImage()).into(holder.userimg);
        holder.usern.setText(String.valueOf(data.getUserName()));
        Log.d(TAG, "onBindViewHolder: 33333333 "+ data.getUserName());
        holder.userp.setText(String.valueOf(data.getUserPlan()));
        holder.userpl.setText(String.valueOf(data.getUserphone()));


        holder.buttondel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("Customers").child(currentuser);
                String delname=data.getUserName();

                myref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot child : snapshot.getChildren()){

                                Profile_recycle mdata = child.getValue(Profile_recycle.class);
                                if(mdata.getUserName().equals(delname)){
                                  myref.child(delname).removeValue();
                                }
                        }}


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView usern,userp,userpl;
        ImageView userimg;
        Button buttonedit,buttondel;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usern=itemView.findViewById(R.id.recUser);
            userp=itemView.findViewById(R.id.recgymplan);
            userpl=itemView.findViewById(R.id.recphno);
            userimg=itemView.findViewById(R.id.recImage);
            buttondel=itemView.findViewById(R.id.btnDelete);
            buttonedit=itemView.findViewById(R.id.btnEdit);



        }
    }

}

