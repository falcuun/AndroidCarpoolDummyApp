package com.example.carpooldummyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;
import com.google.android.gms.common.data.DataHolder;

import java.util.ArrayList;
import java.util.List;

public class PassengerDash extends AppCompatActivity {

    public static final String DEPARTURE_LOCATION = "Departure Location";
    public static final String ARRIVAL_LOCATION = "Arrival Location";
    public static final String DEPARTURE_TIME = "Departure Time";
    public static final String ARRIVAL_TIME = "Arrival Time";

    private EditText Search_Bar;
    public static ArrayList<Ride> All_Rides_Array = new ArrayList<>();
    private RecyclerView recyclerView;
    private Recycler_View_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_dash);

        Search_Bar = findViewById(R.id.Search_Name_Bar);
        recyclerView =  findViewById(R.id.recyclerview);

        Search_Bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    void Populate_List(){
        for (Account acc : Start.All_Accounts) {
            if (acc.getACCOUNT_TYPE() == ACCOUNT_TYPE.DRIVER) {
                DriverAccount Temp_Vozac = (DriverAccount) acc;
                All_Rides_Array.addAll(Temp_Vozac.getScheduled_Rides());
            }
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        adapter = null;
    }

    @Override
    public void onResume(){
        super.onResume();

        if (All_Rides_Array.size() == 0) {
            Populate_List();
        } else {
            All_Rides_Array.clear();
            Populate_List();
        }

        adapter = new Recycler_View_Adapter(All_Rides_Array, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
