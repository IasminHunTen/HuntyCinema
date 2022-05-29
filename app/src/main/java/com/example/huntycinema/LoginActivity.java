package com.example.huntycinema;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huntycinema.localstorage.DataStorageSingleton;
import com.example.huntycinema.services.cinema_server.users.authentication.UserClient;
import com.example.huntycinema.services.cinema_server.users.authentication.UserToken;
import com.example.huntycinema.utils.LoginFieldsValidator;
import com.example.huntycinema.utils.ResponseHandler;

public class LoginActivity extends AppCompatActivity {

    private EditText username_et, password_et;
    private TextView username_tv, password_tv, go2Register;
    private Button login_btn;
    private Intent nextIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        login_btn = (Button) findViewById(R.id.login_button);
        login();

        username_et = (EditText) findViewById(R.id.username_field_login);
        username_tv = (TextView) findViewById(R.id.username_validator_login);
        username_et.addTextChangedListener(new LoginFieldsValidator(username_tv, login_btn));

        password_et = (EditText) findViewById(R.id.password_field_login);
        password_tv = (TextView) findViewById(R.id.password_validator_login);
        password_et.addTextChangedListener(new LoginFieldsValidator(password_tv, login_btn));
        go2Register = (TextView) findViewById(R.id.register_link);
        go2register();
    }

    private boolean emptyET(EditText editText){
        return editText.getText().toString().isEmpty();
    }

    private void primaryValidation(EditText editText, TextView textView){
        editText.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                textView.setVisibility(View.INVISIBLE);
                if(emptyET(editText)){
                    textView.setText(editText.getHint() + "can not be empty");
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void login(){
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UserClient().login(
                        username_et.getText().toString().trim(),
                        password_et.getText().toString().trim(),
                        DataStorageSingleton.getDevice_id(),
                        new ResponseHandler<UserToken>() {
                            @Override
                            public void onResponse(UserToken response) {
                                DataStorageSingleton.setToken(response.getToken());
                                if (DataStorageSingleton.isGo2account())
                                    startActivity(new Intent(LoginActivity.this, MyAccountActivity.class));
                                else
                                    Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_LONG ).show();
                            }

                            @Override
                            public void onError(int code, String error) {
                                if(code==404){
                                    username_tv.setText(R.string.username_does_not_exist);
                                    username_tv.setVisibility(View.VISIBLE);
                                }
                                else if (code==401){
                                    password_tv.setText(R.string.wrong_password);
                                    password_tv.setVisibility(View.VISIBLE);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                );
            }
        });
    }

    private void go2register(){
        go2Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }
}