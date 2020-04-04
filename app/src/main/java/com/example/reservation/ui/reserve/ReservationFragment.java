package com.example.reservation.ui.reserve;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reservation.R;
import com.example.reservation.ReservationItem;
import com.example.reservation.ReserveAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ReservationFragment extends Fragment {

    String inst;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ReservationItem> recycleArrayList = new ArrayList<>();
    private CardView cv;
    private FloatingActionButton buttonRemove;
    private String TAG;
    private View root;
    private String clientName = "";
    private String resAddress = "";
    private String resName = "";
    private int resParty = 0;
    private String resTime = "";
    private EditText nameTwo;
    private EditText restaurantAddress;
    private EditText restaurantName;
    private EditText restaurantParty;
    private EditText reservationTime;
    private Button reservebtn;
    private ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            //when user swipes left on the cardview., the arraylist wil find the position of swiped item and remove it from the recyclerview
            int position = viewHolder.getPosition();

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    recycleArrayList.remove(position);
                    adapter.notifyItemChanged(position, recycleArrayList);
                    //notifty adapter of change in arraylist at certian position and update view
                    adapter.notifyDataSetChanged();
                    break;


            }
        }
    };

    public ReservationFragment() {
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_reserve, container, false);


        buildRecyclerView(); //creates views for adapter, viewholder and layput manager in another method
        createRecycleList();
        setButtonAdd();

        return root;
    }

    public void insertCardView(int position) {


        recycleArrayList.add(new ReservationItem("", "", "", "", ""));
        //adds item to array list using get methods from adapter
    }



    public void createRecycleList() {
        recycleArrayList.add(new ReservationItem("", "", "", "", ""));
        //initalises the array by adding an inital object
    }

    public void buildRecyclerView() {
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());

        adapter = new ReserveAdapter(recycleArrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }

    public void setButtonAdd() {

        buttonRemove = root.findViewById(R.id.button_add); //inialises button from the recylcer "root" view
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recycleArrayList.size();
                insertCardView(position + 1); //adds cardview and new addition to recylcer array list at position + 1
                Log.v(TAG, "Adding CardView");
                adapter.notifyItemInserted(position);
            }
        });
        getActivity().getWindow().getDecorView();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);


    }


}
