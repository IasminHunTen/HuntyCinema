package com.example.huntycinema.utils;

import android.os.Build;
import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtils {

    public static void toggle_password_field(EditText editText){
        if(editText.getInputType() == (InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT))
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_CLASS_TEXT);
        else
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        editText.setSelection(editText.getText().toString().trim().length());
    }

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
