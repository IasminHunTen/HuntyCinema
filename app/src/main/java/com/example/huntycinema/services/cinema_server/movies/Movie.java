package com.example.huntycinema.services.cinema_server.movies;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String id, tag, title, plot, poster, trailer;
    private int year, run_time;
    private double imdb_rate;
    private List<String> genres = new ArrayList<>();
    private List<String> directors = new ArrayList<>();
    private List<String> top_cast = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRun_time() {
        return run_time;
    }

    public void setRun_time(int run_time) {
        this.run_time = run_time;
    }

    public double getImdb_rate() {
        return imdb_rate;
    }

    public void setImdb_rate(double imdb_rate) {
        this.imdb_rate = imdb_rate;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getTop_cast() {
        return top_cast;
    }

    public void setTop_cast(List<String> top_cast) {
        this.top_cast = top_cast;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", plot='" + plot + '\'' +
                ", poster='" + poster + '\'' +
                ", trailer='" + trailer + '\'' +
                ", year=" + year +
                ", run_time=" + run_time +
                ", imdb_rate=" + imdb_rate +
                ", genres=" + genres +
                ", directors=" + directors +
                ", top_cast=" + top_cast +
                '}';
    }
}
