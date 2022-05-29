package com.example.huntycinema.utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallBackHandler<T> implements Callback<T>{
    private ResponseHandler<T> responseHandler;

    public CallBackHandler(ResponseHandler<T> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()){
            responseHandler.onError(response.code(), "code: " + response.code() +
                    "\nReason: " + response.message());
            return;
        }
        responseHandler.onResponse(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        responseHandler.onError(500, t.getMessage());
    }
}
