package com.example.huntycinema.components.reyclerAdapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huntycinema.R;

import java.util.List;

public class SelectedPlaceAdapter extends RecyclerView.Adapter<SelectedPlaceAdapter.MyViewHolder> {
    private Context context;
    private List<String> places;
    private static OnDeleteAdapterItem<Integer> onDeleteAdapterItem;

    public SelectedPlaceAdapter(Context context, List<String> places) {
        this.context = context;
        this.places = places;
    }

    public static void setOnDeleteAdapterItem(OnDeleteAdapterItem<Integer> onDeleteAdapterItem) {
        SelectedPlaceAdapter.onDeleteAdapterItem = onDeleteAdapterItem;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        Button button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.selected_place);
            button = itemView.findViewById(R.id.remove_tickets);
        }
    }

    @NonNull
    @Override
    public SelectedPlaceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_place, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(places.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog builder = new AlertDialog.Builder(context)
                        .setMessage("Are you sure that you want to remove this ticket")
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                onDeleteAdapterItem.onDelete(position);
                            }
                        })
                        .setNeutralButton("cancel", new DialogInterface.OnClickListener() {
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
        return places.size();
    }

}
