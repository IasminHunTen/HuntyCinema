package com.example.huntycinema;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.huntycinema.components.MovieItem;
import com.squareup.picasso.Picasso;

public class MovieDescriptionActivity extends AppCompatActivity {

    private MovieItem movieItem;


    private static final String TAG = "MovieDescriptionActivit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);
        init();
        playTrailer();
        imdbRate();
    }

    private void init(){
        movieItem = (MovieItem) getIntent().getSerializableExtra("movie_item");

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        ImageView poster = (ImageView) findViewById(R.id.full_cover_poster);
        Glide.with(this)
                .load(movieItem.getImageUrl())
                .into(poster);

        TextView header = (TextView) findViewById(R.id.header_description);
        header.setText(movieItem.prepareHeader());

        TextView plot = (TextView) findViewById(R.id.plot);
        plot.setText(movieItem.getPlot());

        TextView top_cast = (TextView) findViewById(R.id.top_cast);
        top_cast.setText(movieItem.getTop_cast());

        TextView directors = (TextView) findViewById(R.id.director);
        directors.setText(movieItem.getDirectors());
    }

    private void playTrailer(){
        ((Button) findViewById(R.id.trailer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trailer_intent = new Intent(getApplicationContext(), TrailerActivity.class);
                trailer_intent.putExtra("trailerUrl", movieItem.getTrailerUrl());
                startActivity(trailer_intent);
            }
        });
    }

    private void imdbRate(){
        if (movieItem.getImdb_rate() == null)
            return;
        Button button = (Button) findViewById(R.id.imdb_rate);
        button.setText(movieItem.getImdb_rate());
        button.setVisibility(View.VISIBLE);
    }

}