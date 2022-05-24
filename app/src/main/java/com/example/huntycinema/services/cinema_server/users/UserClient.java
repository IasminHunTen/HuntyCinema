package com.example.huntycinema.services.cinema_server.users;

import com.example.huntycinema.services.cinema_server.MyApiRequest;
import com.example.huntycinema.services.cinema_server.users.gson_mapper_objects.Credentials;
import com.example.huntycinema.services.cinema_server.users.gson_mapper_objects.UserToken;
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
        call.enqueue(new CallBackHandler<UserToken>(responseHandler));
    }

    public void login(String username, String password, String device_id, ResponseHandler<UserToken> responseHandler){
        Call<UserToken> call = usersApi.login(device_id, new Credentials(username, password));
        call.enqueue(new CallBackHandler<UserToken>(responseHandler));
    }
}
