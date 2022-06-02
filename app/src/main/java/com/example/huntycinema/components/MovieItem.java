package com.example.huntycinema.components;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.annotations.Nullable;

public class MovieItem implements Serializable {
    private String title, plot, date, imageUrl, trailerUrl, genres, directors, top_cast, year, run_time, imdb_rate, day, configuration, schedule_id;
    private Integer rom_rows, sits_left;
    private Double price;

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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getRom_rows() {
        return rom_rows;
    }

    public void setRom_rows(Integer rom_rows) {
        this.rom_rows = rom_rows;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public Integer getSits_left() {
        return sits_left;
    }

    public void setSits_left(Integer sits_left) {
        this.sits_left = sits_left;
    }

    public String getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(String schedule_id) {
        this.schedule_id = schedule_id;
    }
}