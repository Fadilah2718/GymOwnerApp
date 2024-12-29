package com.example.gymownerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarGraph extends AppCompatActivity {

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);

        barChart = findViewById(R.id.barchart);

        // Fetch data from Firebase Realtime Database
        FirebaseDatabase.getInstance().getReference().child("PUsers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Map to store the total number of users for each date
                Map<String, Integer> dateUserMap = new HashMap<>();

                for (DataSnapshot dateSnapshot : dataSnapshot.getChildren()) {
                    String date = dateSnapshot.getKey();
                    int userCount = (int) dateSnapshot.getChildrenCount();
                    dateUserMap.put(date, userCount);
                }

                // Populate BarChart
                populateBarChart(dateUserMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BarGraph.this, "Error retrieving data from Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateBarChart(Map<String, Integer> dateUserMap) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        // Create entries for each date and the corresponding total number of users
        int index = 0;
        for (Map.Entry<String, Integer> entry : dateUserMap.entrySet()) {
            String date = entry.getKey();
            int userCount = entry.getValue();
            barEntries.add(new BarEntry(index++, userCount));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Visitors");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(2000);
    }
}
