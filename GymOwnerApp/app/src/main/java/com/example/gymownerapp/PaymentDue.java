package com.example.gymownerapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PaymentDue extends AppCompatActivity {

    private CardView card1, card2, card3, card4, card5, card6;
    private Button paidButton1, paidButton2, paidButton3, paidButton4, paidButton5, paidButton6;
    private Button leavingButton1, leavingButton2, leavingButton3, leavingButton4, leavingButton5, leavingButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_due);

        // Find views by their respective IDs
        card1 = findViewById(R.id.details_layout_card1);
        card2 = findViewById(R.id.details_layout_card2);
        card3 = findViewById(R.id.details_layout_card3);
        card4 = findViewById(R.id.details_layout_card4);
        card5 = findViewById(R.id.details_layout_card5);
        card6 = findViewById(R.id.details_layout_card6);

        paidButton1 = findViewById(R.id.paid_button_card1);
        paidButton2 = findViewById(R.id.paid_button_card2);
        paidButton3 = findViewById(R.id.paid_button_card3);
        paidButton4 = findViewById(R.id.paid_button_card4);
        paidButton5 = findViewById(R.id.paid_button_card5);
        paidButton6 = findViewById(R.id.paid_button_card6);

        leavingButton1 = findViewById(R.id.leaving_button_card1);
        leavingButton2 = findViewById(R.id.leaving_button_card2);
        leavingButton3 = findViewById(R.id.leaving_button_card3);
        leavingButton4 = findViewById(R.id.leaving_button_card4);
        leavingButton5 = findViewById(R.id.leaving_button_card5);
        leavingButton6 = findViewById(R.id.leaving_button_card6);

        // Set onClick listeners for "Paid the Due" buttons
        paidButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set card1 background color to green
                card1.setCardBackgroundColor(Color.GREEN);
            }
        });

        paidButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set card2 background color to green
                card2.setCardBackgroundColor(Color.GREEN);
            }
        });

        paidButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set card3 background color to green
                card3.setCardBackgroundColor(Color.GREEN);
            }
        });

        paidButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set card4 background color to green
                card4.setCardBackgroundColor(Color.GREEN);
            }
        });

        paidButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set card5 background color to green
                card5.setCardBackgroundColor(Color.GREEN);
            }
        });

        paidButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set card6 background color to green
                card6.setCardBackgroundColor(Color.GREEN);
            }
        });

        // Set onClick listeners for "Leaving" buttons
        leavingButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove card1 from its parent LinearLayout
                ((LinearLayout) card1.getParent()).removeView(card1);
            }
        });

        leavingButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove card2 from its parent LinearLayout
                ((LinearLayout) card2.getParent()).removeView(card2);
            }
        });

        leavingButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove card3 from its parent LinearLayout
                ((LinearLayout) card3.getParent()).removeView(card3);
            }
        });

        leavingButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove card4 from its parent LinearLayout
                ((LinearLayout) card4.getParent()).removeView(card4);
            }
        });

        leavingButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove card5 from its parent LinearLayout
                ((LinearLayout) card5.getParent()).removeView(card5);
            }
        });

        leavingButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove card6 from its parent LinearLayout
                ((LinearLayout) card6.getParent()).removeView(card6);
            }
        });
    }
}
