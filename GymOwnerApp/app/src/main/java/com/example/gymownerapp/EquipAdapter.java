package com.example.gymownerapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EquipAdapter extends RecyclerView.Adapter<EquipAdapter.ViewHolder> {
    Context context;
    ArrayList<AddEquip> list;
    //using Addplan as recycler
    public EquipAdapter(Context context,ArrayList<AddEquip> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.equip_card,parent,false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AddEquip data = list.get(position);
        holder.name.setText(String.valueOf(data.getEquipname()));
        holder.price.setText(String.valueOf(data.getEquipprice()));
        holder.duration.setText(String.valueOf(data.getEquipduration()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, duration;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.cardname1);
            price=itemView.findViewById(R.id.cardprice1);
            duration=itemView.findViewById(R.id.cardduration1);
        }
    }

}