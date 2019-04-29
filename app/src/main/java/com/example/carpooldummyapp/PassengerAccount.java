package com.example.carpooldummyapp;

public class PassengerAccount extends Account {

    private ACCOUNT_TYPE Account_Type = ACCOUNT_TYPE.PASSENGER;


    public PassengerAccount(String name, String lastname, String phone, String Email, int rating, ACCOUNT_TYPE account_type) {
        super(name, lastname, phone, Email, rating, account_type);
    }



}
