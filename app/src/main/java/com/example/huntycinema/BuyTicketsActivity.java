package com.example.huntycinema;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huntycinema.components.MovieItem;
import com.example.huntycinema.components.dialogs.ChooseCardDialog;
import com.example.huntycinema.components.dialogs.DialogCommunication;
import com.example.huntycinema.components.reyclerAdapter.CardAdapter;
import com.example.huntycinema.components.reyclerAdapter.OnDeleteAdapterItem;
import com.example.huntycinema.components.reyclerAdapter.SelectedPlaceAdapter;
import com.example.huntycinema.localstorage.DataStorageSingleton;
import com.example.huntycinema.services.cinema_server.schedule.RefreshSchedule;
import com.example.huntycinema.services.cinema_server.schedule.Schedule;
import com.example.huntycinema.services.cinema_server.schedule.ScheduleClient;
import com.example.huntycinema.services.cinema_server.users.cards.Card;
import com.example.huntycinema.services.cinema_server.users.cards.CardClient;
import com.example.huntycinema.services.cinema_server.users.tickets.Amount;
import com.example.huntycinema.services.cinema_server.users.tickets.TicketClient;
import com.example.huntycinema.services.cinema_server.users.tickets.TicketResp;
import com.example.huntycinema.utils.ResponseHandler;
import com.example.huntycinema.utils.StringUtils;
import com.example.huntycinema.utils.TicketsUtils;
import com.google.android.gms.common.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BuyTicketsActivity extends AppCompatActivity {
    private TextView movie_title, movie_date_time, movie_price, free_sits, no_tickets, line_validator, row_validator;
    private EditText line_selection, row_selection;
    private RecyclerView selected_places;
    private Button add_ticket, place_order;
    private ImageButton free_sits_map;
    private MovieItem movieItem;
    private Integer no_line;
    private List<String> stringList;
    private List<Integer> integerList;
    private TicketClient ticketClient;
    private CardClient cardClient;
    private ScheduleClient scheduleClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_tickets);
        movieItem = (MovieItem) getIntent().getSerializableExtra("movieItem");
        init();
    }

    private void init(){
        no_line = movieItem.getConfiguration().length() / movieItem.getRom_rows();
        stringList = new ArrayList<>();
        integerList = new ArrayList<>();
        ticketClient = new TicketClient();
        cardClient = new CardClient();
        scheduleClient = new ScheduleClient();

        movie_title = findViewById(R.id.movie_name_buy_ticket);
        movie_title.setText(movieItem.getTitle());

        movie_date_time = findViewById(R.id.date_time);
        movie_date_time.setText(movieItem.getDay());

        movie_price = findViewById(R.id.ticket_price);
        movie_price.setText(String.valueOf(movieItem.getPrice()));

        free_sits = findViewById(R.id.free_sits);
        free_sits.setText(String.valueOf(movieItem.getSits_left()));

        no_tickets = findViewById(R.id.no_tickets);
        no_tickets.setText(String.valueOf(0));

        line_selection = findViewById(R.id.line_value);
        line_validator = findViewById(R.id.line_validator);
        line_selection.setHint("line index, max value: " + String.valueOf(no_line));
        line_selection.addTextChangedListener(et_watcher(line_validator));

        row_selection = findViewById(R.id.row_value);
        row_validator = findViewById(R.id.row_validator);
        row_selection.setHint("row index, max value: " + String.valueOf(movieItem.getRom_rows()));
        row_selection.addTextChangedListener(et_watcher(row_validator));

        selected_places = findViewById(R.id.selected_places);
        selected_places.setLayoutManager(new LinearLayoutManager(this));
        selected_places.setHasFixedSize(true);
        selected_places.setAdapter(new SelectedPlaceAdapter(this, stringList));
        SelectedPlaceAdapter.setOnDeleteAdapterItem(class_implementation());

        add_ticket = findViewById(R.id.add_ticket);
        addTicket();

        place_order = findViewById(R.id.place_order);
        placeOrder();

    }

    private View.OnClickListener et_click_listener(TextView tv){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setVisibility(View.INVISIBLE);
            }
        };
    }

    private TextWatcher et_watcher(TextView tv){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    private void addTicket(){

      add_ticket.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String line_index = line_selection.getText().toString().trim();
              String row_index = row_selection.getText().toString().trim();

              if(line_index.isEmpty()){
                  line_validator.setText(R.string.empty_field);
                  line_validator.setVisibility(View.VISIBLE);
                  return;
              }

              if(row_index.isEmpty()){
                  row_validator.setText(R.string.empty_field);
                  row_validator.setVisibility(View.VISIBLE);
                  return;
              }

              String line_index_validation = TicketsUtils.validateSitsSelected(line_index, no_line);
              if(line_index_validation!=null){
                  line_validator.setText(line_index_validation);
                  line_validator.setVisibility(View.VISIBLE);
                  return;
              }

              String row_index_validation = TicketsUtils.validateSitsSelected(row_index, movieItem.getRom_rows());
              if(row_index_validation!=null){
                  row_validator.setText(row_index_validation);
                  row_validator.setVisibility(View.VISIBLE);
                  return;
              }

              stringList.add(line_index + " : " + row_index);
              selected_places.setAdapter(new SelectedPlaceAdapter(BuyTicketsActivity.this, stringList));
              Integer aux = TicketsUtils.place_counter(line_index) * TicketsUtils.place_counter(row_index);
              integerList.add(aux);
              update_no_of_tickets(aux);
          }
      });
    }

    private void update_no_of_tickets(int value){
        int current_value = Integer.parseInt(no_tickets.getText().toString());
        no_tickets.setText(String.valueOf(current_value + value));
    }

    private OnDeleteAdapterItem<Integer> class_implementation(){
        return new OnDeleteAdapterItem<Integer>() {
            @Override
            public void onDelete(Integer value) {
                stringList.remove(value.intValue());
                update_no_of_tickets(-1 * integerList.remove(value.intValue()));
                selected_places.setAdapter(new SelectedPlaceAdapter(BuyTicketsActivity.this, stringList));
            }
        };
    }



    private void placeOrder(){
        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stringList.size() == 0)
                    return;
                String ticket_content = TicketsUtils.prepare_ticket_order(stringList, movieItem.getRom_rows());
                if (ticket_content.contains("overlap")){
                    Toast.makeText(getApplicationContext(), ticket_content, Toast.LENGTH_LONG).show();
                    return;
                }
                ticketClient.createTicket(DataStorageSingleton.getToken(), movieItem.getSchedule_id(), ticket_content, new ResponseHandler<TicketResp>() {
                    @Override
                    public void onResponse(TicketResp response) {
                        List<Card> cards = DataStorageSingleton.getMyCards();
                        if(cards == null){
                            cardClient.fetch_credit_cards(DataStorageSingleton.getToken(), new ResponseHandler<List<Card>>() {
                                @Override
                                public void onResponse(List<Card> response2) {
                                    manage_card(mapCards2CardsNumber(response2), response.getTickets_id(), response.getAmount());
                                }

                                @Override
                                public void onError(int code, String error) {

                                }
                            });
                        }else{
                            manage_card(mapCards2CardsNumber(cards), response.getTickets_id(), response.getAmount());
                        }
                    }

                    @Override
                    public void onError(int code, String error) {
                        if (code == 400){
                            scheduleClient.refreshSchedule(movieItem.getSchedule_id(), new ResponseHandler<RefreshSchedule>() {
                                @Override
                                public void onResponse(RefreshSchedule response) {
                                    movieItem.setConfiguration(response.getConfiguration());
                                    movieItem.setSits_left(response.getSits_left());
                                }

                                @Override
                                public void onError(int code, String error) {
                                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void manage_card(List<String> cards, String ticket_id, Double amount){
        if (cards.size() == 0){
            Toast.makeText(getApplicationContext(), "No card found for this account, please go to your account and add a card", Toast.LENGTH_LONG).show();
            return;
        }
        ChooseCardDialog chooseCardDialog = new ChooseCardDialog(cards, new DialogCommunication() {
            @Override
            public void communicate(String... values) {
                cardClient.pay(DataStorageSingleton.getToken(), values[0], amount, new ResponseHandler<Void>() {
                    @Override
                    public void onResponse(Void response) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(int code, String error) {
                        if(code==400){
                            Toast.makeText(getApplicationContext(), "Insufficient founds", Toast.LENGTH_LONG).show();
                            ticketClient.deleteTicket(DataStorageSingleton.getToken(), ticket_id, new ResponseHandler<Amount>() {
                                @Override
                                public void onResponse(Amount response) {
                                    finish();
                                }

                                @Override
                                public void onError(int code, String error) {
                                    finish();
                                }
                            });
                        }
                    }
                });
            }
        });
        chooseCardDialog.show(getSupportFragmentManager(), "choose card");
        chooseCardDialog.setCancelListener((android.app.AlertDialog) chooseCardDialog.getDialog());
    }

    private List<String> mapCards2CardsNumber(List<Card> cards){
        return cards.stream()
                .map(Card::getCard_number)
                .collect(Collectors.toList());
    }
}

