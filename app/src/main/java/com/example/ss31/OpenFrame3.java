package com.example.ss31;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpenFrame3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpenFrame3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpenFrame3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpenFrame3.
     */
    // TODO: Rename and change types and number of parameters
    public static OpenFrame3 newInstance(String param1, String param2) {
        OpenFrame3 fragment = new OpenFrame3();
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
        View v = inflater.inflate(R.layout.fragment_open_frame3, container, false);


        Bundle bundle = getArguments();
        TextView nextbtn = (TextView) v.findViewById(R.id.Nexttbtn);
        TextView lattxt = (TextView) v.findViewById(R.id.lat);
        TextView lontxt = (TextView) v.findViewById(R.id.lon);

        if(getArguments()!=null && bundle.getString("map").equals("true"))
        {
            lattxt.setText(bundle.getString("latitude").substring(0,9));
            lontxt.setText(bundle.getString("longitude").substring(0,9));
        }

        FragmentManager m = getActivity().getSupportFragmentManager();
        FragmentTransaction t = m.beginTransaction();
        Fragment frame = new MapsFragment();
        bundle.putString("latitude", "17.4351");
        bundle.putString("longitude", "78.3407");
        frame.setArguments(bundle);
        t.replace(R.id.mapframe, frame);
        t.commit();


        View mark1 = (View) v.findViewById(R.id.markframe1);
        View mark2 = (View) v.findViewById(R.id.markframe2);
        View mark3 = (View) v.findViewById(R.id.markframe3);
        View mark4 = (View) v.findViewById(R.id.markframe4);

        mark1.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.orangecircle));
        mark2.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.orangecircle));
        mark3.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.orangecircle));
        mark4.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.whitecircle));

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(getArguments()!=null)
                {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new OpenFrame4();
                    myFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.openframe, myFragment).addToBackStack(null).commit();

                }
                else
                {
                    Toast.makeText(getContext(), "Press Long to mark your location", Toast.LENGTH_SHORT).show();
                }



            }
        });

        return v;
    }
    }