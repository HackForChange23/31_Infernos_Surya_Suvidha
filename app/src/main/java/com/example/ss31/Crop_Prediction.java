package com.example.ss31;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

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
import android.widget.EditText;
import android.widget.ImageView;
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

    EditText fasalname, fasaltype, temperature, humidity, soilpH, rainfall, nitrogen, phosphorus, potassium;

    TextView result;
    CardView submitbtn;
    ImageView backbtn;
    AutoCompleteTextView orgs;

    String url = "https://coderamantech.pythonanywhere.com/predict";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_prediction);

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

        fasalname = findViewById(R.id.fasal_name);
        fasaltype = findViewById(R.id.fasal_type);
        temperature = findViewById(R.id.temperature);
        humidity = findViewById(R.id.humidity);
        soilpH = findViewById(R.id.soilpH);
        rainfall = findViewById(R.id.rainfall);
        nitrogen = findViewById(R.id.nitrogen);
        phosphorus = findViewById(R.id.phosphorus);
        potassium = findViewById(R.id.potassium);
        result = findViewById(R.id.result);
        submitbtn = findViewById(R.id.submitbtn);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showDialog();

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
                        map.put("N","76");
                        map.put("P","676");
                        map.put("K","65");
//                        map.put("temperature","45");
//                        map.put("humidity","879");
                        map.put("ph", "989");
//                        map.put("rainfall", "654");
//                        map.put("fasalname", fasalname.getText().toString());
//                        map.put("fasaltype",fasaltype.getText().toString());

                        return map;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(Crop_Prediction.this);
                requestQueue.add(stringRequest);
            }
        });






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
            cropImage.setImageDrawable(ContextCompat.getDrawable(Crop_Prediction.this, R.drawable.tomato));
        }
        else
        {

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