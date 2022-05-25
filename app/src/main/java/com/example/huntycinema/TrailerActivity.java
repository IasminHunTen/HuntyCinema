package com.example.huntycinema;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class TrailerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);
        VideoView trailer = (VideoView) findViewById(R.id.trailer_video);
        Uri trailer_uri = Uri.parse(getIntent().getStringExtra("trailerUrl"));
        trailer.setVideoURI(trailer_uri);
        MediaController mediaController = new MediaController(this);
        trailer.setMediaController(mediaController);
        mediaController.setAnchorView(trailer);
    }
}