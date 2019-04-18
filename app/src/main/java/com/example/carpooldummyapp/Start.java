package com.example.carpooldummyapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class Start extends AppCompatActivity {
    static ArrayList<Account> Nalozi = new ArrayList<>();
    Spinner spinner;
    String Acc_Name;
    TIP_NALOGA tip;
    public static int ID = 0;

    static boolean Dummy_Data_Added = false;

    void Add_Dummy_Data(){
        DriverAccount driver1 = new DriverAccount("Vozac1", "Vozi1", "1", "vozac@1", "BMW", TIP_NALOGA.VOZAC, 0);
        DriverAccount driver2 = new DriverAccount("Vozac2", "Vozi2", "2", "vozac@2", "BMW", TIP_NALOGA.VOZAC, 0);
        DriverAccount driver3 = new DriverAccount("Vozac3", "Vozi3", "2", "vozac@2", "BMW", TIP_NALOGA.VOZAC, 0);
        DriverAccount driver4 = new DriverAccount("Vozac4", "Vozi4", "2", "vozac@2", "BMW", TIP_NALOGA.VOZAC, 0);
        DriverAccount driver5 = new DriverAccount("Vozac5", "Vozi5", "2", "vozac@2", "BMW", TIP_NALOGA.VOZAC, 0);
        DriverAccount driver6 = new DriverAccount("Vozac6", "Vozi6", "2", "vozac@2", "BMW", TIP_NALOGA.VOZAC, 0);
        DriverAccount driver7 = new DriverAccount("Vozac7", "Vozi7", "2", "vozac@2", "BMW", TIP_NALOGA.VOZAC, 0);

        Account passenger1 = new PassengerAccount("Putnik1", "Putuje1", "2", "putnik@1", TIP_NALOGA.PUTNIK);

        driver1.Add_Voznja(new Voznja(ID,"Belgrade", "Novi Sad", "Danas", "Sutra", 2));
        driver2.Add_Voznja(new Voznja(ID,"Paracin", "Nis", "Ponedeljak", "Utorak",3));
        driver3.Add_Voznja(new Voznja(ID,"Cuprija", "Jagodina", "Subota", "Nedelja", 1));
        driver4.Add_Voznja(new Voznja(ID,"Kraljevo", "Kragujevac", "Danas", "Nakosutra", 4));
        driver5.Add_Voznja(new Voznja(ID,"Krusevac", "Presevo", "15:00", "20:00", 2));
        driver6.Add_Voznja(new Voznja(ID,"Subotica", "Budapest", "08:00", "20:00", 1));
        driver7.Add_Voznja(new Voznja(ID,"Kuca", "Poso", "Danas", "Kasnije Danas", 3));


        Nalozi.add(driver1);
        Nalozi.add(driver2);
        Nalozi.add(driver3);
        Nalozi.add(driver4);
        Nalozi.add(driver5);
        Nalozi.add(driver6);
        Nalozi.add(driver7);
        Nalozi.add(passenger1);
        Toast.makeText(this, String.valueOf(Nalozi.size()), Toast.LENGTH_LONG).show();

        Dummy_Data_Added = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("SPARTAAAAAAAAAAAAAAAAAA", String.valueOf(Nalozi.size()));
        if(!Dummy_Data_Added){

            Add_Dummy_Data();
        }

        Log_In();
    }

    private boolean User_Exists(ArrayList<Account> list, String Ime_Query) {
        for (Account acc : list) {
            if (acc.getIme().equalsIgnoreCase(Ime_Query)) {
                Acc_Name = acc.getIme();
                tip = acc.getTip_naloga();
                return true;
            }
        }
        return false;
    }

    private void Driver_Dash() {
        Intent intent = new Intent(this, DriverDash.class);
        intent.putExtra("Nalog", Acc_Name);
        startActivity(intent);
    }

    private void Passenger_Dash() {
        Intent intent = new Intent(this, PassengerDash.class);
        startActivity(intent);
    }

    private void Log_In() {
        setContentView(R.layout.activity_start);

        final EditText Input_Ime = findViewById(R.id.Input_Ime);
        Button Login_Button = findViewById(R.id.Login_Button);

        Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (User_Exists(Nalozi, Input_Ime.getText().toString())) {
                    Toast.makeText(Start.this, "Account Exists", Toast.LENGTH_LONG).show();
                    if (tip == TIP_NALOGA.PUTNIK) {
                        Passenger_Dash();
                    } else {
                        Driver_Dash();
                    }
                } else {
                    Toast.makeText(Start.this, "Account Doesn't Exist", Toast.LENGTH_LONG).show();
                    Create_New_User();
                }
            }
        });
    }

    private void Create_New_User() {

        Start.this.setContentView(R.layout.register_new_account);
        spinner = findViewById(R.id.spinner1);
        String[] types = {"Driver", "Passenger"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, types);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        final EditText Novo_Ime = findViewById(R.id.Novo_Ime);
        final EditText Novo_Prezime = findViewById(R.id.Novo_Prezime);
        final EditText Novi_Telefon = findViewById(R.id.Novi_Telefon);
        final EditText Novi_Email = findViewById(R.id.Novi_Email);
        Button Register = findViewById(R.id.Register_Button);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ime = Novo_Ime.getText().toString();
                String prezime = Novo_Prezime.getText().toString();
                String Telefon = Novi_Telefon.getText().toString();
                String Email = Novi_Email.getText().toString();
                if (spinner.getSelectedItemPosition() == 0) {
                    showAddItemDialog(Start.this);
                    Register_New_Driver(ime, prezime, Telefon, Email, Car_model);
                } else {
                    Register_New_Passenger(ime, prezime, Telefon, Email);
                }
            }
        });
    }

    private void Register_New_Passenger(String ime, String prezime, String email, String telefon) {
        Nalozi.add(new PassengerAccount(ime, prezime, telefon, email, TIP_NALOGA.PUTNIK));
    }

    private void Register_New_Driver(String ime, String prezime, String email, String telefon, String model) {
        Nalozi.add(new DriverAccount(ime, prezime, telefon, email, model, TIP_NALOGA.VOZAC, 0));
    }

    String Car_model;

    private void showAddItemDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Enter Car Model")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Car_model = String.valueOf(taskEditText.getText());
                        Log_In();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

}
