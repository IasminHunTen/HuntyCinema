package com.example.huntycinema.services.cinema_server.users.cards;

import java.io.Serializable;

public class Card{

    private String card_number;
    private Integer expire_year, expire_month;
    private Double sold;

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public Integer getExpire_year() {
        return expire_year;
    }

    public void setExpire_year(Integer expire_year) {
        this.expire_year = expire_year;
    }

    public Integer getExpire_month() {
        return expire_month;
    }

    public void setExpire_month(Integer expire_month) {
        this.expire_month = expire_month;
    }

    public Double getSold() {
        return sold;
    }

    public void setSold(Double sold) {
        this.sold = sold;
    }
}
