package com.example.ss31;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link solar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class solar extends Fragment {
    LinearLayout govnschemes;
    String lat , lon;
    int area;

    int Area;


    // ActivityMainBinding binding;

    ProgressBar progressBar;
    TextView progress_txt;
    TextView fetchbtn;


    public String jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec = null;
    public String jancc, febcc, marcc, aprcc, maycc, juncc, julcc, augcc, sepcc, octcc, novcc, deccc = null;


    public int jp,jh, rain_h;
    ViewGroup.LayoutParams params;
    ViewGroup.LayoutParams params_c;


    LinearLayout janparent, febparent, marparent, aprparent, mayparent, junparent, julparent, augparent, sepparent, octparent, novparent, decparent;
    View janv, febv, marv, aprv, mayv, junv, julv, augv, sepv, octv, novv, decv;
    View janc, febc, marc, aprc, mayc, junc, julc, augc, sepc, octc, novc, decc;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public solar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment solar.
     */
    // TODO: Rename and change types and number of parameters
    public static solar newInstance(String param1, String param2) {
        solar fragment = new solar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_solar, container, false);

        TextView knowsolar = (TextView) v.findViewById(R.id.knowsolar);
        TextView datasolar = (TextView) v.findViewById(R.id.solardata);
        RelativeLayout datalinear = (RelativeLayout) v.findViewById(R.id.datalinear);
        LinearLayout knowlinear = (LinearLayout) v.findViewById(R.id.knowlinear);

        TextView npanels = (TextView) v.findViewById(R.id.npanels);
        TextView cost = (TextView) v.findViewById(R.id.cost);
        TextView areatxt = (TextView) v.findViewById(R.id.area);

        TextView future = (TextView) v.findViewById(R.id.future);

        /*future.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment billcalculatingfragment = new expense();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, billcalculatingfragment).addToBackStack(null).commit();



            }
        });*/

        Bundle bundle = getArguments();
//        area = bundle.getInt("area", 50);
        area = 100;
        Area = area;


        govnschemes = (LinearLayout) v.findViewById(R.id.govnschemes);
        govnschemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), govn_scheme.class);
