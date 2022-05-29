package com.example.huntycinema.services.cinema_server.users;

import com.example.huntycinema.services.cinema_server.users.authentication.Credentials;
import com.example.huntycinema.services.cinema_server.users.authentication.CredentialsWithEmail;
import com.example.huntycinema.services.cinema_server.users.authentication.UserToken;
import com.example.huntycinema.services.cinema_server.users.cards.Card;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Query;

public interface UsersApi {

    @GET("users/token")
    Call<UserToken> getToken(@Query("device_id") String device_id);

    @POST("users/login")
    Call<UserToken> login(@Query("device_id") String device_id,
                          @Body Credentials credentials);

    @POST("users/register")
    Call<UserToken> login(@Query("device_id") String device_id,
                          @Body CredentialsWithEmail credentials);

    @POST("users/credit_card")
    Call<Void> generate_credit_card(@Query("token") String token);

    @GET("users/credit_card")
    Call<List<Card>> fetch_credit_card(@Query("token") String token);

    @DELETE("users/credit_card")
    Call<Void> delete_credit_card(@Query("token") String token, @Query("card_number") String card_number);

}