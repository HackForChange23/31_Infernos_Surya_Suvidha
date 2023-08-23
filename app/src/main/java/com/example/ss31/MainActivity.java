package com.example.ss31;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView homebtn, solarbtn, fasalbtn, notificationbtn, userbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homebtn = (ImageView) findViewById(R.id.homebtn);
        solarbtn = (ImageView) findViewById(R.id.solarbtn);
//        fasalbtn = (ImageView) findViewById(R.id.fasalbtn);
        notificationbtn = (ImageView) findViewById(R.id.notificationbtn);
        userbtn = (ImageView) findViewById(R.id.userbtn);

        homebtn.setImageResource(R.drawable.homecolor);
        FragmentManager m = getSupportFragmentManager();
        FragmentTransaction t = m.beginTransaction();
        Fragment Home = new FeedFragment();
        t.replace(R.id.fragment, Home);
        t.commit();

//        homebtn.setOnClickListener(new View.OnClickListener() {
        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");



//        ImageView solarbtn = (ImageView) findViewById(R.id.solarbtn);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solarbtn.setImageResource(R.drawable.sunfill);
                homebtn.setImageResource(R.drawable.homecolor);
//                windbtn.setImageResource(R.drawable.windmill);
//                fasalbtn.setImageResource(R.drawable.crop_outline);
                notificationbtn.setImageResource(R.drawable.notification);
                userbtn.setImageResource(R.drawable.user);
                FragmentManager m = getSupportFragmentManager();
                FragmentTransaction t = m.beginTransaction();
                Fragment home = new FeedFragment();
                home.setArguments(bundle);
                t.replace(R.id.fragment, home);
                t.commit();
            }
        });


        solarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solarbtn.setImageResource(R.drawable.sunimg);
                homebtn.setImageResource(R.drawable.explore);
//                windbtn.setImageResource(R.drawable.windmill);
//                fasalbtn.setImageResource(R.drawable.crop_outline);
                notificationbtn.setImageResource(R.drawable.notification);
                userbtn.setImageResource(R.drawable.user);
                FragmentManager m = getSupportFragmentManager();
                FragmentTransaction t = m.beginTransaction();
                Fragment solar = new solar();
                solar.setArguments(bundle);
                t.replace(R.id.fragment, solar);
                t.commit();
            }
        });

//        fasalbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                solarbtn.setImageResource(R.drawable.sunfill);
//                homebtn.setImageResource(R.drawable.explore);
//                fasalbtn.setImageResource(R.drawable.rice);
//                notificationbtn.setImageResource(R.drawable.notification);
//                userbtn.setImageResource(R.drawable.user);
//                FragmentManager m = getSupportFragmentManager();
//                FragmentTransaction t = m.beginTransaction();
//                Fragment solar = new fasal_management();
//                t.replace(R.id.fragment, solar);
//                t.commit();
//            }
//        });


        notificationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solarbtn.setImageResource(R.drawable.sunfill);
                homebtn.setImageResource(R.drawable.explore);
//                fasalbtn.setImageResource(R.drawable.crop_outline);
                notificationbtn.setImageResource(R.drawable.notificationcolor);
                userbtn.setImageResource(R.drawable.user);
                FragmentManager m = getSupportFragmentManager();
                FragmentTransaction t = m.beginTransaction();
                Fragment solar = new notification();
                t.replace(R.id.fragment, solar);
                t.commit();
            }
        });


        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solarbtn.setImageResource(R.drawable.sunfill);
                homebtn.setImageResource(R.drawable.explore);
//                fasalbtn.setImageResource(R.drawable.crop_outline);
                notificationbtn.setImageResource(R.drawable.notification);
                userbtn.setImageResource(R.drawable.usercolor);
                FragmentManager m = getSupportFragmentManager();
                FragmentTransaction t = m.beginTransaction();
                Fragment solar = new User();
                t.replace(R.id.fragment, solar);
                t.commit();
            }
        });
    }
}