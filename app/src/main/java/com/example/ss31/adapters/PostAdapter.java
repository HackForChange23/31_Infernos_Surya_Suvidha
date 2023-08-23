package com.example.ss31.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ss31.R;
import com.example.ss31.modelClass.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    ArrayList<Post> posts = new ArrayList<>();

    Context context;
    public PostAdapter(Context context, ArrayList<Post> posts){

        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.author.setText(posts.get(position).getAuthor());
        Glide.with(holder.view)
                .load(posts.get(position).getImagelink())
                .into(holder.image);
        holder.caption.setText(posts.get(position).getCaption());
        holder.like.setText(String.valueOf(posts.get(position).getLike()));
        holder.comment.setText(String.valueOf(posts.get(position).getComment()));
        holder.date.setText(String.valueOf(posts.get(position).getData()));
        holder.time.setText(String.valueOf(posts.get(position).getTime()));
//        holder.share.setText(posts.get(position).getShare());
//        holder.save.setText(posts.get(position).getSave());

        holder.share.setVisibility(View.GONE);
        holder.save.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView author, caption, date, time, like, comment, share, save;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            image = itemView.findViewById(R.id.image_posts);
            author = itemView.findViewById(R.id.author_posts);
            caption = itemView.findViewById(R.id.caption_posts);
            like = itemView.findViewById(R.id.like_posts);
            comment = itemView.findViewById(R.id.comment_posts);
            share = itemView.findViewById(R.id.share_posts);
            save = itemView.findViewById(R.id.save_posts);
            date = itemView.findViewById(R.id.date_posts);
            time = itemView.findViewById(R.id.time_posts);
        }
    }
}
