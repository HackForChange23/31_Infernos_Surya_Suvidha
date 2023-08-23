package com.example.ss31;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import java.util.ArrayList;

public class Crop_Prediction extends AppCompatActivity {

    CardView submitbtn;
    ImageView backbtn;
    AutoCompleteTextView orgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_prediction);

        orgs = findViewById(R.id.edittext_orgs);
        ArrayList<String> list = new ArrayList<>();
        list.add("Rabi");
        list.add("Kharif");
        list.add("Zaid");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.layout_dropdown,list);
        orgs.setAdapter(arrayAdapter);


        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager m = getSupportFragmentManager();
//                FragmentTransaction t = m.beginTransaction();
//                Fragment solar = new fasal_management();
//                t.replace(R.id.fragment, solar);
//                t.commit();
            }
        });

        submitbtn = findViewById(R.id.submitbtn);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout);

        CardView tomato_fertilizer_calc = dialog.findViewById(R.id.tomato_fertilizer_calc);
        CardView rice_fertilizer_calc = dialog.findViewById(R.id.rice_fertilizer_calc);

        tomato_fertilizer_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Fertilizer_Calculator.class);
                startActivity(i);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations= R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}