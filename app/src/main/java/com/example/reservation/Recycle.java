package com.example.reservation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class Recycle extends AppCompatActivity {

    //main activity
    private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<ReservationItem> recycleArrayList = new ArrayList<>();
    public TextView nameTwo;
    public EditText editText1;
    public EditText editText2;
    public EditText editText3;
    public FloatingActionButton buttonRemove;
    public String TAG;
    public DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reserve);
//        createRecycleList();
        buildRecyclerView();


    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    recycleArrayList.remove(position);
                    adapter.notifyItemRemoved(position);
                    break;

            }
        }
    };
//    public void insertCardView(int position){
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("cardView");
//        recycleArrayList.add(new  ReservationItem(FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), ""));
//    }
// //TODO
//    public void insertFIrebaseCardView(int position){
//        recycleArrayList.add(new  ReservationItem(FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), ""));
//    }
//
//    public void createRecycleList(){
//        recycleArrayList = new ArrayList<>();
//        recycleArrayList.add(new  ReservationItem(FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), ""));
//
//    }

    public void buildRecyclerView(){
        nameTwo = findViewById(R.id.name2);

        editText3 = findViewById(R.id.editTextAddress);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        adapter = new ReserveAdapter(recycleArrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
