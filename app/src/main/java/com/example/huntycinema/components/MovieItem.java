package com.example.huntycinema.components;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.annotations.Nullable;

public class MovieItem implements Serializable {
    private String title, plot, date, imageUrl, trailerUrl, genres, directors, top_cast, year, run_time, imdb_rate;


    public MovieItem() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getTop_cast() {
        return top_cast;
    }

    public void setTop_cast(String top_cast) {
        this.top_cast = top_cast;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRun_time() {
        return run_time;
    }

    public void setRun_time(String run_time) {
        this.run_time = run_time;
    }

    public String getImdb_rate() {
        return imdb_rate;
    }

    public void setImdb_rate(String imdb_rate) {
        this.imdb_rate = imdb_rate;
    }

    public String prepareHeader(){
       return Arrays.asList(title, genres, run_time, year).stream().collect(Collectors.joining("-"));
    }

    @Override
    public String toString() {
        return "MovieItem{" +
                "title='" + title + '\'' +
                ", plot='" + plot + '\'' +
                ", date='" + date + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", trailerUrl='" + trailerUrl + '\'' +
                ", genres='" + genres + '\'' +
                ", directors='" + directors + '\'' +
                ", top_cast='" + top_cast + '\'' +
                ", year='" + year + '\'' +
                ", run_time='" + run_time + '\'' +
                ", imdb_rate='" + imdb_rate + '\'' +
                '}';
    }
}