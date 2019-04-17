package com.example.carpooldummyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class DriverDash extends AppCompatActivity {

    private EditText Mesto_Polaska;
    private EditText Mesto_Dolaska;
    private EditText Vreme_Polaska;
    private EditText Vreme_Dolaska;
    private EditText Slobodnih_Mesta;
    private Button Nova_Voznja;
    private ListView Lista_Voznje;
    private ArrayAdapter<Voznja> arrayAdapter;
    private DriverAccount vozac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dash);
        Init();

    }

    private void Init() {
        setContentView(R.layout.activity_driver_dash);
        Mesto_Polaska = findViewById(R.id.Novi_Polazak);
        Mesto_Dolaska = findViewById(R.id.Novi_Dolazak);
        Vreme_Polaska = findViewById(R.id.Vreme_Polaska);
        Vreme_Dolaska = findViewById(R.id.Vreme_Dolaska);
        Nova_Voznja = findViewById(R.id.Nova_Voznja);
        Lista_Voznje = findViewById(R.id.driverDrivesList);
        Slobodnih_Mesta = findViewById(R.id.Slobodnih_Mesta);
        Napravi_Novu_Voznju();
        Izmeni_Voznju();
        Intent i = getIntent();
        String Acc_Name = i.getStringExtra("Nalog");
        for (Account acc : Start.Nalozi) {
            if (acc.getIme().equalsIgnoreCase(Acc_Name)) {
                vozac = (DriverAccount) acc;
            }
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vozac.getTrenutne_Voznje());
        Lista_Voznje.setAdapter(arrayAdapter);
        Lista_Voznje.smoothScrollToPosition(arrayAdapter.getCount());
    }

    private void Izmeni_Voznju() {
        Lista_Voznje.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                setContentView(R.layout.modify_ride);
                Modify_Init(position);
                return true;
            }
        });
    }

    private void Modify_Init(final int position) {
        final EditText Modified_Mesto_Polaska = findViewById(R.id.Modified_Mesto_Polaska);
        final EditText Modified_Mesto_Dolaska = findViewById(R.id.Modified_Mesto_Dolaska);
        final EditText Modified_Vreme_Polaska = findViewById(R.id.Modified_Vreme_Polaska);
        final EditText Modified_Vreme_Dolaska = findViewById(R.id.Modified_Vreme_Dolaska);
        final Button Modify_Button = findViewById(R.id.Modify_Button);
        final Button Delete_Button = findViewById(R.id.Delete_Ride_Button);

        Modify_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vozac.getTrenutne_Voznje().get(position).setPolazno_Mesto(Modified_Mesto_Polaska.getText().toString());
                vozac.getTrenutne_Voznje().get(position).setDolazno_Mesto(Modified_Mesto_Dolaska.getText().toString());
                vozac.getTrenutne_Voznje().get(position).setVreme_Polaska(Modified_Vreme_Polaska.getText().toString());
                vozac.getTrenutne_Voznje().get(position).setVreme_Dolaska(Modified_Vreme_Dolaska.getText().toString());
                Init();
            }
        });

        Delete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vozac.getTrenutne_Voznje().remove(position);
                Init();
            }
        });
    }
    private void Napravi_Novu_Voznju() {
        Nova_Voznja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mp = Mesto_Polaska.getText().toString();
                String md = Mesto_Dolaska.getText().toString();
                String vp = Vreme_Polaska.getText().toString();
                String vd = Vreme_Dolaska.getText().toString();
                int sm = Integer.parseInt(Slobodnih_Mesta.getText().toString());
                vozac.Add_Voznja(new Voznja(Start.ID, mp, md, vp, vd, sm));
                arrayAdapter.notifyDataSetChanged();
                Lista_Voznje.smoothScrollToPosition(arrayAdapter.getCount());
                Start.ID++;
            }
        });
    }
}
