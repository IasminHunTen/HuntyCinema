package com.example.huntycinema.services.cinema_server.users.authentication;

public class ResetPasswordBody {
    private String username, new_password, validation_code;

    public ResetPasswordBody(String username, String new_password, String validation_code) {
        this.username = username;
        this.new_password = new_password;
        this.validation_code = validation_code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getValidation_code() {
        return validation_code;
    }

    public void setValidation_code(String validation_code) {
        this.validation_code = validation_code;
    }
}
