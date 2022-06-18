package com.example.huntycinema.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huntycinema.R;
import com.example.huntycinema.RegisterActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;

public class FormFieldValidation implements TextWatcher {

    private TextView textView;
    private Button button;
    private ValidateRegisterField validateRegisterField;
    public static Map<Integer, Boolean> validators;



    public FormFieldValidation(TextView textView, Button button, ValidateRegisterField validateRegisterField) {
        super();
        this.textView = textView;
        this.button = button;
        this.validateRegisterField = validateRegisterField;
        validators.put(textView.hashCode(), false);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        textView.setText(R.string.empty_field);
        textView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        while(true){
            if(editable.toString().trim().isEmpty()) {
                textView.setText(R.string.empty_field);
                break;
            }
            String error_message = validateRegisterField.validate(editable);
            if(error_message == null){
                enable_button(true);
                return;
            }
            textView.setText(error_message);
            break;
        }
        enable_button(false);
        textView.setVisibility(View.VISIBLE);
    }

    private void enable_button(Boolean valid_state){
        validators.put(textView.hashCode(), valid_state);
        boolean b = true;
        for (Boolean aBoolean : validators.values()) {
            if (!aBoolean) {
                b = false;
                break;
            }
        }
        button.setEnabled(b);
    }

    public static void resetValidators(){
        validators = new HashMap<>();
    }

}
