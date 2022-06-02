package com.example.huntycinema.components.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huntycinema.R;
import com.example.huntycinema.components.reyclerAdapter.CardNumbersAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ChooseCardDialog extends DialogFragment {


    private List<String> stringList;
    private DialogCommunication dialogCommunication;
    private boolean flag;

    public ChooseCardDialog(List<String> stringList, DialogCommunication dialogCommunication){
        this.stringList = stringList;
        this.dialogCommunication = dialogCommunication;
        flag = false;
    }

    DialogCommunication dialogCommunication2 = new DialogCommunication() {
        @Override
        public void communicate(String... values) {
            dialogCommunication.communicate(values);
            flag = true;
        }
    };

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_choose_card, null);
        RecyclerView recyclerView = view.findViewById(R.id.cards_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CardNumbersAdapter(getActivity(), stringList, dialogCommunication2));
        builder.setNeutralButton("cancel", null);
        return builder.create();
    }

    public void setCancelListener(AlertDialog dialog){
        Button button = dialog.getButton(DialogInterface.BUTTON_NEUTRAL);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag)
                    return;
                dialog.dismiss();
            }
        });
    }

}
