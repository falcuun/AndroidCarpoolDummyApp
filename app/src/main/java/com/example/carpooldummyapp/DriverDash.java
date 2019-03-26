package com.example.carpooldummyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class DriverDash extends AppCompatActivity {

    private EditText Mesto_Polaska;
    private EditText Mesto_Dolaska;
    private EditText Vreme_Polaska;
    private EditText Vreme_Dolaska;
    private Button Nova_Voznja;
    private ListView Lista_Voznje;
    private ArrayAdapter<Voznja> arrayAdapter;
    private DriverAccount vozac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dash);
        Init();

        Intent i = getIntent();
        String Acc_Name = i.getStringExtra("Nalog");
        for (Account acc : Start.Nalozi) {
            if (acc.getIme().equalsIgnoreCase(Acc_Name)) {
                vozac = (DriverAccount)acc;
            }
        }
        Napravi_Novu_Voznju();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vozac.getTrenutne_Voznje());

        Lista_Voznje.setAdapter(arrayAdapter);
    }

    private void Init() {
        Mesto_Polaska = findViewById(R.id.Novi_Polazak);
        Mesto_Dolaska = findViewById(R.id.Novi_Dolazak);
        Vreme_Polaska = findViewById(R.id.Vreme_Polaska);
        Vreme_Dolaska = findViewById(R.id.Vreme_Dolaska);
        Nova_Voznja = findViewById(R.id.Nova_Voznja);
        Lista_Voznje = findViewById(R.id.driverDrivesList);
    }

    private void Napravi_Novu_Voznju() {
        Nova_Voznja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mp = Mesto_Polaska.getText().toString();
                String md = Mesto_Dolaska.getText().toString();
                String vp = Vreme_Polaska.getText().toString();
                String vd = Vreme_Dolaska.getText().toString();
                vozac.Add_Voznja(new Voznja(mp, md, vp, vd));
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}
