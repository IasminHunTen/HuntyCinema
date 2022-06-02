package com.example.huntycinema.services.cinema_server.users.tickets;

public class TicketResp {
    private String tickets_id;
    private Double amount;

    public TicketResp() {
    }

    public String getTickets_id() {
        return tickets_id;
    }

    public void setTickets_id(String tickets_id) {
        this.tickets_id = tickets_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
