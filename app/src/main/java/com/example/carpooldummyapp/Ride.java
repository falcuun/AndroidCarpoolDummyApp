package com.example.carpooldummyapp;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Ride implements Serializable {
    private int _ID;

    private String Departure_Location;
    private String Arrival_Location;
    private String Departure_Time;
    private String Arrival_Time;
    private DriverAccount driver;
    private int Free_Seats;

    public Ride(int ID, String Departure_Location, String Arrival_Location, String Departure_Time, String Arrival_Time, int Free_Seats, DriverAccount driver) {
        this._ID = ID;
        this.Departure_Location = Departure_Location;
        this.Arrival_Location = Arrival_Location;
        this.Departure_Time = Departure_Time;
        this.Arrival_Time = Arrival_Time;
        this.Free_Seats = Free_Seats;
        this.driver = driver;
        Start.ID++;
    }

    public void setDriver(DriverAccount driver) {
        this.driver = driver;
    }

    DriverAccount getDriver() {
        return this.driver;
    }

    int get_ID() {
        return _ID;
    }

    void set_ID(int _ID) {
        this._ID = _ID;
    }


    String getDeparture_Time() {
        return Departure_Time;
    }

    void setDeparture_Time(String Departure_Time) {
        this.Departure_Time = Departure_Time;
    }

    String getArrival_Time() {
        return Arrival_Time;
    }

    void setArrival_Time(String Arrival_Time) {
        this.Arrival_Time = Arrival_Time;
    }

    String getDeparture_Location() {
        return Departure_Location;
    }

    void setDeparture_Location(String Departure_Location) {
        this.Departure_Location = Departure_Location;
    }

    String getArrival_Location() {
        return Arrival_Location;
    }

    void setArrival_Location(String Arrival_Location) {
        this.Arrival_Location = Arrival_Location;
    }

    @Override
    public String toString() {
        return Departure_Location + '\n' + Arrival_Location + '\n'
                + Departure_Time + '\n' + Arrival_Time + '\n';
    }
}
