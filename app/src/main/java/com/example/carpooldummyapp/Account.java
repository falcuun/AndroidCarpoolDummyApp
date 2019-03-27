package com.example.carpooldummyapp;

import java.io.Serializable;

enum TIP_NALOGA {VOZAC, PUTNIK}


public class Account implements Serializable {
    private String Ime;
    private String Prezime;
    private String Telefon;
    private String Email;
    TIP_NALOGA tip_naloga;

    public Account(String ime, String prezime, String telefon, String email, TIP_NALOGA tip_naloga) {
        Ime = ime;
        Prezime = prezime;
        Telefon = telefon;
        Email = email;
        this.tip_naloga = tip_naloga;
    }

    public TIP_NALOGA getTip_naloga() {
        return tip_naloga;
    }

    public void setTip_naloga(TIP_NALOGA tip_naloga) {
        this.tip_naloga = tip_naloga;
    }

    public Account(){}

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        Prezime = prezime;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
