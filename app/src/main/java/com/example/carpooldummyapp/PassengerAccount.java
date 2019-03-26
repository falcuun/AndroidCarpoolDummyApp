package com.example.carpooldummyapp;

public class PassengerAccount extends Account {

    private TIP_NALOGA tip = TIP_NALOGA.PUTNIK;
    private int Reputacija = 0;

    public PassengerAccount(String ime, String prezime, String telefon, String Email) {
        super(ime, prezime, telefon, Email);
    }

    public void Dobra_Reputacija(){
        Reputacija += 1;
    }

    public void Losa_Reputacija(){
        Reputacija -= 1;
    }


}
