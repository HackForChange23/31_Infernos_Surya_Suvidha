package com.example.ss31;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpenFrame4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpenFrame4 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpenFrame4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpenFrame4.
     */
    // TODO: Rename and change types and number of parameters
    public static OpenFrame4 newInstance(String param1, String param2) {
        OpenFrame4 fragment = new OpenFrame4();
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
        View v = inflater.inflate(R.layout.fragment_open_frame4, container, false);

        Bundle b = getArguments();
        TextView hindi = v.findViewById(R.id.hindi);
        TextView english = v.findViewById(R.id.english);
        TextView guest = v.findViewById(R.id.guest);

        final boolean[] e = {false};
        final boolean[] h = {false};



        hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hindi.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greencircle));
                english.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.orangecircle));

                e[0] = false;
                h[0] = true;

            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e[0] = true;
                h[0] = false;

                hindi.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.orangecircle));
                english.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greencircle));
            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(h[0] ==true || e[0] ==true)
                {
                    Intent i = new Intent(getContext(), MainActivity.class);
                    b.putString("hindi",h[0]+"");
                    b.putString("english",e[0]+"");
                    i.putExtra("bundle", b);
                    startActivity(i);
                }
            }
        });
        return v;
    }
}