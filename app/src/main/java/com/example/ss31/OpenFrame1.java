package com.example.ss31;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpenFrame1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpenFrame1 extends Fragment {

    int mark1, mark2, mark3, mark4;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpenFrame1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpenFrame1.
     */
    // TODO: Rename and change types and number of parameters
    public static OpenFrame1 newInstance(String param1, String param2) {
        OpenFrame1 fragment = new OpenFrame1();
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
        View v = inflater.inflate(R.layout.fragment_open_frame1, container, false);

        mark1=0;
        mark2=0;
        mark3=0;
        mark4=0;

        View mark1 = (View) v.findViewById(R.id.markframe1);
        View mark2 = (View) v.findViewById(R.id.markframe2);
        View mark3 = (View) v.findViewById(R.id.markframe3);
        View mark4 = (View) v.findViewById(R.id.markframe4);

        mark1.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.orangecircle));
        mark2.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.whitecircle));
        mark3.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.whitecircle));
        mark4.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.whitecircle));

        TextView getbtn = (TextView) v.findViewById(R.id.getstartedbtn);
        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new OpenFrame2();
                //myFragment.setArguments(bundle_addsubject);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.openframe, myFragment).addToBackStack(null).commit();


               /* mark1 = 1;
                Bundle bundle = new Bundle();
                bundle.putInt("mark1", 0);
                bundle.putInt("mark2", 1);
                bundle.putInt("mark3", 0);
                bundle.putInt("mark4", 0);*/

               /* Intent i = new Intent(getActivity(),splashscreen.class);
                i.putExtra("bundle", bundle);
                startActivity(i); */


            }
        });



        return v;
    }
}