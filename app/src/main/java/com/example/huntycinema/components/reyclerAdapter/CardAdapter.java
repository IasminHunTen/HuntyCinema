package com.example.huntycinema.components.reyclerAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huntycinema.R;
import com.example.huntycinema.services.cinema_server.users.cards.Card;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private Context context;
    private String cards_holder;
    private List<Card> cardList;
    private static OnDeleteAdapterItem onDeleteAdapterItem;

    public CardAdapter(Context context, String cards_holder, List<Card> cardList) {
        this.context = context;
        this.cards_holder = cards_holder;
        this.cardList = cardList;
    }

    public static void setOnDeleteAdapterItem(OnDeleteAdapterItem onDeleteAdapterItem) {
        CardAdapter.onDeleteAdapterItem = onDeleteAdapterItem;
    }

    @NonNull
    @Override
    public CardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_reprezentation, parent, false);
        return new CardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.card_number.setText(card.getCard_number());
        holder.card_validity.setText(String.valueOf(card.getExpire_month()+ "/"+
                card.getExpire_year()));
        holder.card_holder.setText(cards_holder);
        holder.revoke_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog builder = new AlertDialog.Builder(context)
                    .setMessage("Are you sure that you want to delete this card ?")
                    .setNegativeButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onDeleteAdapterItem.onDelete(card.getCard_number());
                        }
                    }).setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create();
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView card_number, card_validity, card_holder, revoke_action;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_number = itemView.findViewById(R.id.card_number);
            card_validity = itemView.findViewById(R.id.card_validity);
            card_holder = itemView.findViewById(R.id.card_holder);
            revoke_action = itemView.findViewById(R.id.revoke_card);
        }
    }
}
