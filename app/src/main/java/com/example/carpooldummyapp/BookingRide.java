package com.example.carpooldummyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONException;
import org.json.JSONObject;

public class BookingRide extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Voznja Selected_Voznja;
    private TextView Polazno_Mesto, Dolazno_Mesto, Polazno_Vreme, Dolazno_Vreme, Slobodna_Mesta;
    private Button Cancel_Button, Book_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_ride);


        Polazno_Mesto = findViewById(R.id.Polazno_Mesto);
        Dolazno_Mesto = findViewById(R.id.Polazno_Vreme);
        Polazno_Vreme = findViewById(R.id.Dolazno_Mesto);
        Dolazno_Vreme = findViewById(R.id.Dolazno_Vreme);
        Slobodna_Mesta = findViewById(R.id.Slobodna_Mesta);
        Cancel_Button = findViewById(R.id.Cancel_Button);
        Book_Button = findViewById(R.id.Book_Ride_Button);

        Polazno_Mesto.setText(getIntent().getStringExtra("Polazno Mesto"));
        Dolazno_Mesto.setText(getIntent().getStringExtra("Dolazno Mesto"));
        Polazno_Vreme.setText(getIntent().getStringExtra("Polazno Vreme"));
        Dolazno_Vreme.setText(getIntent().getStringExtra("Dolazno Vreme"));

        int position = getIntent().getIntExtra("position", 0);

        for (Voznja v : PassengerDash.Sve_Voznje){
            if(v.get_ID() == position){
                Selected_Voznja = v;
            }
        }
        Book_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookingRide.this, RideInProgress.class);
                startActivity(i);
            }
        });
        Cancel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLatLng(Selected_Voznja.getPolazno_Mesto());
        getLatLng(Selected_Voznja.getDolazno_Mesto());
    }

    public void getLatLng(String query) {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + query + "&units=metric&appid=8d0c53f37b08f1d5abf0aa4f01bf8118";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    double lat = ((JSONObject) response.opt("coord")).getDouble("lat");
                    double lon = ((JSONObject) response.opt("coord")).getDouble("lon");
                    LatLng tempLL = new LatLng(lat, lon);
                    mMap.addMarker(new MarkerOptions().position(tempLL).title("Marker"));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(tempLL).zoom(5.0f).build();
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                    mMap.moveCamera(cameraUpdate);
                } catch (JSONException ignored) {
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(jsonObjectRequest);
    }
}