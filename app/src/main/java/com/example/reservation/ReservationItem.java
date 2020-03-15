package com.example.reservation;

import android.widget.EditText;
import android.widget.TextView;

public class ReservationItem {

    //ExampleItem
    private String eTextAddress;
    private String  mName;


    public ReservationItem(String  resName, String textE3){

        mName = resName;

        eTextAddress = textE3;
    }

    public ReservationItem(TextView nameTwo, EditText editText1, EditText editText2, EditText editText3) {
    }


    public String  getmName() {
        return mName;
    }


    public String geteTextAddress() {
        return eTextAddress;
    }

}
