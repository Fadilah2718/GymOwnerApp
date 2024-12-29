package com.example.gymownerapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder>{
    Context context;
    ArrayList<Attendance_recycle> list;
    OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public AttendanceAdapter(Context context, ArrayList<Attendance_recycle> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_attendance,parent,false);
        return  new ViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Attendance_recycle data = list.get(position);
//
        Glide.with(context).load(data.getDataImage()).into(holder.userimg);
        holder.usern.setText(String.valueOf(data.getUserName()));
        Log.d(TAG, "onBindViewHolder: 33333333 "+ data.getUserName());
        holder.userp.setText(String.valueOf(data.getUserPlan()));
        holder.userpl.setText(String.valueOf(data.getUserphone()));
        holder.status.setText(String.valueOf(data.getStatus()));

        Log.d(TAG, "onBindViewHolder: 33333333 "+data.getStatus());
        holder.cardViewAtt.setCardBackgroundColor(getColor(position));
    }
    private int getColor(int position){
        Attendance_recycle attendance_recycle=list.get(position);
        String st = attendance_recycle.getStatus();
        if (st != null) {
            if (st.equals("P"))
                return Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(context, R.color.present)));
            else if (st.equals("A"))
                return Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(context, R.color.absent)));
        }
        return Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(context, R.color.white)));
        /*if(st.equals("P"))
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.present)));
        else if (st.equals("A"))
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.absent)));

        return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.white)));*/
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView usern,userp,userpl,status;
        ImageView userimg;
        CardView cardViewAtt;
        String st;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usern=itemView.findViewById(R.id.recattUser);
            userp=itemView.findViewById(R.id.recattgymplan);
            userpl=itemView.findViewById(R.id.recattphno);
            userimg=itemView.findViewById(R.id.recattImage);
            status=itemView.findViewById(R.id.recattendance);
            Log.d(TAG, "if status is coming "+ status.getText().toString());
            //status=stat.getText().toString();
            cardViewAtt=itemView.findViewById(R.id.attendancecard);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}

