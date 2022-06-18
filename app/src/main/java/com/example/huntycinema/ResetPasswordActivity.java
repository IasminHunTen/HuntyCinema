package com.example.huntycinema;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huntycinema.services.cinema_server.users.authentication.ResetPasswordBody;
import com.example.huntycinema.services.cinema_server.users.authentication.UserClient;
import com.example.huntycinema.utils.FormFieldValidation;
import com.example.huntycinema.utils.ResponseHandler;
import com.example.huntycinema.utils.StringUtils;
import com.example.huntycinema.utils.ValidateRegisterField;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText password_et, confirmation_code_et;
    private TextView password_tv, confirmation_code_tv;
    private Button reset_btn;
    private ImageButton hide_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        FormFieldValidation.resetValidators();
        init();
    }

    private void init(){
        reset_btn = findViewById(R.id.reset_password_btn);

        password_et = findViewById(R.id.reset_password_et);
        password_tv = findViewById(R.id.reset_password_validator);
        password_et.addTextChangedListener(new FormFieldValidation(password_tv, reset_btn, new ValidateRegisterField() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public String validate(Editable editable) {
                String input_field = editable.toString().trim();
                if(input_field.length() < 6)
                    return "al least 6 characters are necessary";
                if(input_field.length() > 64)
                    return "at most 64 characters are accepted";
                if(!StringUtils.containsLowerCase(input_field))
                    return "must contain at least one lower case";
                if(!StringUtils.containsUpperCase(input_field))
                    return "must contain at least one upper case";
                if(StringUtils.containsWhiteSpace(input_field))
                    return "must not contains white spaces";
                return null;            }
        }));

        confirmation_code_et = findViewById(R.id.confirm_code);
        confirmation_code_tv = findViewById(R.id.confirm_code_validator);
        confirmation_code_et.addTextChangedListener(new FormFieldValidation(confirmation_code_tv, reset_btn, new ValidateRegisterField() {
            @Override
            public String validate(Editable editable) {
                String input_field = editable.toString().trim();
                if(input_field.length()!=6)
                    return "confirmation code must be compose out of 6 digits";
                return null;
            }
        }));

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

        hide_password = findViewById(R.id.hide_password_reset_password);
        hide_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringUtils.toggle_password_field(password_et);
            }
        });
    }

    private void resetPassword(){

        String username = getIntent().getStringExtra("username");
        ResetPasswordBody resetPasswordBody = new ResetPasswordBody(
                username,
                password_et.getText().toString().trim(),
                confirmation_code_et.getText().toString().trim()
        );
        new UserClient().updatePassword(resetPasswordBody, new ResponseHandler<Void>() {
            @Override
            public void onResponse(Void response) {
                onBackPressed();
            }

            @Override
            public void onError(int code, String error) {
                if(code == 400){
                    error = "confirmation code does not match";
                }
                if(code == 401){
                    error = "password reset request was not made for user: "+ username;
                }
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
            }
        });
    }


}