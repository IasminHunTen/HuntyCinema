package com.example.huntycinema;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huntycinema.localstorage.DataStorageSingleton;
import com.example.huntycinema.services.cinema_server.users.authentication.UserClient;
import com.example.huntycinema.services.cinema_server.users.authentication.UserToken;
import com.example.huntycinema.utils.RegisterFieldsValidator;
import com.example.huntycinema.utils.ResponseHandler;
import com.example.huntycinema.utils.StringUtils;
import com.example.huntycinema.utils.ValidateRegisterField;

public class RegisterActivity extends AppCompatActivity {

    private EditText mail_et, username_et, password_et;
    private TextView mail_tv, username_tv, password_tv, go2Login;
    private Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init(){

        register_button = (Button) findViewById(R.id.register_button);

        mail_et = (EditText) findViewById(R.id.email_field);
        mail_tv = (TextView) findViewById(R.id.email_validator);
        mail_et.addTextChangedListener(new RegisterFieldsValidator(mail_tv, register_button, new ValidateRegisterField() {
            @Override
            public String validate(Editable editable) {
                String input_field = editable.toString().trim();
                if(!Patterns.EMAIL_ADDRESS.matcher(input_field).matches())
                    return "invalid email address";
                if(input_field.length() > 64)
                    return "at most 64 characters are accepted";
                return null;
            }
        }));

        username_et = (EditText) findViewById(R.id.username_field_register);
        username_tv = (TextView) findViewById(R.id.username_validator_register);
        username_et.addTextChangedListener(new RegisterFieldsValidator(username_tv, register_button, new ValidateRegisterField() {
            @Override
            public String validate(Editable editable) {
                String input_field = editable.toString().trim();
                if(input_field.length() < 6)
                    return "al least 6 characters are necessary";
                if(input_field.length() > 64)
                    return "at most 64 characters are accepted";
                return null;
            }
        }));

        password_et = (EditText) findViewById(R.id.password_field_register);
        password_tv = (TextView) findViewById(R.id.password_validator_register);
        password_et.addTextChangedListener(new RegisterFieldsValidator(password_tv, register_button, new ValidateRegisterField() {
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
                return null;
            }
        }));


        register();
        go2login();
    }

    private void register(){
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UserClient().register(
                        mail_et.getText().toString().trim(),
                        username_et.getText().toString().trim(),
                        password_et.getText().toString().trim(),
                        DataStorageSingleton.getDevice_id(),
                        new ResponseHandler<UserToken>() {
                            @Override
                            public void onResponse(UserToken response) {
                                DataStorageSingleton.setToken(response.getToken());
                                if(DataStorageSingleton.isGo2account())
                                    startActivity(new Intent(RegisterActivity.this, MyAccountActivity.class));
                                else
                                    Toast.makeText(RegisterActivity.this, "Register Successfully",Toast.LENGTH_LONG).show();
                            }

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onError(int code, String error) {
                                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                                if(code==409)
                                    Toast.makeText(getApplicationContext(), "email or username already in use", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                            }
                        }
                );
            }
        });
    }
    private void go2login() {
        go2Login = findViewById(R.id.login_link);
        go2Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

}