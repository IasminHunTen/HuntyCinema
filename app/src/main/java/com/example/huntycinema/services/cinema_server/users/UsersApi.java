package com.example.huntycinema.services.cinema_server.users;

import com.example.huntycinema.services.cinema_server.users.gson_mapper_objects.Credentials;
import com.example.huntycinema.services.cinema_server.users.gson_mapper_objects.UserToken;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Query;

public interface UsersApi {

    @GET("users/token/")
    Call<UserToken> getToken(@Query("device_id") String device_id);

    @POST("users/login/")
    Call<UserToken> login(@Query("device_id") String device_id,
                          @Body Credentials credentials);

}