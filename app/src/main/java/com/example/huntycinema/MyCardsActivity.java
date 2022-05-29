package com.example.huntycinema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huntycinema.components.reyclerAdapter.CardAdapter;
import com.example.huntycinema.components.reyclerAdapter.OnDeleteAdapterItem;
import com.example.huntycinema.localstorage.DataStorageSingleton;
import com.example.huntycinema.services.cinema_server.users.cards.Card;
import com.example.huntycinema.services.cinema_server.users.cards.CardClient;
import com.example.huntycinema.utils.ResponseHandler;

import java.util.ArrayList;
import java.util.List;

public class MyCardsActivity extends AppCompatActivity {
    private TextView textView;
    private RecyclerView recyclerView;
    private Button add_card;
    private final CardClient cardClient = new CardClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cards);
        init();
        addCard();
        removeCard();
    }

    private void init(){
        textView = findViewById(R.id.no_cards_yet);
        add_card = findViewById(R.id.add_card_btn);
        recyclerView = (RecyclerView) findViewById(R.id.cards_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CardAdapter(this, "hunty", DataStorageSingleton.getMyCards()));
        if(DataStorageSingleton.getMyCards().size()>0) {
            textView.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    private void addCard(){
        add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardClient.generate_card(DataStorageSingleton.getToken(), new ResponseHandler<Void>() {
                    @Override
                    public void onResponse(Void response) {
                        cardClient.fetch_credit_cards(DataStorageSingleton.getToken(), new ResponseHandler<List<Card>>() {
                            @Override
                            public void onResponse(List<Card> response) {
                                DataStorageSingleton.setMyCards(response);
                                updateView();
                            }

                            @Override
                            public void onError(int code, String error) {
                                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onError(int code, String error) {
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void removeCard(){
        CardAdapter.setOnDeleteAdapterItem(new OnDeleteAdapterItem() {
            @Override
            public void onDelete(String id) {
                cardClient.delete_card(DataStorageSingleton.getToken(), id, new ResponseHandler<Void>() {
                    @Override
                    public void onResponse(Void response) {
                        cardClient.fetch_credit_cards(DataStorageSingleton.getToken(), new ResponseHandler<List<Card>>() {
                            @Override
                            public void onResponse(List<Card> response) {
                                DataStorageSingleton.setMyCards(response);
                                updateView();
                            }

                            @Override
                            public void onError(int code, String error) {
                                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onError(int code, String error) {
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void updateView(){
        if(DataStorageSingleton.getMyCards().size()==0) {
            recyclerView.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
            return;
        }
        recyclerView.setAdapter(new CardAdapter(this, "Hunty",DataStorageSingleton.getMyCards()));
        if(textView.getVisibility() == View.VISIBLE){
            textView.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

}