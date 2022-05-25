package com.example.huntycinema.components;

import android.content.Context;
import android.widget.Toast;

import com.example.huntycinema.services.cinema_server.movies.Movie;
import com.example.huntycinema.services.cinema_server.movies.MovieClient;
import com.example.huntycinema.services.cinema_server.schedule.Schedule;
import com.example.huntycinema.services.cinema_server.schedule.ScheduleClient;
import com.example.huntycinema.utils.DateUtils;
import com.example.huntycinema.utils.ResponseHandler;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MovieItemFetcher {
    private Boolean loadState;
    private Map<String, Schedule> scheduleMap;
    private Map<String, Movie> movieMap;
    private List<MovieItem> movieItemList;

    public MovieItemFetcher() {
        loadState = false;
        scheduleMap = new HashMap<>();
        movieMap = new HashMap<>();
        movieItemList = new ArrayList<>();
    }

    private String moviesIds(List<Schedule> schedules){
        return schedules.stream()
                .map(Schedule::getMovie_id)
                .collect(Collectors.toSet()).stream()
                .collect(Collectors.joining(" "));
    }

    public void fetch(String date, Context context){
        new ScheduleClient().getSchedule(date, new ResponseHandler<List<Schedule>>() {
            @Override
            public void onResponse(List<Schedule> response) {
                if (response.size() == 0){
                    loadState=true;
                    return;
                }
                scheduleMap = response.stream()
                        .collect(Collectors.toMap(
                                Schedule::getId,
                                Function.identity()
                        ));
                new MovieClient().getMovies(moviesIds(response), new ResponseHandler<List<Movie>>() {
                    @Override
                    public void onResponse(List<Movie> response) {
                        movieMap = response.stream()
                                .collect(Collectors.toMap(
                                        Movie::getId,
                                        Function.identity()
                                ));
                        loadState = true;
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(context, error, Toast.LENGTH_LONG);
                        loadState = true;
                    }
                });
            }

            @Override
            public void onError(String error) {
                Toast.makeText(context, error, Toast.LENGTH_LONG);
                loadState = true;
            }
        });
    }

    private MovieItem prepareMovieItem(Schedule schedule){
        MovieItem movieItem = new MovieItem();
        movieItem.setDate(DateUtils.formatTime(schedule.getHour(), schedule.getMinute()));

        Movie movie = movieMap.get(schedule.getMovie_id());
        movieItem.setTitle(movie.getTitle());
        movieItem.setPlot(movie.getPlot());
        movieItem.setImageUrl(movie.getPoster());
        movieItem.setTrailerUrl(movie.getTrailer());
        movieItem.setGenres(
                movie.getGenres().stream()
                        .map(el->el.substring(0, 1).toUpperCase() + el.substring(1))
                        .collect(Collectors.joining("/")));
        movieItem.setTop_cast(movie.getTop_cast().stream().collect(Collectors.joining(", ")));
        movieItem.setDirectors(movie.getDirectors().stream().collect(Collectors.joining(",")));
        movieItem.setYear(String.valueOf(movie.getYear()));
        movieItem.setRun_time(
                String.valueOf(movie.getRun_time() / 60) + "h " +
                String.valueOf(movie.getRun_time() % 60) + "min");
        movieItem.setImdb_rate(movie.getImdb_rate() == null ?
                null : String.valueOf(movie.getImdb_rate()));
        return movieItem;
    }

    public List<MovieItem> get(){
        return scheduleMap.values().stream()
                .map(this::prepareMovieItem)
                .collect(Collectors.toList());
    }

    public Boolean getLoadState() {
        return loadState;
    }
}