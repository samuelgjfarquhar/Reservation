package com.example.reservation.ui.reserve;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reservation.R;
import com.example.reservation.ReservationItem;
import com.example.reservation.ReserveAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReservationFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {
    public AppBarConfiguration mAppBarConfiguration;
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;
    public ArrayList<ReservationItem> recycleArrayList = new ArrayList<>();
    public TextView nameTwo;
    public EditText editText1;
    public EditText editText2;
    public EditText editText3;
    public FloatingActionButton buttonRemove;
    public String TAG;
    public DatabaseReference databaseReference;
    public View root;
    public Button b;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_reserve, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        buildRecyclerView();


        databaseReference = FirebaseDatabase.getInstance().getReference().child("cardView");
        databaseReference.keepSynced(true);

        createRecycleList();
        setButtonAdd();

        return root;
    }

    private ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getPosition();

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    recycleArrayList.remove(position);
                    adapter.notifyItemChanged(position, recycleArrayList);
                    adapter.notifyDataSetChanged();
                    break;


            }
        }
    };

    public void insertCardView(int position) {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("cardView");
        recycleArrayList.add(new ReservationItem(FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), ""));
    }

    public void createRecycleList() {
        recycleArrayList.add(new ReservationItem(FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), ""));

    }

    public void buildRecyclerView() {
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());

        adapter = new ReserveAdapter(recycleArrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        nameTwo = root.findViewById(R.id.name2);
        editText3 = root.findViewById(R.id.editTextAddress);

    }

    public void setButtonAdd() {

        buttonRemove = root.findViewById(R.id.button_add);
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recycleArrayList.size();
                insertCardView(position + 1);
                Log.v(TAG, "Adding CardView");
                adapter.notifyItemInserted(position);

            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView tv = root.findViewById(R.id.time);
        tv.setText(hourOfDay + ":" + minute);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);



    }
}
