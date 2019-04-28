package com.example.carpooldummyapp;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Ride implements Serializable {
    private int _ID;

    private String Departure_Location;
    private String Arrival_Location;
    private String Departure_Time;
    private String Arrival_Time;

    private int Free_Seats;

    public Ride(int ID, String Departure_Location, String Arrival_Location, String Departure_Time, String Arrival_Time, int Free_Seats) {
        this._ID = ID;
        this.Departure_Location = Departure_Location;
        this.Arrival_Location = Arrival_Location;
        this.Departure_Time = Departure_Time;
        this.Arrival_Time = Arrival_Time;
        this.Free_Seats = Free_Seats;
        Start.ID++;
    }


    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }


    public String getDeparture_Time() {
        return Departure_Time;
    }

    public void setDeparture_Time(String Departure_Time) {
        this.Departure_Time = Departure_Time;
    }

    public String getArrival_Time() {
        return Arrival_Time;
    }

    public void setArrival_Time(String Arrival_Time) {
        this.Arrival_Time = Arrival_Time;
    }

    public String getDeparture_Location() {
        return Departure_Location;
    }

    public void setDeparture_Location(String Departure_Location) {
        this.Departure_Location = Departure_Location;
    }

    public String getArrival_Location() {
        return Arrival_Location;
    }

    public void setArrival_Location(String Arrival_Location) {
        this.Arrival_Location = Arrival_Location;
    }

    @Override
    public String toString() {
        return "Departure Location is: " + Departure_Location + '\n' + "Arrival Location is: " + Arrival_Location + '\n'
                + "Departure Time is: " + Departure_Time + '\n' + "Arrival Time is: " + Arrival_Time + '\n';
    }
}
