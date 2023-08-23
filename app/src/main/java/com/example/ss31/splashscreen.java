package com.example.ss31;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class splashscreen extends AppCompatActivity {

    int mark1, mark2, mark3, mark4;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        mark1=0;
        mark2=0;
        mark3=0;
        mark4=0;

        FragmentManager m = getSupportFragmentManager();
        FragmentTransaction t = m.beginTransaction();
        Fragment frame = new OpenFrame1();
        t.replace(R.id.openframe, frame);
        t.commit();

/*
            Intent i = getIntent();
            Bundle bundle = i.getExtras();
            mark1 = bundle.getInt("mark1");
            mark2 = bundle.getInt("mark2");
            mark3 = bundle.getInt("mark3");
            mark4 = bundle.getInt("mark4");



        if(mark4==1 && mark1==0 && mark2==0 && mark3==0)
        {
            Intent ii = new Intent(this, MainActivity.class);
            startActivity(ii);
        }
        else if(mark3==0 && mark1==0 && mark2==0 && mark4==1)
        {
            Fragment frame1 = new Openframe4();
            t.replace(R.id.openframe, frame1);
            t.commit();
        }
        else if(mark2==0 && mark1==0 && mark3==1 && mark4==0)
        {
            Fragment frame1 = new Openframe3();
            t.replace(R.id.openframe, frame1);
            t.commit();
        }
        else if(mark1==0 && mark2==1 && mark3==0 && mark4==0)
        {
            Fragment frame1 = new Openframe2();
            t.replace(R.id.openframe, frame1);
            t.commit();
        }
        else
        {
            Toast.makeText(this, "m"+mark1+mark2+mark3+mark4, Toast.LENGTH_SHORT).show();
        }
*/


    }
}