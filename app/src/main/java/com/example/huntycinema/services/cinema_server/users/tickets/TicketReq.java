package com.example.huntycinema.services.cinema_server.users.tickets;

public class TicketReq {
    private String schedule_id, sits_configuration;

    public TicketReq(String schedule_id, String sits_configuration) {
        this.schedule_id = schedule_id;
        this.sits_configuration = sits_configuration;
    }

    public String getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(String schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getSits_configuration() {
        return sits_configuration;
    }

    public void setSits_configuration(String sits_configuration) {
        this.sits_configuration = sits_configuration;
    }
}