package com.example.huntycinema.utils;


import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DateUtils {

    private static List<String> month_names = Arrays.asList("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC");
    private static Calendar Today = Calendar.getInstance();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String make_date_string(int year, int month, int day){
         return month_names.get(month) + " " + String.valueOf(day) + " " + String.valueOf(year);
    }

    public static int thisYear(){
        return Today.get(Calendar.YEAR);
    }

    public static int thisMount(){
        return Today.get(Calendar.MONTH);
    }

    public static int thisDay(){
        return Today.get(Calendar.DAY_OF_MONTH);
    }

    public static String today(){
        return make_date_string(thisYear(), thisMount(), thisDay());
    }

    public static String formatDate(@Nullable String date){
        if (date==null)
            return simpleDateFormat.format(new Date());
        List<String> source = Arrays.asList(date.split(" "));
        Collections.rotate(source, 1);
        return source.stream()
                .map(el -> (month_names.contains(el))? String.valueOf(month_names.indexOf(el)+1) : el)
                .collect(Collectors.joining("-"));

    }

    public static String formatTime(Integer... values){
        return Arrays.asList(values).stream()
                .map(value -> value < 10 ? "0"+String.valueOf(value):String.valueOf(value))
                .collect(Collectors.joining(":"));
    }

}
