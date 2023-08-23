package com.example.ss31;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fertilizer_Calculator extends AppCompatActivity {

    private TextView textViewAreaValue;
    private int landArea = 0;
    LinearLayout fertilizer_result;
    CardView submitbtn, decrease, increase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizer_calculator);

        textViewAreaValue = findViewById(R.id.textViewAreaValue);

        CardView buttonDecrease = findViewById(R.id.buttonDecrease);
        CardView buttonIncrease = findViewById(R.id.buttonIncrease);

        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (landArea > 0) {
                    landArea--;
                    updateAreaValue();
                }
            }
        });

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                landArea++;
                updateAreaValue();
            }
        });

        fertilizer_result = findViewById(R.id.fertilizer_result);
        submitbtn = findViewById(R.id.submitbtn);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fertilizer_result.setVisibility(View.VISIBLE);
            }
        });
    }

    private void updateAreaValue() {
        textViewAreaValue.setText(String.valueOf(landArea));
    }
}