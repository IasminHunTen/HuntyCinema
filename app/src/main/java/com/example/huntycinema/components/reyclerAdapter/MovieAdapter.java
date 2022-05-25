package com.example.huntycinema.components.reyclerAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.huntycinema.MovieDescriptionActivity;
import com.example.huntycinema.R;
import com.example.huntycinema.components.MovieItem;


import java.util.Arrays;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    List<MovieItem> movieItemList;
    Context context;

    public MovieAdapter(List<MovieItem> movieItemList, Context context) {
        this.movieItemList = movieItemList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title_date;
        ImageView movie_poster;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title_date = itemView.findViewById(R.id.title_time);
            movie_poster = itemView.findViewById(R.id.movie_poster);
        }
    }

    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int position) {
        MovieItem movieItem = movieItemList.get(position);
        holder.title_date.setText(movieItem.getTitle() + " - " + movieItem.getDate());
        Glide.with(context)
                .load(movieItem.getImageUrl())
                .centerCrop()
                .into(holder.movie_poster);
        holder.movie_poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent movie_description_intent = new Intent(context, MovieDescriptionActivity.class);
                movie_description_intent.putExtra("movie_item", movieItem);
                ((Activity)context).startActivity(movie_description_intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieItemList.size();
    }
}
