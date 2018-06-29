package com.back4app.quickstartexampleapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private List<PostClass> postClassList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, user;
        public ImageView img;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            user = (TextView) view.findViewById(R.id.username);
            img = (ImageView) view.findViewById(R.id.imgPost);
        }
    }


    public PostAdapter(List<PostClass> postClassList) {
        this.postClassList = postClassList;
    }

    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list_row, parent, false);

        return new PostAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostAdapter.MyViewHolder holder, int position) {
        PostClass postClass = postClassList.get(position);
        holder.title.setText(postClass.getTitle());
        holder.user.setText(postClass.getUsername());
        holder.img.setImageBitmap(postClass.getImg());
    }

    @Override
    public int getItemCount() {
        return postClassList.size();
    }
}
