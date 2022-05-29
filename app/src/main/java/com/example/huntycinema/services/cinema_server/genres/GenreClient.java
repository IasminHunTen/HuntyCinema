package com.example.huntycinema.services.cinema_server.genres;

import com.example.huntycinema.services.cinema_server.MyApiRequest;
import com.example.huntycinema.utils.CallBackHandler;
import com.example.huntycinema.utils.ResponseHandler;

import java.util.List;

import retrofit2.Call;

public class GenreClient extends MyApiRequest {
    private final GenreApi genreClient;

    public GenreClient(){
        genreClient = retrofit.create(GenreApi.class);
    }

    public void getGenres(ResponseHandler<List<Genre>> responseHandler){
        Call<List<Genre>> call = genreClient.getGenres();
        call.enqueue(new CallBackHandler<>(responseHandler));
    }



}
