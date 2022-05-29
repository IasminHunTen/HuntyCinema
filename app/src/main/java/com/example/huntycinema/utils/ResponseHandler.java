package com.example.huntycinema.utils;

public interface ResponseHandler<T>{
    void onResponse(T response);
    void onError(int code, String error);
}
