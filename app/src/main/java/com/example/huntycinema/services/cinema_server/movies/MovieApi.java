package com.example.huntycinema.services.cinema_server.movies;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MovieApi {
    @GET("movies/")
    Call<List<Movie>> getMovies(@QueryMap Map<String, String> ids);
}
