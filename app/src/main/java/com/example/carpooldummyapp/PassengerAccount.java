package com.example.carpooldummyapp;

public class PassengerAccount extends Account {

    private ACCOUNT_TYPE tip = ACCOUNT_TYPE.PASSENGER;
    private int Rating = 0;

    public PassengerAccount(String name, String lastname, String phone, String Email, ACCOUNT_TYPE account_type) {
        super(name, lastname, phone, Email, account_type);
    }

    public void Good_Rating(){
        Rating += 1;
    }

    public void Bad_Rating(){
        Rating -= 1;
    }


}
