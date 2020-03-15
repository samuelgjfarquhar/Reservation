package com.example.reservation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReserveAdapter extends RecyclerView.Adapter<ReserveAdapter.ReserveViewHolder> {

    public ArrayList<ReservationItem> reserveList;

    //exampleadapter

    public static class ReserveViewHolder extends RecyclerView.ViewHolder {


        public TextView editTime;
        public EditText editParty;
        public EditText editAddress;
        public TextView name2;
        public FloatingActionButton faby;

        public ReserveViewHolder(@NonNull View itemView) {
            super(itemView);


            editAddress = itemView.findViewById(R.id.editTextAddress);
            name2 = itemView.findViewById(R.id.name2);
            faby = itemView.findViewById(R.id.button_add);

        }


    }

    public ReserveAdapter(ArrayList<ReservationItem> reservationItems){
        reserveList = reservationItems;
    }

    @NonNull
    @Override
    public ReserveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reserve_item, parent, false);
        return new ReserveViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReserveViewHolder holder, final int position) {
        final ReservationItem reservationItem = reserveList.get(position);

        holder.editAddress.setText(reservationItem.geteTextAddress());
        holder.name2.setText(reservationItem.getmName());


    }

    @Override
    public int getItemCount() {
        return reserveList.size();
    }


}
