package com.example.huntycinema.services.cinema_server.schedule;

public class RefreshSchedule {
    private String configuration;
    private Integer sits_left;

    public RefreshSchedule() {
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
}
