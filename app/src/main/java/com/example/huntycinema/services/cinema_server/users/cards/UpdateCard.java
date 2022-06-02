package com.example.huntycinema.services.cinema_server.users.cards;

public class UpdateCard {
    private String card_number;
    private Double amount;

    public UpdateCard(String card_number, Double amount) {
        this.card_number = card_number;
        this.amount = amount;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
