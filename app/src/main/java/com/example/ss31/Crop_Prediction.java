package com.example.ss31;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Crop_Prediction extends AppCompatActivity {

    TextInputLayout temperature, humidity, soilpH, rainfall, nitrogen, phosphorus, potassium;

    TextView textView;
    RadioGroup radioGroup;
    RadioButton radioButton;
    CardView submitbtn;
    ImageView backbtn;
    AutoCompleteTextView orgs;

    String url = "https://coderamantech.pythonanywhere.com/predict";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_prediction);

        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.radiotext);

//        orgs = findViewById(R.id.edittext_orgs);
//        ArrayList<String> list = new ArrayList<>();
//        list.add("Rabi");
//        list.add("Kharif");
//        list.add("Zaid");
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.layout_dropdown,list);
//        orgs.setAdapter(arrayAdapter);


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

//        temperature = findViewById(R.id.temperature);
//        humidity = findViewById(R.id.humidity);
        soilpH = findViewById(R.id.soilpH);
//        rainfall = findViewById(R.id.rainfall);
        nitrogen = findViewById(R.id.nitrogen);
        phosphorus = findViewById(R.id.phosphorus);
        potassium = findViewById(R.id.potassium);
        submitbtn = findViewById(R.id.submitbtn);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showDialog();

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                textView.setText(radioButton.getText());


                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String data = jsonObject.getString("crop");
//                                    result.setText(data);

                                    showDialog(data);
//                                    if(data.equals("1"))
//                                    {
//                                        result.setText("grapes");
//                                    }
//                                    else
//                                    {
//                                        result.setText("wheat");
//                                    }
//                                    result.setText(data);
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Crop_Prediction.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                            }
                        }){

                    @Override
                    protected Map<String,String> getParams()
                    {
                        Map<String,String> map = new HashMap<String,String>();
                        map.put("N",nitrogen.getEditText().getText().toString().trim());
                        map.put("P",phosphorus.getEditText().getText().toString().trim());
                        map.put("K",potassium.getEditText().getText().toString().trim());
                        map.put("temperature","89");
                        map.put("humidity","65");
                        map.put("ph", soilpH.getEditText().getText().toString().trim());
                        map.put("rainfall", "89");


                        return map;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(Crop_Prediction.this);
                requestQueue.add(stringRequest);
            }
        });


    }

    private void checkbutton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "selected"+radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    private void showDialog(String result)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout);

        ImageView cropImage = dialog.findViewById(R.id.cropimage);
        TextView croptext = dialog.findViewById(R.id.croptext);
        CardView tomato_fertilizer_calc = dialog.findViewById(R.id.tomato_fertilizer_calc);

//        CardView rice_fertilizer_calc = dialog.findViewById(R.id.rice_fertilizer_calc);

        croptext.setText(result);

        if(result.equals("apple"))
        {
            cropImage.setImageDrawable(ContextCompat.getDrawable(Crop_Prediction.this, R.drawable.apple));
        }
        else if(result.equals("rice"))
        {
            cropImage.setImageDrawable(ContextCompat.getDrawable(Crop_Prediction.this, R.drawable.rice));
        }

        else if(result.equals("maize"))
        {
            cropImage.setImageDrawable(ContextCompat.getDrawable(Crop_Prediction.this, R.drawable.maize));
        }

        else if(result.equals("chickpea"))
        {
            cropImage.setImageDrawable(ContextCompat.getDrawable(Crop_Prediction.this, R.drawable.chickpea));
        }

        else if(result.equals("kidneybeans"))
        {
            cropImage.setImageDrawable(ContextCompat.getDrawable(Crop_Prediction.this, R.drawable.kidneybeans));
        }

        else if(result.equals("pigeonpeas"))
        {
            cropImage.setImageDrawable(ContextCompat.getDrawable(Crop_Prediction.this, R.drawable.pigeonpeas));
        }

        else if(result.equals("mothbeans"))
        {
            cropImage.setImageDrawable(ContextCompat.getDrawable(Crop_Prediction.this, R.drawable.mothbeans));
        }

        else if(result.equals("mango"))
        {
            cropImage.setImageDrawable(ContextCompat.getDrawable(Crop_Prediction.this, R.drawable.mango));
        }

        else if(result.equals("banana"))
        {
            cropImage.setImageDrawable(ContextCompat.getDrawable(Crop_Prediction.this, R.drawable.banana));
        }

        else if(result.equals("coconut"))
        {
            cropImage.setImageDrawable(ContextCompat.getDrawable(Crop_Prediction.this, R.drawable.coconut));
        }

        else
        {
            cropImage.setImageDrawable(ContextCompat.getDrawable(Crop_Prediction.this, R.drawable.maize));
        }
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