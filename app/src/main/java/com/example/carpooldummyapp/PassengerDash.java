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

    private EditText Search_Bar;
    private ListView Sve_Voznje_View;
    private ArrayAdapter<Voznja> adapter;
    public static ArrayList<Voznja> Sve_Voznje = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_dash);

        Search_Bar = findViewById(R.id.Search_Name_Bar);
        Sve_Voznje_View = findViewById(R.id.Sve_Voznje);


        for (Account acc : Start.Nalozi) {
            if (acc.getTip_naloga() == TIP_NALOGA.VOZAC) {
                DriverAccount Temp_Vozac = (DriverAccount) acc;
                Sve_Voznje.addAll(Temp_Vozac.getTrenutne_Voznje());
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Sve_Voznje);
        Sve_Voznje_View.setAdapter(adapter);
        Sve_Voznje_View.smoothScrollToPosition(adapter.getCount());

        Sve_Voznje_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PassengerDash.this, BookingRide.class);
                intent.putExtra("Polazno Mesto", Sve_Voznje.get(position).getPolazno_Mesto());
                intent.putExtra("Dolazno Mesto", Sve_Voznje.get(position).getDolazno_Mesto());
                intent.putExtra("Polazno Vreme", Sve_Voznje.get(position).getVreme_Polaska());
                intent.putExtra("Dolazno Vreme", Sve_Voznje.get(position).getVreme_Dolaska());
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

        Sve_Voznje_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PassengerDash.this, BookingRide.class);

                intent.putExtra("position", ((Voznja) parent.getAdapter().getItem(position)).get_ID());
                startActivity(intent);
            }
        });

    }


}
