package com.example.huntycinema.services.cinema_server.users.cards;

import com.example.huntycinema.services.cinema_server.MyApiRequest;
import com.example.huntycinema.services.cinema_server.users.UsersApi;
import com.example.huntycinema.utils.CallBackHandler;
import com.example.huntycinema.utils.ResponseHandler;

import java.util.List;

import retrofit2.Call;

public class CardClient extends MyApiRequest {
    private final UsersApi cardClient;

    public CardClient(){
        cardClient = retrofit.create(UsersApi.class);
    }

    public void generate_card(String token, ResponseHandler<Void> responseHandler){
        Call<Void> call = cardClient.generate_credit_card(token);
        call.enqueue(new CallBackHandler<>(responseHandler));
    }

    public void delete_card(String token, String card_number, ResponseHandler<Void> responseHandler){
        Call<Void> call = cardClient.delete_credit_card(token, card_number);
        call.enqueue(new CallBackHandler<>(responseHandler));
    }

    public void fetch_credit_cards(String token, ResponseHandler<List<Card>> responseHandler){
        Call<List<Card>> call = cardClient.fetch_credit_card(token);
        call.enqueue(new CallBackHandler<>(responseHandler));
    }

    public void pay(String token, String card_number, Double amount, ResponseHandler<Void> responseHandler){
        Call<Void> call = cardClient.update_card(token, new UpdateCard(card_number, amount));
        call.enqueue(new CallBackHandler<>(responseHandler));
    }
}
