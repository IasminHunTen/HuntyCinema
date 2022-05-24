package com.example.huntycinema.services.cinema_server;

import retrofit2.Retrofit;

public class MyApiRequest {
    protected Retrofit retrofit;

    protected MyApiRequest() {
        retrofit = BaseRetrofit.getInstance();
    }

}

