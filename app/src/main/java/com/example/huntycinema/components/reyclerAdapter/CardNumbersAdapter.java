package com.example.huntycinema.components.reyclerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huntycinema.R;
import com.example.huntycinema.components.dialogs.DialogCommunication;

import java.util.List;

public class CardNumbersAdapter extends RecyclerView.Adapter<CardNumbersAdapter.MyViewHolder> {

    private Context context;
    private List<String> card_list;
    private DialogCommunication dialogCommunication;

    public CardNumbersAdapter(Context context, List<String> card_list, DialogCommunication dialogCommunication) {
        this.context = context;
        this.card_list = card_list;
        this.dialogCommunication = dialogCommunication;
    }

    @NonNull
    @Override
    public CardNumbersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_number, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(card_list.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCommunication.communicate(card_list.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return card_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.card_number_dialog);
            button = itemView.findViewById(R.id.pay_btn);
        }
    }
}
