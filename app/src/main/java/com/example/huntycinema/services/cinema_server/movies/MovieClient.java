package com.example.huntycinema.services.cinema_server.movies;

import androidx.annotation.Nullable;

import com.example.huntycinema.services.cinema_server.MyApiRequest;
import com.example.huntycinema.utils.CallBackHandler;
import com.example.huntycinema.utils.ResponseHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MovieClient extends MyApiRequest {

    private MovieApi movieClient;

    public MovieClient() {
        movieClient = retrofit.create(MovieApi.class);
    }

    public void getMovies(@Nullable String ids, ResponseHandler<List<Movie>> responseRetrofit){
        Map<String, String> ids_param = new HashMap<>();
        if(ids != null){
            ids_param.put("ids", ids);
        }
        Call<List<Movie>> call = movieClient.getMovies(ids_param);
        call.enqueue(new CallBackHandler<List<Movie>>(responseRetrofit));
    }
}
