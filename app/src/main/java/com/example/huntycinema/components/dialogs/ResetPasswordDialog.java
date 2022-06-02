package com.example.huntycinema.components.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.huntycinema.R;

import java.util.regex.Pattern;

public class ResetPasswordDialog extends DialogFragment {

    private EditText mail;
    private DialogCommunication dialogCommunication;

    public ResetPasswordDialog(DialogCommunication dialogCommunication){
        this.dialogCommunication = dialogCommunication;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_reset_password, null);
        mail  = view.findViewById(R.id.email_reset_password);

        builder.setView(view)
                .setMessage("Please provide us an email address, where you going to receive a mail" +
                        "with confirmation code")
                .setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String input_field = mail.getText().toString().trim();
                        if(!Patterns.EMAIL_ADDRESS.matcher(input_field).matches())
                            Toast.makeText(getActivity().getApplicationContext(), "Invalid email", Toast.LENGTH_LONG).show();
                        else{
                            dialogCommunication.communicate(input_field);
                        }
                    }
                });
        return builder.create();
        }
    }

