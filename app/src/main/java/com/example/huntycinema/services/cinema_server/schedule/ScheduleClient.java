package com.example.huntycinema.services.cinema_server.schedule;

import androidx.annotation.Nullable;

import com.example.huntycinema.services.cinema_server.MyApiRequest;
import com.example.huntycinema.utils.CallBackHandler;
import com.example.huntycinema.utils.ResponseHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class ScheduleClient extends MyApiRequest {

    ScheduleAPI scheduleClient;
    public ScheduleClient(){
        scheduleClient = retrofit.create(ScheduleAPI.class);
    }

    public void getSchedule(@Nullable String date, ResponseHandler<List<Schedule>> responseHandler){
        Map<String, String> map = new HashMap<>();
        if (date != null)
            map.put("date", date);
        Call<List<Schedule>> call = scheduleClient.getSchedule(map);
        call.enqueue(new CallBackHandler<List<Schedule>>(responseHandler));
    }
}
