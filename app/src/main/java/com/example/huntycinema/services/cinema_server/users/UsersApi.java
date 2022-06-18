package com.example.huntycinema.services.cinema_server.users;

import com.example.huntycinema.services.cinema_server.genres.Genre;
import com.example.huntycinema.services.cinema_server.users.authentication.Credentials;
import com.example.huntycinema.services.cinema_server.users.authentication.CredentialsWithEmail;
import com.example.huntycinema.services.cinema_server.users.authentication.ResetPasswordBody;
import com.example.huntycinema.services.cinema_server.users.authentication.UserToken;
import com.example.huntycinema.services.cinema_server.users.cards.Card;
import com.example.huntycinema.services.cinema_server.users.cards.UpdateCard;
import com.example.huntycinema.services.cinema_server.users.tickets.Amount;
import com.example.huntycinema.services.cinema_server.users.tickets.TicketId;
import com.example.huntycinema.services.cinema_server.users.tickets.TicketReq;
import com.example.huntycinema.services.cinema_server.users.tickets.TicketResp;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.PUT;
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

    @POST("users/reset-password")
    Call<Void> reset_password(@Query("username") String email);

    @PUT("users/reset-password")
    Call<Void> update_password(@Body ResetPasswordBody resetPasswordBody);

    @POST("users/credit_card")
    Call<Void> generate_credit_card(@Query("token") String token);

    @GET("users/credit_card")
    Call<List<Card>> fetch_credit_card(@Query("token") String token);

    @PUT("users/credit_card")
    Call<Void> update_card(@Query("token") String token, UpdateCard updateCard);

    @DELETE("users/credit_card")
    Call<Void> delete_credit_card(@Query("token") String token, @Query("card_number") String card_number);

    @POST("users/tickets")
    Call<TicketResp> create_ticket(@Query("token") String token, @Body TicketReq req);

    @DELETE("users/tickets")
    Call<Amount> delete_ticket(@Query("token") String token, TicketId ticketId);

    @GET("users/user_favorite_genres")
    Call<List<Genre>> get_fav_genres(@Query("token") String token);

    @PUT("users/user_favorite_genres")
    Call<Void> update_fav_genres(@Query("token") String token, @Query("genre_ids") String genre_ids);
}