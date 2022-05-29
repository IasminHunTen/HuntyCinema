package com.example.huntycinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huntycinema.localstorage.DataStorageSingleton;
import com.example.huntycinema.services.cinema_server.users.cards.Card;
import com.example.huntycinema.services.cinema_server.users.cards.CardClient;
import com.example.huntycinema.utils.ResponseHandler;

import java.io.Serializable;
import java.util.List;

public class MyAccountActivity extends AppCompatActivity {
    Button logout, my_cards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        my_cards = (Button) findViewById(R.id.my_credit_cards);
        show_credit_cards();
    }

    private void show_credit_cards(){
        my_cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CardClient().fetch_credit_cards(DataStorageSingleton.getToken(), new ResponseHandler<List<Card>>() {
                    @Override
                    public void onResponse(List<Card> response) {
                        DataStorageSingleton.setMyCards(response);
                        startActivity(new Intent(getApplicationContext(), MyCardsActivity.class));
                    }

                    @Override
                    public void onError(int code, String error) {
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}