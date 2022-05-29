package com.example.huntycinema.services.cinema_server.users.authentication;

import com.example.huntycinema.services.cinema_server.MyApiRequest;
import com.example.huntycinema.services.cinema_server.users.UsersApi;
import com.example.huntycinema.utils.CallBackHandler;
import com.example.huntycinema.utils.ResponseHandler;

import retrofit2.Call;

public class UserClient extends MyApiRequest {
    private UsersApi usersApi;
    public UserClient() {
        usersApi = retrofit.create(UsersApi.class);
    }

    public void getToken(String device_id, ResponseHandler<UserToken> responseHandler){
        Call<UserToken> call = usersApi.getToken(device_id);
        call.enqueue(new CallBackHandler<>(responseHandler));
    }

    public void login(String username, String password, String device_id, ResponseHandler<UserToken> responseHandler){
        Call<UserToken> call = usersApi.login(device_id, new Credentials(username, password));
        call.enqueue(new CallBackHandler<>(responseHandler));
    }

    public void register(String email, String username, String password, String device_id, ResponseHandler<UserToken> responseHandler){
        Call<UserToken> call = usersApi.login(device_id, new CredentialsWithEmail(username, password, email));
        call.enqueue(new CallBackHandler<>(responseHandler));
    }
}
