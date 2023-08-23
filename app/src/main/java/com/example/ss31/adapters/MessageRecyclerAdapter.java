package com.example.ss31.adapters;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ss31.R;
import com.example.ss31.modelClass.Message;

import java.util.ArrayList;
import java.util.Locale;

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.ViewHolder> {

    ArrayList<Message> messages = new ArrayList<>();
    Context context;

    TextToSpeech tts;

    public MessageRecyclerAdapter(Context context, ArrayList<Message> msgs){
        this.context = context;
        this.messages = msgs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_message_chatbot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if (i != TextToSpeech.ERROR) {
                    // To Choose language of speech
                    //tts.setLanguage(Locale.ENGLISH);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        tts.setLanguage(Locale.forLanguageTag("hin"));
                    }
                }
            }
        });


        if(messages.get(position).isSentByUser()){
            holder.msgBot.setVisibility(View.GONE);
            holder.textUser.setText(messages.get(position).getContent());
        }
        else{
            holder.msgUser.setVisibility(View.GONE);
            holder.textBot.setText(messages.get(position).getContent());
        }

        holder.playUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mssg = holder.textBot.getText().toString();
                tts.speak(mssg,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        holder.playBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mssg = holder.textUser.getText().toString();
                tts.speak(mssg,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View msgBot;
        View msgUser;
        TextView textBot;
        TextView textUser;
        ImageView playUser;
        ImageView playBot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            msgBot = itemView.findViewById(R.id.msg_bot);
            msgUser = itemView.findViewById(R.id.msg_user);
            textBot = itemView.findViewById(R.id.text_box1);
            textUser = itemView.findViewById(R.id.text_box2);
            playUser = itemView.findViewById(R.id.a1btn);
            playBot = itemView.findViewById(R.id.a2btn);
        }
    }
}
