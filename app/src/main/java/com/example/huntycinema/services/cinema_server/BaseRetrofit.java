package com.example.huntycinema.services.cinema_server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofit {
    private static Retrofit instance;

    private BaseRetrofit(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Gson gson = new GsonBuilder().serializeNulls().create();

        instance = new Retrofit.Builder()
                .baseUrl("https://cinema-app-server.herokuapp.com/api/doc/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static Retrofit getInstance(){
        if (instance == null)
            new BaseRetrofit();
        return instance;
    }
}
