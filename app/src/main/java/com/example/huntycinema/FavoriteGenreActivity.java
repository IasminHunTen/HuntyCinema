package com.example.huntycinema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Button;

import com.example.huntycinema.components.reyclerAdapter.AllGenresAdapter;
import com.example.huntycinema.components.reyclerAdapter.MyFavGenresAdapter;
import com.example.huntycinema.components.reyclerAdapter.OnDeleteAdapterItem;
import com.example.huntycinema.services.cinema_server.genres.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FavoriteGenreActivity extends AppCompatActivity {
    private RecyclerView all_genres_rv, my_fav_genres_rv;
    private Set<Genre> all_genres, my_fav_genres;
    private List<String> all_genres_values, my_fav_genres_values;
    private Button commit;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_genre);
        intent = getIntent();
        init();
    }

    private void setUpRV(RecyclerView rv){
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
    }

    private void updateAdapters(){
        all_genres_rv.setAdapter(new AllGenresAdapter(all_genres_values));
        my_fav_genres_rv.setAdapter(new MyFavGenresAdapter(my_fav_genres_values));
    }

    private void adapterDeleteElementImpl(){
        AllGenresAdapter.setOnDeleteAdapterItem(new OnDeleteAdapterItem<Integer>() {
            @Override
            public void onDelete(Integer value) {
                my_fav_genres_values.add(all_genres_values.remove(value.intValue()));
                updateAdapters();
            }
        });

        MyFavGenresAdapter.setOnDeleteAdapterItem(new OnDeleteAdapterItem<Integer>() {
            @Override
            public void onDelete(Integer value) {
                all_genres_values.add(my_fav_genres_values.remove(value.intValue()));
                updateAdapters();
            }
        });
    }

    private void init(){
        all_genres_rv = findViewById(R.id.all_genres_list);
        setUpRV(all_genres_rv);
        my_fav_genres_rv = findViewById(R.id.my_genres_list);
        setUpRV(my_fav_genres_rv);
        all_genres = new HashSet<>(intent.getParcelableExtra("allGenres"));
        my_fav_genres = new HashSet<>(intent.getParcelableExtra("favGenres"));
        all_genres.removeAll(my_fav_genres);
        all_genres_values = convertGenre2String(all_genres);
        my_fav_genres_values = convertGenre2String(my_fav_genres);
        adapterDeleteElementImpl();
        updateAdapters();
    };

    private List<String> convertGenre2String(Set<Genre> genres){
        return genres.stream()
                .map(Genre::getGenre)
                .collect(Collectors.toList());
    }

}
