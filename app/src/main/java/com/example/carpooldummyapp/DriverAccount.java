package com.example.carpooldummyapp;

import java.util.ArrayList;

public class DriverAccount extends Account {

    private String Vehicle_Model;
    private ArrayList<Ride> Scheduled_Rides = new ArrayList<>();

    private ACCOUNT_TYPE account_type = ACCOUNT_TYPE.DRIVER;
    private int Rating = 0;
    
    public ACCOUNT_TYPE getAccount_type() {
        return account_type;
    }

    public void setAccount_type(ACCOUNT_TYPE account_type) {
        this.account_type = account_type;
    }


    public DriverAccount(String name, String surname, String phone, String Email, String Vehicle_Model, int Rating, ACCOUNT_TYPE account_type) {
        super(name, surname, phone, Email, Rating, account_type);
        this.Vehicle_Model = Vehicle_Model;
        this.Rating = Rating;
    }

    public void Add_Ride(Ride ride){
        Scheduled_Rides.add(ride);
    }

    public String getVehicle_Model() {
        return Vehicle_Model;
    }

    public void setVehicle_Model(String Vehicle_Model) {
        Vehicle_Model = Vehicle_Model;
    }

    public ArrayList<Ride> getScheduled_Rides() {
        return Scheduled_Rides;
    }

    public void setScheduled_Rides(ArrayList<Ride> Scheduled_Rides) {
        Scheduled_Rides = Scheduled_Rides;
    }


    public void Good_Rating(){
        Rating += 1;
    }

    public void Bad_Rating(){
        Rating -= 1;
    }
}
