package com.example.huntycinema.services.cinema_server.schedule;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ScheduleAPI {

    @GET("schedule/")
    Call<List<Schedule>> getSchedule(@QueryMap Map<String, String> date);

    @GET("schedule/refresh_schedule")
    Call<RefreshSchedule> refreshSchedule(@Query("id") String id);
}
