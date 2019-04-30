package com.example.carpooldummyapp;

import java.util.ArrayList;

public class DriverAccount extends Account {

    private String Vehicle_Model;
    private ArrayList<Ride> Scheduled_Rides = new ArrayList<>();

    private ACCOUNT_TYPE account_type = ACCOUNT_TYPE.DRIVER;
    private float Rating;
    private int Free_Seats;
    
    public ACCOUNT_TYPE getAccount_type() {
        return account_type;
    }

    public void setAccount_type(ACCOUNT_TYPE account_type) {
        this.account_type = account_type;
    }


    DriverAccount(String name, String surname, String phone, String Email, String Vehicle_Model, float Rating, ACCOUNT_TYPE account_type, int free_seats) {
        super(name, surname, phone, Email, Rating, account_type);
        this.Vehicle_Model = Vehicle_Model;
        this.Rating = Rating;
        this.Free_Seats = free_seats;
    }

    void Add_Ride(Ride ride){
        Scheduled_Rides.add(ride);
    }

    void Reduce_Seats(){
        if(Free_Seats <= 0){
            Free_Seats = 0;
        } else {
            Free_Seats--;
        }
    }

    public void Increase_Seats(){
        Free_Seats++;
    }

    public String getVehicle_Model() {
        return Vehicle_Model;
    }

    public void setVehicle_Model(String Vehicle_Model) {
        Vehicle_Model = Vehicle_Model;
    }

    ArrayList<Ride> getScheduled_Rides() {
        return Scheduled_Rides;
    }

    public void setScheduled_Rides(ArrayList<Ride> Scheduled_Rides) {
        this.Scheduled_Rides = Scheduled_Rides;
    }
}
