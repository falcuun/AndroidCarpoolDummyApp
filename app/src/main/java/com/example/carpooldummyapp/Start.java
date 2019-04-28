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
    static ArrayList<Account> All_Accounts = new ArrayList<>();
    Spinner spinner;
    String Acc_Name;
    ACCOUNT_TYPE type;
    public static int ID = 0;

    static boolean Dummy_Data_Added = false;

    void Add_Dummy_Data(){
        DriverAccount driver1 = new DriverAccount("Vozac1", "Vozi1", "1", "vozac@1", "BMW", ACCOUNT_TYPE.DRIVER, 0);
        DriverAccount driver2 = new DriverAccount("Vozac2", "Vozi2", "2", "vozac@2", "BMW", ACCOUNT_TYPE.DRIVER, 0);
        DriverAccount driver3 = new DriverAccount("Vozac3", "Vozi3", "2", "vozac@2", "BMW", ACCOUNT_TYPE.DRIVER, 0);
        DriverAccount driver4 = new DriverAccount("Vozac4", "Vozi4", "2", "vozac@2", "BMW", ACCOUNT_TYPE.DRIVER, 0);
        DriverAccount driver5 = new DriverAccount("Vozac5", "Vozi5", "2", "vozac@2", "BMW", ACCOUNT_TYPE.DRIVER, 0);
        DriverAccount driver6 = new DriverAccount("Vozac6", "Vozi6", "2", "vozac@2", "BMW", ACCOUNT_TYPE.DRIVER, 0);
        DriverAccount driver7 = new DriverAccount("Vozac7", "Vozi7", "2", "vozac@2", "BMW", ACCOUNT_TYPE.DRIVER, 0);

        Account passenger1 = new PassengerAccount("Putnik1", "Putuje1", "2", "putnik@1", ACCOUNT_TYPE.PASSENGER);

        driver1.Add_Ride(new Ride(ID,"Belgrade", "Novi Sad", "Danas", "Sutra", 2));
        driver2.Add_Ride(new Ride(ID,"Paracin", "Nis", "Ponedeljak", "Utorak",3));
        driver3.Add_Ride(new Ride(ID,"Cuprija", "Jagodina", "Subota", "Nedelja", 1));
        driver4.Add_Ride(new Ride(ID,"Kraljevo", "Kragujevac", "Danas", "Nakosutra", 4));
        driver5.Add_Ride(new Ride(ID,"Krusevac", "Presevo", "15:00", "20:00", 2));
        driver6.Add_Ride(new Ride(ID,"Subotica", "Budapest", "08:00", "20:00", 1));
        driver7.Add_Ride(new Ride(ID,"Kuca", "Poso", "Danas", "Kasnije Danas", 3));
        driver7.Add_Ride(new Ride(ID,"Kuca", "Poso", "Danas", "Kasnije Danas", 3));


        All_Accounts.add(driver1);
        All_Accounts.add(driver2);
        All_Accounts.add(driver3);
        All_Accounts.add(driver4);
        All_Accounts.add(driver5);
        All_Accounts.add(driver6);
        All_Accounts.add(driver7);
        All_Accounts.add(passenger1);
        Toast.makeText(this, String.valueOf(All_Accounts.size()), Toast.LENGTH_LONG).show();

        Dummy_Data_Added = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("SPARTAAAAAAAAAAAAAAAAAA", String.valueOf(All_Accounts.size()));
        if(!Dummy_Data_Added){

            Add_Dummy_Data();
        }

        Log_In();
    }

    private boolean User_Exists(ArrayList<Account> list, String name_Query) {
        for (Account acc : list) {
            if (acc.getName().equalsIgnoreCase(name_Query)) {
                Acc_Name = acc.getName();
                type = acc.getACCOUNT_TYPE();
                return true;
            }
        }
        return false;
    }

    private void Driver_Dash() {
        Intent intent = new Intent(this, DriverDash.class);
        intent.putExtra("Account", Acc_Name);
        startActivity(intent);
    }

    private void Passenger_Dash() {
        Intent intent = new Intent(this, PassengerDash.class);
        startActivity(intent);
    }

    private void Log_In() {
        setContentView(R.layout.activity_start);

        final EditText Input_name = findViewById(R.id.Input_Name);
        Button Login_Button = findViewById(R.id.Login_Button);

        Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (User_Exists(All_Accounts, Input_name.getText().toString())) {
                    Toast.makeText(Start.this, "Account Exists", Toast.LENGTH_LONG).show();
                    if (type == ACCOUNT_TYPE.PASSENGER) {
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

        final EditText New_Account_Name = findViewById(R.id.New_Name);
        final EditText New_Account_Lastname = findViewById(R.id.New_Lastname);
        final EditText New_Account_Phone = findViewById(R.id.New_Phone);
        final EditText New_Account_Email = findViewById(R.id.New_Email);
        Button Register = findViewById(R.id.Register_Button);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = New_Account_Name.getText().toString();
                String lastname = New_Account_Lastname.getText().toString();
                String phone = New_Account_Phone.getText().toString();
                String Email = New_Account_Email.getText().toString();
                if (spinner.getSelectedItemPosition() == 0) {
                    showAddItemDialog(Start.this);
                    Register_New_Driver(name, lastname, phone, Email, Car_model);
                } else {
                    Register_New_Passenger(name, lastname, phone, Email);
                }
            }
        });
    }

    private void Register_New_Passenger(String name, String lastname, String email, String phone) {
        All_Accounts.add(new PassengerAccount(name, lastname, phone, email, ACCOUNT_TYPE.PASSENGER));
    }

    private void Register_New_Driver(String name, String lastname, String email, String phone, String model) {
        All_Accounts.add(new DriverAccount(name, lastname, phone, email, model, ACCOUNT_TYPE.DRIVER, 0));
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
