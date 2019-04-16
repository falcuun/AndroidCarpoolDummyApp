package com.example.carpooldummyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BookingRide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_ride);

        int position = getIntent().getIntExtra("position", 0);
        Voznja Selected_Voznja = PassengerDash.Sve_Voznje.get(position);
        Toast.makeText(this, Selected_Voznja.getDolazno_Mesto(), Toast.LENGTH_LONG).show();
    }
}
