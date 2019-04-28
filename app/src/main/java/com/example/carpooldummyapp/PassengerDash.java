package com.example.carpooldummyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class PassengerDash extends AppCompatActivity {

    public static final String DEPARTURE_LOCATION = "Departure Location";
    public static final String ARRIVAL_LOCATION  = "Arrival Location";
    public static final String DEPARTURE_TIME  = "Departure Time";
    public static final String ARRIVAL_TIME  = "Arrival Time";

    private EditText Search_Bar;
    private ListView All_Rides_View;
    private ArrayAdapter<Ride> adapter;
    public static ArrayList<Ride> All_Rides_Array = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_dash);

        Search_Bar = findViewById(R.id.Search_Name_Bar);
        All_Rides_View = findViewById(R.id.Rides);

        if(All_Rides_Array.size() == 0) {
            for (Account acc : Start.All_Accounts) {
                if (acc.getACCOUNT_TYPE() == ACCOUNT_TYPE.DRIVER) {
                    DriverAccount Temp_Vozac = (DriverAccount) acc;
                    All_Rides_Array.addAll(Temp_Vozac.getScheduled_Rides());
                }
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, All_Rides_Array);
        All_Rides_View.setAdapter(adapter);
        All_Rides_View.smoothScrollToPosition(adapter.getCount());

        All_Rides_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PassengerDash.this, BookingRide.class);
                intent.putExtra(DEPARTURE_LOCATION, All_Rides_Array.get(position).getDeparture_Location());
                intent.putExtra(ARRIVAL_LOCATION, All_Rides_Array.get(position).getArrival_Location());
                intent.putExtra(DEPARTURE_TIME, All_Rides_Array.get(position).getDeparture_Time());
                intent.putExtra(ARRIVAL_TIME, All_Rides_Array.get(position).getArrival_Time());
                intent.putExtra("position", ((Ride) parent.getAdapter().getItem(position)).get_ID());
                startActivity(intent);
            }
        });

        Search_Bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (PassengerDash.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
