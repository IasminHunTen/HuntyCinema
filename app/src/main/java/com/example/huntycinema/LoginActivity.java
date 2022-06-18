package com.example.huntycinema;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huntycinema.components.dialogs.ResetPasswordDialog;
import com.example.huntycinema.localstorage.DataStorageSingleton;
import com.example.huntycinema.services.cinema_server.users.authentication.UserClient;
import com.example.huntycinema.services.cinema_server.users.authentication.UserToken;
import com.example.huntycinema.components.dialogs.DialogCommunication;
import com.example.huntycinema.utils.LoginFieldsValidator;
import com.example.huntycinema.utils.ResponseHandler;
import com.example.huntycinema.utils.StringUtils;

import java.lang.reflect.Type;

public class LoginActivity extends AppCompatActivity {

    private EditText username_et, password_et;
    private TextView username_tv, password_tv, go2Register, reset_password;
    private Button login_btn;
    private ImageButton hide_password;

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
        reset_password = (TextView) findViewById(R.id.reset_password_link);
        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

        hide_password = findViewById(R.id.login_hide_password);
        hide_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringUtils.toggle_password_field(password_et);
            }
        });
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

    private void resetPassword(){
        String username = username_et.getText().toString().trim();
        new UserClient().resetPassword(username, new ResponseHandler<Void>() {
            @Override
            public void onResponse(Void response) {
                Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }

            @Override
            public void onError(int code, String error) {
                if(code == 404){
                    error = "User with username: " + username + " not found";
                }
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
            }
        });
    }



}