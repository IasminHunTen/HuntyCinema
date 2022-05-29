package com.example.huntycinema.services.cinema_server.genres;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GenreApi {
    @GET("genres")
    Call<List<Genre>> getGenres();
}
