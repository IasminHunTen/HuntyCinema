package com.example.huntycinema.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huntycinema.R;

public class LoginFieldsValidator implements TextWatcher {

    private TextView textView;
    private Button button;
    private static Boolean buttonState;

    public LoginFieldsValidator(TextView textView, Button button) {
        super();
        buttonState = false;
        this.textView = textView;
        this.button = button;
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
        if(editable.toString().trim().isEmpty()){
            enableButton(false);
            textView.setVisibility(View.VISIBLE);
        }else{
            enableButton(true);
        }
    }

    private void enableButton(Boolean state){
        button.setEnabled(buttonState && state);
        buttonState = state;
    }
}
