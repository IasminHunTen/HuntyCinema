package com.example.huntycinema.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TicketsUtils {
    private static final Pattern sits_selections = Pattern.compile("(\\d+-)?\\d+");
    private static List<Integer> overlap_checker;

    public static String validateSitsSelected(String selection, int max_value){
        if(!sits_selections.matcher(selection).matches())
            return "bad pattern";
        List<Integer> integerList = Arrays.asList(selection.split("-")).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        if(integerList.stream().anyMatch(v -> v > max_value))
            return "too large index";
        if (integerList.size() > 1)
            if(integerList.get(1) < integerList.get(0))
                return "number after dash must be bigger that the one before";
        return null;
    }

    public static int place_counter(String place){
        if(!place.contains("-"))
            return 1;
        List<Integer> aux = Arrays.asList(place.split("-")).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return aux.get(1) - aux.get(0) + 1;
    }

    private static boolean pto(String[] indexes, int row){
        List<Integer> line_indexes = Arrays.asList(indexes[0].split("-")).stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .map(v -> v - 1)
                .collect(Collectors.toList());
        List<Integer> row_indexes = Arrays.asList(indexes[1].split("-")).stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Integer aux;
        for (int i = line_indexes.get(0); i <= line_indexes.get(1) ; i++) {
            for (int j = row_indexes.get(0); j<= row_indexes.get(1); j++){
                aux = i * row + j;
                if(overlap_checker.contains(aux))
                    return false;
                overlap_checker.add(aux);
            }
        }
        return  true;
    }


    public static String prepare_ticket_order(List<String> tickets, int row){
        if(overlap_checker==null)
            overlap_checker = new ArrayList<>();
        else
            overlap_checker.clear();
        for(String ticket: tickets){
            if(!pto(ticket.split(":"), row))
                return ticket + "overlap with previous tickets";
        }
        return overlap_checker.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

}