//                startActivity(i);
            }
        });

        datasolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datalinear.setVisibility(View.VISIBLE);
                knowlinear.setVisibility(View.GONE);

                datasolar.setTextColor(Color.parseColor("#000000"));
                datasolar.setBackground(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.whiteoutline));
                knowsolar.setTextColor(Color.parseColor("#FFFFFF"));
                knowsolar.setBackground(null);


            }
        });

        knowsolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                knowlinear.setVisibility(View.VISIBLE);
                datalinear.setVisibility(View.GONE);

                knowsolar.setTextColor(Color.parseColor("#000000"));
                knowsolar.setBackground(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.whiteoutline));
                datasolar.setTextColor(Color.parseColor("#FFFFFF"));
                datasolar.setBackground(null);


                int nsolarpan = Area/9;
                nsolarpan = nsolarpan/3;

                npanels.setText(nsolarpan + " of 330 watts");
                areatxt.setText(Area + "m2");
                cost.setText(nsolarpan*6600 +"");



            }
        });



        TextView loctxt = (TextView) v.findViewById(R.id.location_text);


        if(getArguments()!=null)
        {
            lat = bundle.getString("latitude").substring(0,5);
            lon = bundle.getString("longitude").substring(0,5);
            area = bundle.getInt("area");
            //Area = area;
            //area = area;

            loctxt.setText(lat+" , "+lon);
        }


        TextView compare =  (TextView) v.findViewById(R.id.compare);
        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        progress_txt = (TextView) v.findViewById(R.id.text_view_progress);
        TextView annualtab = (TextView) v.findViewById(R.id.annualtab);
        TextView monthtab = (TextView) v.findViewById(R.id.monthlytab);


        janparent = (LinearLayout) v.findViewById(R.id.janparent);
        febparent = (LinearLayout) v.findViewById(R.id.febparent);
        marparent = (LinearLayout) v.findViewById(R.id.marparent);
        mayparent = (LinearLayout) v.findViewById(R.id.mayparent);
        junparent = (LinearLayout) v.findViewById(R.id.junparent);
        julparent = (LinearLayout) v.findViewById(R.id.julparent);
        augparent = (LinearLayout) v.findViewById(R.id.augparent);
        sepparent = (LinearLayout) v.findViewById(R.id.sepparent);
        octparent = (LinearLayout) v.findViewById(R.id.octparent);
        novparent = (LinearLayout) v.findViewById(R.id.novparent);
        decparent = (LinearLayout) v.findViewById(R.id.decparent);
        aprparent = (LinearLayout) v.findViewById(R.id.aprparent);


        janv = (View) v.findViewById(R.id.jan);
        febv = (View) v.findViewById(R.id.feb);
        marv = (View) v.findViewById(R.id.mar);
        mayv = (View) v.findViewById(R.id.may);
        junv = (View) v.findViewById(R.id.jun);
        julv = (View) v.findViewById(R.id.jul);
        augv = (View) v.findViewById(R.id.aug);
        sepv = (View) v.findViewById(R.id.sep);
        octv = (View) v.findViewById(R.id.oct);
        novv = (View) v.findViewById(R.id.nov);
        decv = (View) v.findViewById(R.id.dec);
        aprv = (View) v.findViewById(R.id.apr);



        janc = (View) v.findViewById(R.id.jancomp);
        febc = (View) v.findViewById(R.id.febcomp);
        marc = (View) v.findViewById(R.id.marcomp);
        mayc = (View) v.findViewById(R.id.maycomp);
        junc = (View) v.findViewById(R.id.juncomp);
        julc = (View) v.findViewById(R.id.julcomp);
        augc = (View) v.findViewById(R.id.augcomp);
        sepc = (View) v.findViewById(R.id.sepcomp);
        octc = (View) v.findViewById(R.id.octcomp);
        novc = (View) v.findViewById(R.id.novcomp);
        decc = (View) v.findViewById(R.id.deccomp);
        aprc = (View) v.findViewById(R.id.aprcomp);


        janc.setVisibility(v.GONE);
        febc.setVisibility(v.GONE);
        marc.setVisibility(v.GONE);
        aprc.setVisibility(v.GONE);
        mayc.setVisibility(v.GONE);
        junc.setVisibility(v.GONE);
        julc.setVisibility(v.GONE);
        augc.setVisibility(v.GONE);
        sepc.setVisibility(v.GONE);
        augc.setVisibility(v.GONE);
        octc.setVisibility(v.GONE);
        novc.setVisibility(v.GONE);
        decc.setVisibility(v.GONE);



        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new fetchdata_rain().start();

                if(jancc==null || febcc==null || marcc==null || aprcc==null || maycc==null || juncc==null || julcc==null || augcc==null || sepcc==null || octcc==null || novcc==null || deccc==null)
                {
                    Toast.makeText(getContext(), "Please Wait, Its Loading!", Toast.LENGTH_SHORT).show();
                    //progress_txt.setText("null");
                }
                else
                {
                    Float jani, febi, mari, apri, mayi, juni, juli, augi, sepi, octi, novi, deci;
                    jani = Float.parseFloat(jancc);
                    febi = Float.parseFloat(febcc);
                    mari = Float.parseFloat(marcc);
                    apri = Float.parseFloat(aprcc);
                    mayi = Float.parseFloat(maycc);
                    juni = Float.parseFloat(juncc);
                    juli = Float.parseFloat(julcc);
                    augi = Float.parseFloat(augcc);
                    sepi = Float.parseFloat(sepcc);
                    octi=  Float.parseFloat(octcc);
                    novi = Float.parseFloat(novcc);
                    deci = Float.parseFloat(deccc);


                    jani = (jani/100)*100;
                    febi = (febi/100)*100;
                    mari = (mari/100)*100;
                    apri = (apri/100)*100;
                    mayi = (mayi/100)*100;
                    juni = (juni/100)*100;
                    juli = (juli/100)*100;
                    augi = (augi/100)*100;
                    sepi = (sepi/100)*100;
                    octi=  (octi/100)*100;
                    novi = (novi/100)*100;
                    deci = (deci/100)*100;


                    jani = (jani*200)/100;
                    febi = (febi*200)/100;
                    mari = (mari*200)/100;
                    apri = (apri*200)/100;
                    mayi = (mayi*200)/100;
                    juni = (juni*200)/100;
                    juli = (juli*200)/100;
                    augi = (augi*200)/100;
                    sepi = (sepi*200)/100;
                    octi=  (octi*200)/100;
                    novi = (novi*200)/100;
                    deci = (deci*200)/100;


                    rain_h = Math.round(jani);
                    params = janc.getLayoutParams();
                    params.height = rain_h;
                    janc.setLayoutParams(params);


                    rain_h = Math.round(febi);
                    params = febc.getLayoutParams();
                    params.height = rain_h;
                    febc.setLayoutParams(params);

                    rain_h = Math.round(mari);
                    params = marc.getLayoutParams();
                    params.height = rain_h;
                    marc.setLayoutParams(params);

                    rain_h = Math.round(apri);
                    params = aprc.getLayoutParams();
                    params.height = rain_h;
                    aprc.setLayoutParams(params);

                    rain_h = Math.round(mayi);
                    params = mayc.getLayoutParams();
                    params.height = rain_h;
                    mayc.setLayoutParams(params);

                    rain_h = Math.round(juni);
                    params = junc.getLayoutParams();
                    params.height = rain_h;
                    junc.setLayoutParams(params);

                    rain_h = Math.round(juli);
                    params = julc.getLayoutParams();
                    params.height = rain_h;
                    julc.setLayoutParams(params);

                    rain_h = Math.round(augi);
                    params = augc.getLayoutParams();
                    params.height = rain_h;
                    augc.setLayoutParams(params);

                    rain_h = Math.round(sepi);
                    params = sepc.getLayoutParams();
                    params.height = rain_h;
                    sepc.setLayoutParams(params);

                    rain_h = Math.round(octi);
                    params = octc.getLayoutParams();
                    params.height = rain_h;
                    octc.setLayoutParams(params);

                    rain_h = Math.round(novi);
                    params = novc.getLayoutParams();
                    params.height = rain_h;
                    novc.setLayoutParams(params);

                    rain_h = Math.round(deci);
                    params = decc.getLayoutParams();
                    params.height = rain_h;
                    decc.setLayoutParams(params);






                    janc.setVisibility(v.VISIBLE);
                    febc.setVisibility(v.VISIBLE);
                    marc.setVisibility(v.VISIBLE);
                    aprc.setVisibility(v.VISIBLE);
                    mayc.setVisibility(v.VISIBLE);
                    junc.setVisibility(v.VISIBLE);
                    julc.setVisibility(v.VISIBLE);
                    augc.setVisibility(v.VISIBLE);
                    sepc.setVisibility(v.VISIBLE);
                    augc.setVisibility(v.VISIBLE);
                    octc.setVisibility(v.VISIBLE);
                    novc.setVisibility(v.VISIBLE);
                    decc.setVisibility(v.VISIBLE);

                }


            }
        });




        annualtab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new fetchdata().start();
                new fetchdata_rain().start();

                annualtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                monthtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                janparent.setBackgroundColor(0);
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);




                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }
                else
                {
                    //area = 32;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));


                    Float jani, febi, mari, apri, mayi, juni, juli, augi, sepi, octi, novi, deci;
                    jani = Float.parseFloat(jan) * e;
                    febi = Float.parseFloat(feb) * e;
                    mari = Float.parseFloat(mar)* e;
                    apri = Float.parseFloat(apr)* e;
                    mayi = Float.parseFloat(may)* e;
                    juni = Float.parseFloat(jun)* e;
                    juli = Float.parseFloat(jul)* e;
                    augi = Float.parseFloat(aug)* e;
                    sepi = Float.parseFloat(sep)* e;
                    octi= Float.parseFloat(oct)* e;
                    novi = Float.parseFloat(nov)* e;
                    deci = Float.parseFloat(dec)* e;

                    Float avg = (jani + febi + mari + apri + mayi + juni + juli + augi + sepi + octi + novi + deci);






                    // progress_txt.setText(s);

                    int i = Math.round(avg);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/year");

                    i = i*100;
                    i= (int) (i/(area*17.5*12));

                    progressBar.setProgress(i);



                    jp = Math.round(jani);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = janv.getLayoutParams();
                    params.height = jh;
                    janv.setLayoutParams(params);

                    if(jp>75)
                    {
                        janv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.redoutline));
                    }
                    else if(jp>50)
                    {
                        janv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                    }
                    else if(jp>=0)
                    {
                        janv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greenoutline));
                    }
                    else
                    {
                        janv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(febi);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = febv.getLayoutParams();
                    params.height = jh;
                    febv.setLayoutParams(params);
                    if(jp>75)
                    {
                        febv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.redoutline));
                    }
                    else if(jp>50)
                    {
                        febv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                    }
                    else if(jp>=0)
                    {
                        febv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greenoutline));
                    }
                    else
                    {
                        febv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(mari);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = marv.getLayoutParams();
                    params.height = jh;
                    marv.setLayoutParams(params);
                    if(jp>75)
                    {
                        marv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.redoutline));
                    }
                    else if(jp>50)
                    {
                        marv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                    }
                    else if(jp>=0)
                    {
                        marv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greenoutline));
                    }
                    else
                    {
                        marv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(apri);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = aprv.getLayoutParams();
                    params.height = jh;
                    aprv.setLayoutParams(params);

                    if(jp>75)
                    {
                        aprv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.redoutline));
                    }
                    else if(jp>50)
                    {
                        aprv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                    }
                    else if(jp>=0)
                    {
                        aprv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greenoutline));
                    }
                    else
                    {
                        aprv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(mayi);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = mayv.getLayoutParams();
                    params.height = jh;
                    mayv.setLayoutParams(params);
                    if(jp>75)
                    {
                        mayv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.redoutline));
                    }
                    else if(jp>50){
                        mayv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                    }
                    else if(jp>=0)
                    {
                        mayv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greenoutline));
                    }
                    else
                    {
                        mayv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(juni);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = junv.getLayoutParams();
                    params.height = jh;
                    junv.setLayoutParams(params);

                    if(jp>75)
                    {
                        junv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.redoutline));
                    }
                    else if(jp>50)
                    {
                        junv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                    }
                    else if(jp>=0)
                    {
                        junv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greenoutline));
                    }
                    else
                    {
                        junv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }


                    jp = Math.round(juli);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = julv.getLayoutParams();
                    params.height = jh;
                    julv.setLayoutParams(params);

                    if(jp>75)
                    {
                        julv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.redoutline));
                    }
                    else if(jp>50)
                    {
                        julv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                    }
                    else if(jp>=0)
                    {
                        julv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greenoutline));
                    }
                    else
                    {
                        julv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }


                    jp = Math.round(augi);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = augv.getLayoutParams();
                    params.height = jh;
                    augv.setLayoutParams(params);

                    if(jp>75)
                    {
                        augv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.redoutline));
                    }
                    else if(jp>50)
                    {
                        augv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                    }
                    else if(jp>=0)
                    {
                        augv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greenoutline));
                    }
                    else
                    {
                        augv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(sepi);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = sepv.getLayoutParams();
                    params.height = jh;
                    sepv.setLayoutParams(params);

                    if(jp>75)
                    {
                        sepv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.redoutline));
                    }
                    else if(jp>50)
                    {
                        sepv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                    }
                    else if(jp>=0)
                    {
                        sepv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greenoutline));
                    }
                    else
                    {
                        sepv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(octi);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = octv.getLayoutParams();
                    params.height = jh;
                    octv.setLayoutParams(params);

                    if(jp>75)
                    {
                        octv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.redoutline));
                    }
                    else if(jp>50)
                    {
                        octv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                    }
                    else if(jp>=0)
                    {
                        octv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greenoutline));
                    }
                    else
                    {
                        octv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }


                    jp = Math.round(novi);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = novv.getLayoutParams();
                    params.height = jh;
                    novv.setLayoutParams(params);

                    if(jp>75)
                    {
                        novv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.redoutline));
                    }
                    else if(jp>50)
                    {
                        novv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                    }
                    else if(jp>=0)
                    {
                        novv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greenoutline));
                    }
                    else
                    {
                        novv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(deci);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = decv.getLayoutParams();
                    params.height = jh;
                    decv.setLayoutParams(params);

                    if(jp>75)
                    {
                        decv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.redoutline));
                    }
                    else if(jp>50)
                    {
                        decv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                    }
                    else if(jp>=0)
                    {
                        decv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greenoutline));
                    }
                    else
                    {
                        decv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }

                }

            }
        });





        janparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                annualtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                janparent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                    Toast.makeText(getContext(), "Please Wait, Its Loading", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    //float area = 32;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(jan) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/January");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });


        febparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                annualtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                febparent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                janparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                    Toast.makeText(getContext(), "Please Wait, Its Loading", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    //float area = 32;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(feb) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/February");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });



        marparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                annualtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                marparent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                febparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                    Toast.makeText(getContext(), "Please Wait, Its Loading", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    // float area = 32;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(mar) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/March");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });


        aprparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                annualtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                aprparent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                    Toast.makeText(getContext(), "Please Wait, Its Loading", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    //float area = 32;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(apr) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/April");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });




        mayparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                annualtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                mayparent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                    Toast.makeText(getContext(), "Please Wait, Its Loading", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    //float area = 32;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(may) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/May");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });




        junparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                annualtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                junparent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                    Toast.makeText(getContext(), "Please Wait, Its Loading", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    //float area = 32;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(jun) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/June");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });


        julparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                annualtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                julparent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                    Toast.makeText(getContext(), "Please Wait, Its Loading", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    // float area = 32;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(jul) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/July");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });


        augparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                annualtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                augparent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                    Toast.makeText(getContext(), "Please Wait, Its Loading", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    //float area = 32;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(aug) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/August");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });




        sepparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                annualtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                sepparent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                    Toast.makeText(getContext(), "Please Wait, Its Loading", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    //float area = 32;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(sep) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/September");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });


        octparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                annualtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                octparent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                    Toast.makeText(getContext(), "Please Wait, Its Loading", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    //float area = 32;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(oct) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/October");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });


        novparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                annualtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                novparent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                    Toast.makeText(getContext(), "Please Wait, Its Loading", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    //float area = 32;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(nov) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/November");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });



        decparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                annualtab.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                decparent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardoutline));
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                    Toast.makeText(getContext(), "Please Wait, Its Loading", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    //float area = 32;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(dec) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/December");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });





        return v;
    }


    class fetchdata extends Thread{

        @Override
        public void run() {
            super.run();


            String data = " ";
            try {

                URL url = new URL("https://power.larc.nasa.gov/api/temporal/climatology/point?parameters=ALLSKY_SFC_SW_DWN&community=RE&longitude="+lon+"&latitude="+lat+"&format=JSON");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while( (line = bufferedReader.readLine()) != null)
                {
                    data = data + line;
                }

                if(!data.isEmpty())
                {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONObject irradiance = jsonObject.getJSONObject("properties").getJSONObject("parameter").getJSONObject("ALLSKY_SFC_SW_DWN");

                    jan = irradiance.getString("JAN");
                    feb = irradiance.getString("FEB");
                    mar = irradiance.getString("MAR");
                    apr = irradiance.getString("APR");
                    may = irradiance.getString("MAY");
                    jun = irradiance.getString("JUN");
                    jul = irradiance.getString("JUL");
                    aug = irradiance.getString("AUG");
                    sep = irradiance.getString("SEP");
                    oct = irradiance.getString("OCT");
                    nov = irradiance.getString("NOV");
                    dec = irradiance.getString("DEC");


                }







            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        //new fetchdata().start();

    }



    class fetchdata_rain extends Thread{

        @Override
        public void run() {
            super.run();


            String data = " ";
            try {

                URL url = new URL("https://power.larc.nasa.gov/api/temporal/climatology/point?parameters=PRECTOTCORR,PRECTOTCORR_SUM&community=RE&longitude="+lon+"&latitude="+lat+"&format=JSON&start=1984&end=2020");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while( (line = bufferedReader.readLine()) != null)
                {
                    data = data + line;
                }

                if(!data.isEmpty())
                {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONObject irradiance = jsonObject.getJSONObject("properties").getJSONObject("parameter").getJSONObject("PRECTOTCORR_SUM");

                    jancc = irradiance.getString("JAN");
                    febcc = irradiance.getString("FEB");
                    marcc = irradiance.getString("MAR");
                    aprcc = irradiance.getString("APR");
                    maycc = irradiance.getString("MAY");
                    juncc = irradiance.getString("JUN");
                    julcc = irradiance.getString("JUL");
                    augcc = irradiance.getString("AUG");
                    sepcc = irradiance.getString("SEP");
                    octcc = irradiance.getString("OCT");
                    novcc = irradiance.getString("NOV");
                    deccc = irradiance.getString("DEC");


                }







            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }




}