package com.example.huntycinema.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class StringUtils {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean containsUpperCase(String inspect) {
        return inspect.chars().mapToObj(x -> (char) x).anyMatch(Character::isUpperCase);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean containsLowerCase(String inspect) {
        return inspect.chars().mapToObj(x -> (char) x).anyMatch(Character::isLowerCase);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean containsWhiteSpace(String inspect) {
        return inspect.chars().mapToObj(x -> (char) x).anyMatch(Character::isWhitespace);
    }
}
