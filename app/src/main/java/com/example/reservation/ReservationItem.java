package com.example.reservation;

import android.widget.EditText;
import android.widget.TextView;

public class ReservationItem {

    //ExampleItem
    private String  mName = "";
    private String eTextAddress= "";
    private String eTextResName= "";
    private String eTextResParty = "";
    private String eTextTime = "";

    public ReservationItem(String resName, String textadd, String textname, String textParty, String texttime){

        mName = resName;
        eTextAddress = textadd;
        eTextResName = textname;
        eTextResParty = textParty;
        eTextTime = texttime;
    }

    public ReservationItem(EditText nameTwo, EditText editTextAddress, EditText editTextResName, EditText editTextParty, EditText editTextTime) {
    }

    //get set methods to initalise arraylist that will get used later in adapter using ViewHolder

    public String  getmName() {
        return mName;
    }

    public String geteTextAddress() {
        return eTextAddress;
    }

    public String geteTextResName() {
        return eTextResName;
    }

    public String geteTextResParty() {
        return eTextResParty;
    }

    public String geteTextTime() {
        return eTextTime;
    }
}
