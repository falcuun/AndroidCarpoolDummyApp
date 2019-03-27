package com.example.carpooldummyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class PassengerDash extends AppCompatActivity {

    private EditText Search_Bar;
    private ListView Sve_Voznje_View;
    private ArrayAdapter<Voznja> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_dash);

        ArrayList<Voznja> Sve_Voznje = new ArrayList<>();
        Search_Bar = findViewById(R.id.Search_Name_Bar);
        Sve_Voznje_View = findViewById(R.id.Sve_Voznje);


        for(Account acc : Start.Nalozi){
            if (acc.getTip_naloga() == TIP_NALOGA.VOZAC){
                DriverAccount Temp_Vozac = (DriverAccount) acc;
                Sve_Voznje.addAll(Temp_Vozac.getTrenutne_Voznje());
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Sve_Voznje);
        Sve_Voznje_View.setAdapter(adapter);
        Sve_Voznje_View.smoothScrollToPosition(adapter.getCount());

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
