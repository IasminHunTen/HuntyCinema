package com.example.huntycinema.localstorage;


import com.example.huntycinema.services.cinema_server.users.cards.Card;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.Nullable;

public class DataStorageSingleton {

    private static String token;
    private static String device_id;
    private static boolean loadState;
    private static boolean go2account;
    private static List<Card> myCards;

    public DataStorageSingleton(@Nullable String token, String device_id) {
        myCards = new ArrayList<>();
        DataStorageSingleton.token = token;
        DataStorageSingleton.device_id = device_id;
        DataStorageSingleton.loadState = true;
    }

    public static void setToken(String token){
        DataStorageSingleton.token = token;
    }

    public static String getToken() {
        return token;
    }

    public static String getDevice_id() {
        return device_id;
    }

    public static boolean isStateLoaded(){
        return loadState;
    }

    public static boolean isGo2account() {
        return go2account;
    }

    public static List<Card> getMyCards() {
        return myCards;
    }

    public static void setMyCards(List<Card> myCards) {
        DataStorageSingleton.myCards = myCards;
    }

    public static void setGo2account(boolean go2account) {
        DataStorageSingleton.go2account = go2account;
    }
}
