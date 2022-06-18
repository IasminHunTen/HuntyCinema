package com.example.huntycinema.services.cinema_server.genres;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Genre implements Serializable {
    private String id, genre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre1 = (Genre) o;
        return Objects.equals(genre, genre1.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre);
    }

    public static List<Genre> differenceGenres(List<Genre> left, List<Genre> right){
        Set<Genre> left_set = new HashSet<>(left);
        Set<Genre> right_set = new HashSet<>(right);
        left_set.removeAll(right_set);
        return new ArrayList<>(left_set);
    }
}
