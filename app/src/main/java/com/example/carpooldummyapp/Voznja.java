package com.example.carpooldummyapp;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Voznja implements Serializable {
    private int _ID;

    private String Polazno_Mesto;
    private String Dolazno_Mesto;
    private String Vreme_Polaska;
    private String Vreme_Dolaska;
    private Mesto Polazno_Mesto_m;
    private Mesto Dolazno_Mesto_m;

    public Mesto getPolazno_Mesto_m() {
        return Polazno_Mesto_m;
    }

    public Mesto getDolazno_Mesto_m() {
        return Dolazno_Mesto_m;
    }

    private int Slobodnih_Mesta;

    public Voznja(int ID, String polazno_Mesto, String dolazno_Mesto, String vreme_Polaska, String vreme_Dolaska, int slobodnih_Mesta) {
        this._ID = ID;
        Polazno_Mesto = polazno_Mesto;
        Dolazno_Mesto = dolazno_Mesto;
        Vreme_Polaska = vreme_Polaska;
        Vreme_Dolaska = vreme_Dolaska;
        Slobodnih_Mesta = slobodnih_Mesta;
        Start.ID++;
    }


    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }


    public String getVreme_Polaska() {
        return Vreme_Polaska;
    }

    public void setVreme_Polaska(String vreme_Polaska) {
        Vreme_Polaska = vreme_Polaska;
    }

    public String getVreme_Dolaska() {
        return Vreme_Dolaska;
    }

    public void setVreme_Dolaska(String vreme_Dolaska) {
        Vreme_Dolaska = vreme_Dolaska;
    }

    public String getPolazno_Mesto() {
        return Polazno_Mesto;
    }

    public void setPolazno_Mesto(String polazno_Mesto) {
        Polazno_Mesto = polazno_Mesto;
    }

    public String getDolazno_Mesto() {
        return Dolazno_Mesto;
    }

    public void setDolazno_Mesto(String dolazno_Mesto) {
        Dolazno_Mesto = dolazno_Mesto;
    }

    @Override
    public String toString() {
        return "Polazno Mesto je: " + Polazno_Mesto + '\n' + "Dolazno Mesto je: " + Dolazno_Mesto + '\n'
                + "Vreme Polaska je: " + Vreme_Polaska + '\n' + "Ocekivano Vreme Dolaska je: " + Vreme_Dolaska + '\n';
    }
}
