package com.example.gymownerapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {


    Context context;
    ArrayList<AddPlan> list;
    //using Addplan as recycler

    public PlanAdapter(Context context,ArrayList<AddPlan> list){
        this.context=context;
        this.list=list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.gym_card,parent,false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AddPlan data = list.get(position);
        holder.name.setText(String.valueOf(data.getPlanname()));
        holder.price.setText(String.valueOf(data.getPlanprice()));
        holder.duration.setText(String.valueOf(data.getPlanduration()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

       public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, duration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.cardname);
            price=itemView.findViewById(R.id.cardprice);
            duration=itemView.findViewById(R.id.cardduration);

        }
    }

}

