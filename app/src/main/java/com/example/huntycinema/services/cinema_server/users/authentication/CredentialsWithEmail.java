package com.example.huntycinema.services.cinema_server.users.authentication;

public class CredentialsWithEmail extends Credentials{
    private String email;
    public CredentialsWithEmail(String username, String password, String email) {
        super(username, password);
        this.email = email;
    }
}
