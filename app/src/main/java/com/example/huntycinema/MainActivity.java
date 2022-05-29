package com.example.huntycinema;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huntycinema.components.MovieItem;
import com.example.huntycinema.components.MovieItemFetcher;
import com.example.huntycinema.components.reyclerAdapter.MovieAdapter;
import com.example.huntycinema.localstorage.DataStorageSingleton;
import com.example.huntycinema.services.cinema_server.users.authentication.UserClient;
import com.example.huntycinema.services.cinema_server.users.authentication.UserToken;
import com.example.huntycinema.utils.DateUtils;
import com.example.huntycinema.utils.ResponseHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Loading state: ";
    private ArrayList<MovieItem> moviesList = new ArrayList<>();
    private RecyclerView movieRecyclerView;
    private ProgressBar progressBar;
    private Button date_button;
    private ImageButton my_account;
    private DatePickerDialog datePickerDialog;
    private RecyclerView.LayoutManager layoutManager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        movieRecyclerView = findViewById(R.id.movies_list);
        progressBar = findViewById(R.id.loading_bar);
        date_button = findViewById(R.id.date_picker_button);
        movieRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        movieRecyclerView.setLayoutManager(layoutManager);
        initDatePicker();
        go2MyAccount();
        if(!DataStorageSingleton.isStateLoaded()){
            @SuppressLint("HardwareIds") String device_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
            new UserClient().getToken(device_id, new ResponseHandler<UserToken>() {
                @Override
                public void onResponse(UserToken response) {
                    new DataStorageSingleton(response.getToken(), device_id);
                    new UpdateStorage().execute(DateUtils.formatDate(null));
                }

                @Override
                public void onError(int code, String error) {
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void initDatePicker(){
        date_button.setText(DateUtils.today());
        datePickerDialog = new DatePickerDialog(MainActivity.this, AlertDialog.THEME_HOLO_DARK,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String oldDate = (String) date_button.getText();
                        String newDate = DateUtils.make_date_string(year, month, day);
                        if(!oldDate.equals(newDate)) {
                            date_button.setText(DateUtils.make_date_string(year, month, day));
                            new UpdateStorage().execute(DateUtils.formatDate(newDate));
                        }
                    }
                }, DateUtils.thisYear(), DateUtils.thisMount(), DateUtils.thisDay());

        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldDate = (String) date_button.getText();
                datePickerDialog.show();
                String newDate = (String) date_button.getText();
                if (!newDate.equals(oldDate))
                    new UpdateStorage().execute(DateUtils.formatDate(newDate));
            }
        });
    }

    private void go2MyAccount(){
        my_account = (ImageButton) findViewById(R.id.my_account_main);
        my_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DataStorageSingleton.getToken() != null)
                    startActivity(new Intent(getApplicationContext(), MyAccountActivity.class));
                else {
                    DataStorageSingleton.setGo2account(true);
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });
    }



    private class UpdateStorage extends AsyncTask<String, Void, List<MovieItem>>{

        private MovieItemFetcher movieItemFetcher;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            movieItemFetcher = new MovieItemFetcher();
            date_button.setClickable(false);
            progressBar.setVisibility(View.VISIBLE);
            movieRecyclerView.setVisibility(View.INVISIBLE);
        }

        @Override
        protected List<MovieItem> doInBackground(String... dates) {
            movieItemFetcher.fetch(dates[0], MainActivity.this);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(!movieItemFetcher.getLoadState()) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return movieItemFetcher.get();
        }

        @Override
        protected void onPostExecute(List<MovieItem> movieItemList) {
            super.onPostExecute(movieItemList);
            MovieAdapter movieAdapter = new MovieAdapter(movieItemList, MainActivity.this);
            movieRecyclerView.setAdapter(movieAdapter);
            movieRecyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            date_button.setClickable(true);
        }

    }

}