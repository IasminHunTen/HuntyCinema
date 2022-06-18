package com.example.huntycinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huntycinema.localstorage.DataStorageSingleton;
import com.example.huntycinema.services.cinema_server.genres.Genre;
import com.example.huntycinema.services.cinema_server.genres.GenreClient;
import com.example.huntycinema.services.cinema_server.users.cards.Card;
import com.example.huntycinema.services.cinema_server.users.cards.CardClient;
import com.example.huntycinema.services.cinema_server.users.fav_genres.FavGenClient;
import com.example.huntycinema.utils.ResponseHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyAccountActivity extends AppCompatActivity {
    Button logout, my_cards, my_genres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        init();
    }

    private void init(){
        my_cards = findViewById(R.id.my_credit_cards);
        show_credit_cards();
        my_genres = findViewById(R.id.my_fav_genres);
        my_genres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_favorite_genres();
            }
        });
    }

    private void show_credit_cards(){
        my_cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CardClient().fetch_credit_cards(DataStorageSingleton.getToken(), new ResponseHandler<List<Card>>() {
                    @Override
                    public void onResponse(List<Card> response) {
                        DataStorageSingleton.setMyCards(response);
                        startActivity(new Intent(getApplicationContext(), MyCardsActivity.class));
                    }

                    @Override
                    public void onError(int code, String error) {
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    
    private void show_favorite_genres(){
        Intent intent = new Intent(getApplicationContext(), FavoriteGenreActivity.class);
        new GenreClient().getGenres(new ResponseHandler<List<Genre>>() {
            @Override
            public void onResponse(List<Genre> all_genres) {
                new FavGenClient().get_fav_genres(DataStorageSingleton.getToken(), new ResponseHandler<List<Genre>>() {
                    @Override
                    public void onResponse(List<Genre> response) {
                        intent.putExtra("allGenres", (Parcelable) all_genres);
                        intent.putExtra("favGenres", (Parcelable) response);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(int code, String error) {
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onError(int code, String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
            }
        });
    }
}