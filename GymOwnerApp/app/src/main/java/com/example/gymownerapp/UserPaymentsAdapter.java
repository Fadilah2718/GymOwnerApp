package com.example.gymownerapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserPaymentsAdapter extends RecyclerView.Adapter<UserPaymentsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Profile_recycle> list;

    private AttendanceAdapter.OnItemClickListener listener;
    public UserPaymentsAdapter(Context context, ArrayList<Profile_recycle> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
