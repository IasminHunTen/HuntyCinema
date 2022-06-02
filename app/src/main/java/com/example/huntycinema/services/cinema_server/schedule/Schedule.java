package com.example.huntycinema.services.cinema_server.schedule;

public class Schedule {

    private String id, movie_id, sits_configuration, day;
    private Integer sits_left, hour, minute, room_raws;
    private Double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public Integer getRoom_raws() {
        return room_raws;
    }

    public void setRoom_raws(Integer room_raws) {
        this.room_raws = room_raws;
    }

    public String getSits_configuration() {
        return sits_configuration;
    }

    public void setSits_configuration(String sits_configuration) {
        this.sits_configuration = sits_configuration;
    }

    public Integer getSits_left() {
        return sits_left;
    }

    public void setSits_left(Integer sits_left) {
        this.sits_left = sits_left;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
