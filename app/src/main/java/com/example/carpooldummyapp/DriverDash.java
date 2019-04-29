package com.example.carpooldummyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class DriverDash extends AppCompatActivity {

    private EditText New_Departure_Location;
    private EditText New_Arrival_Location;
    private EditText New_Departure_Time;
    private EditText New_Arrival_Time;
    private EditText New_Free_Spaces;
    private Button New_Ride_Button;
    private ListView Rides_List_View;
    private ArrayAdapter<Ride> arrayAdapter;
    private DriverAccount Driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dash);
        Init();

    }

    private void Init() {
        setContentView(R.layout.activity_driver_dash);
        New_Departure_Location = findViewById(R.id.New_Departure_Location);
        New_Arrival_Location = findViewById(R.id.New_Arrival_Location);
        New_Departure_Time = findViewById(R.id.New_Departure_Time);
        New_Arrival_Time = findViewById(R.id.New_Arrival_Time);
        New_Ride_Button = findViewById(R.id.New_Ride_Submit);
        Rides_List_View = findViewById(R.id.driverDrivesList);
        New_Free_Spaces = findViewById(R.id.New_Free_Spaces);
        Napravi_Novu_Voznju();
        Izmeni_Voznju();
        Intent i = getIntent();
        String Acc_Name = i.getStringExtra("Account");
        for (Account acc : Start.All_Accounts) {
            if (acc.getName().equalsIgnoreCase(Acc_Name)) {
                Driver = (DriverAccount) acc;
            }
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Driver.getScheduled_Rides());
        Rides_List_View.setAdapter(arrayAdapter);
        Rides_List_View.smoothScrollToPosition(arrayAdapter.getCount());
    }

    private void Izmeni_Voznju() {
        Rides_List_View.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                setContentView(R.layout.modify_ride);
                Modify_Init(position);
                return true;
            }
        });
    }

    private void Modify_Init(final int position) {
        final EditText Modified_New_Departure_Location = findViewById(R.id.Modified_Departure_Location);
        final EditText Modified_New_Arrival_Location = findViewById(R.id.Modified_Arrival_Location);
        final EditText Modified_New_Departure_Time = findViewById(R.id.Modified_Departure_Time);
        final EditText Modified_New_Arrival_Time = findViewById(R.id.Modified_Arrival_Time);
        final Button Modify_Button = findViewById(R.id.Modify_Button);
        final Button Delete_Button = findViewById(R.id.Delete_Ride_Button);

        Modify_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Driver.getScheduled_Rides().get(position).setDeparture_Location(Modified_New_Departure_Location.getText().toString());
                Driver.getScheduled_Rides().get(position).setArrival_Location(Modified_New_Arrival_Location.getText().toString());
                Driver.getScheduled_Rides().get(position).setDeparture_Time(Modified_New_Departure_Time.getText().toString());
                Driver.getScheduled_Rides().get(position).setArrival_Time(Modified_New_Arrival_Time.getText().toString());
                Init();
            }
        });

        Delete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Driver.getScheduled_Rides().remove(position);
                Init();
            }
        });
    }
    private void Napravi_Novu_Voznju() {
        New_Ride_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mp = New_Departure_Location.getText().toString();
                String md = New_Arrival_Location.getText().toString();
                String vp = New_Departure_Time.getText().toString();
                String vd = New_Arrival_Time.getText().toString();
                int sm = Integer.parseInt(New_Free_Spaces.getText().toString());
                Driver.Add_Ride(new Ride(Start.ID, mp, md, vp, vd, sm, Driver));
                PassengerDash.All_Rides_Array.add(new Ride(Start.ID, mp, md, vp, vd, sm, Driver));
                arrayAdapter.notifyDataSetChanged();
                Rides_List_View.smoothScrollToPosition(arrayAdapter.getCount());
                Start.ID++;
            }
        });
    }
}
