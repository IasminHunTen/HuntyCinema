package com.example.huntycinema.localstorage;



import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.huntycinema.components.MovieItem;
import com.example.huntycinema.services.cinema_server.MyApiRequest;
import com.example.huntycinema.services.cinema_server.movies.Movie;
import com.example.huntycinema.services.cinema_server.movies.MovieClient;
import com.example.huntycinema.services.cinema_server.schedule.Schedule;
import com.example.huntycinema.services.cinema_server.schedule.ScheduleClient;
import com.example.huntycinema.services.cinema_server.users.UserClient;
import com.example.huntycinema.services.cinema_server.users.gson_mapper_objects.UserToken;
import com.example.huntycinema.utils.ResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class DataStorageSingleton {

    private static String token;
    private static DataStorageSingleton instance;
    private static Context context;
    private Map<String, MovieItem> movieMap = new HashMap<>();
    private UserClient userClient;
    private MovieClient movieClient = new MovieClient();
    private ScheduleClient scheduleClient = new ScheduleClient();
    private Boolean loadState;

    private DataStorageSingleton(Context context){
        DataStorageSingleton.context = context;
        loadState = false;
    }

    public static synchronized DataStorageSingleton getInstance(Context context){
        if (instance == null)
            instance = new DataStorageSingleton(context);
        return instance;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        DataStorageSingleton.token = token;
    }


}
