package com.example.ss31;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ss31.adapters.MessageRecyclerAdapter;
import com.example.ss31.modelClass.Message;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView homebtn, solarbtn, fasalbtn, notificationbtn, userbtn;
    FloatingActionButton chatbot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homebtn = (ImageView) findViewById(R.id.homebtn);
        solarbtn = (ImageView) findViewById(R.id.solarbtn);
//        fasalbtn = (ImageView) findViewById(R.id.fasalbtn);
        notificationbtn = (ImageView) findViewById(R.id.notificationbtn);
        userbtn = (ImageView) findViewById(R.id.userbtn);

        chatbot = (FloatingActionButton) findViewById(R.id.floatingActionButton);
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



        Dialog dlg;
        FloatingActionButton dialog_button;
        FloatingActionButton cancel_button;

        dlg = new Dialog(MainActivity.this);
        dlg.setContentView(R.layout.dialog_chatbot);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dlg.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_dialog));
        }
        dlg.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        cancel_button = (FloatingActionButton) dlg.findViewById(R.id.cancel_button);
        dialog_button = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
                dialog_button.setVisibility(View.VISIBLE);
            }
        });
        dialog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.show();
                dialog_button.setVisibility(View.GONE);
            }
        });

        ArrayList<Message> messages =  new ArrayList<>();

        Context context = LocaleHelper.setLocale(MainActivity.this, "en");

        messages.add(new Message(1,(context.getResources().getString(R.string.mssg1)),false));
        messages.add(new Message(1,(context.getResources().getString(R.string.mssg2)),true));
        messages.add(new Message(1,(context.getResources().getString(R.string.mssg3)),false));
        messages.add(new Message(1,(context.getResources().getString(R.string.mssg4)),false));
        messages.add(new Message(1,(context.getResources().getString(R.string.mssg5)),true));

        MessageRecyclerAdapter messagesRecyclerAdapter = new MessageRecyclerAdapter(this,messages);

        RecyclerView recyclerView = dlg.findViewById(R.id.msgs_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(messagesRecyclerAdapter);



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


        ImageView sendBtn = (ImageView) dlg.findViewById(R.id.sendbtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }
}