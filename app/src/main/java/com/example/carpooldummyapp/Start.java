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
    String Car_model;
    ACCOUNT_TYPE type;
    public static int ID = 0;

    static boolean Dummy_Data_Added = false;

    void Add_Dummy_Data() {
        DriverAccount driver1 = new DriverAccount("Bradley", "Cooper", "1", "driver@1", "BMW", 1.5f, ACCOUNT_TYPE.DRIVER, 2);
        DriverAccount driver2 = new DriverAccount("John", "Cena", "2", "driver@2", "BMW", 3.7f, ACCOUNT_TYPE.DRIVER, 3);
        DriverAccount driver3 = new DriverAccount("Donald", "Trump", "2", "driver@2", "BMW", 5, ACCOUNT_TYPE.DRIVER,1);
        DriverAccount driver4 = new DriverAccount("Driver4", "Drives4", "2", "driver@2", "BMW", 1.2f, ACCOUNT_TYPE.DRIVER,3);
        DriverAccount driver5 = new DriverAccount("Driver5", "Drives5", "2", "driver@2", "BMW", 4.7f, ACCOUNT_TYPE.DRIVER,4);
        DriverAccount driver6 = new DriverAccount("Driver6", "Drives6", "2", "driver@2", "BMW", 0, ACCOUNT_TYPE.DRIVER,1);
        DriverAccount driver7 = new DriverAccount("Driver7", "Drives7", "2", "driver@2", "BMW", 0, ACCOUNT_TYPE.DRIVER, 1);

        Account passenger1 = new PassengerAccount("Passenger1", "Passenger1", "2", "passenger@1", 0, ACCOUNT_TYPE.PASSENGER);

        driver1.Add_Ride(new Ride(ID, "Belgrade", "Budapest", "12.02.2019", "12.02.2019", 2, driver1));
        driver2.Add_Ride(new Ride(ID, "Budapest", "Bratislava", "13.02.2019", "13.02.2019", 3, driver2));
        driver3.Add_Ride(new Ride(ID, "Bratislava", "Prague", "13.02.2019", "14.02.2019", 1, driver3));
        driver4.Add_Ride(new Ride(ID, "Prague", "Berlin", "14.02.2019", "15.02.2019", 4, driver4));
        driver5.Add_Ride(new Ride(ID, "Berlin", "Copenhagen", "16.02.2019", "17.02.2019", 2, driver5));
        driver6.Add_Ride(new Ride(ID, "Copenhagen", "Stockholm", "17.02.2019", "19.02.2019", 1, driver6));
        driver7.Add_Ride(new Ride(ID, "Stockholm", "Oslo", "25.03.2019", "26.03.2019", 3, driver7));
        driver7.Add_Ride(new Ride(ID, "Oslo", "Belgrade", "12.11.2019", "13.11.2019", 3, driver7));


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
        if (!Dummy_Data_Added) {
            Add_Dummy_Data();
        }

        Log_In();
        Create_New_User();
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
                }
            }
        });
    }

    private void Create_New_User() {

        Button New_User_Button = findViewById(R.id.New_User_Button);
        New_User_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start.this.setContentView(R.layout.register_new_account);
                spinner = findViewById(R.id.spinner1);
                String[] types = {"Driver", "Passenger"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        Start.this, android.R.layout.simple_spinner_item, types);

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
                            Register_New_Driver(name, lastname, phone, Email, Car_model, 3);
                        } else {
                            Register_New_Passenger(name, lastname, phone, Email);
                        }
                    }
                });
            }
        });

    }

    private void Register_New_Passenger(String name, String lastname, String email, String phone) {
        All_Accounts.add(new PassengerAccount(name, lastname, phone, email, 0,ACCOUNT_TYPE.PASSENGER));
    }

    private void Register_New_Driver(String name, String lastname, String email, String phone, String model, int free_spaces) {
        All_Accounts.add(new DriverAccount(name, lastname, phone, email, model,0, ACCOUNT_TYPE.DRIVER, free_spaces));
    }


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
