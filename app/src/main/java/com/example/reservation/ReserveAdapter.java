package com.example.reservation;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReserveAdapter extends RecyclerView.Adapter<ReserveAdapter.ReserveViewHolder> {

    public ArrayList<ReservationItem> reserveList;

    //exampleadapter

    public static class ReserveViewHolder extends RecyclerView.ViewHolder {

        public EditText name2;
        public EditText editAddress;
        public EditText editResName;
        public EditText editResParty;
        public EditText editResTime;
        public FloatingActionButton fabe;
        public Button btnRes;

        public ReserveViewHolder(@NonNull View itemView) {
            super(itemView);

           buildViews();

            btnRes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference mRef = db.getReference("reservationapp-696969/database/reservationapp-696969/data"); //set path to database

                    String clientName = name2.getText().toString();
                    String restAddress = editAddress.getText().toString();
                    String restName = editResName.getText().toString();
                    String restTime = editResTime.getText().toString();
                    String restParty = editResParty.getText().toString();

                    //apply string value of each EditText to new string

                    if ((!TextUtils.isEmpty(restAddress)) && (!TextUtils.isEmpty(restName)) && (!TextUtils.isEmpty(restTime)) && (!TextUtils.isEmpty(restParty))){
                        //if all editext fields are not empty, then add object of all string values to database, with unique key for each element that is added
                        String userId = mRef.push().getKey();
                        ReservationItem reservation = new ReservationItem(clientName, restAddress, restName, restTime, restParty);
                        mRef.child(userId).setValue(reservation);

                        Toast.makeText(itemView.getContext(), "Reservation has been added", Toast.LENGTH_LONG).show();

                    }else{
                        //if empty prompt user with toast to add fields
                        Toast.makeText(itemView.getContext(), "Make sure all fields have been filled", Toast.LENGTH_LONG).show();
                    }

                }
            });

        }

        public void buildViews(){
            //build view and initalise in onCreate
            btnRes = itemView.findViewById(R.id.reserveBtn);
            name2 = itemView.findViewById(R.id.name2);
            editAddress = itemView.findViewById(R.id.editTextAddress);
            editResName = itemView.findViewById(R.id.editTextResName);
            editResTime = itemView.findViewById(R.id.editTextTime);
            editResParty = itemView.findViewById(R.id.editTextParty);
            fabe = itemView.findViewById(R.id.button_add);
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

        holder.name2.setText(reservationItem.getmName());
        holder.editAddress.setText(reservationItem.geteTextAddress());
        holder.editResName.setText(reservationItem.geteTextResName());
        holder.editResParty.setText(reservationItem.geteTextResParty());
        holder.editResTime.setText(reservationItem.geteTextTime());

    }

    @Override
    public int getItemCount() {
        return reserveList.size();
    }




}
