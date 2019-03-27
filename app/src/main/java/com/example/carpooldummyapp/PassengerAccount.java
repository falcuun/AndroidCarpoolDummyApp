package com.example.carpooldummyapp;

public class PassengerAccount extends Account {

    private TIP_NALOGA tip = TIP_NALOGA.PUTNIK;
    private int Reputacija = 0;

    public PassengerAccount(String ime, String prezime, String telefon, String Email, TIP_NALOGA tip_naloga) {
        super(ime, prezime, telefon, Email, tip_naloga);
    }

    public void Dobra_Reputacija(){
        Reputacija += 1;
    }

    public void Losa_Reputacija(){
        Reputacija -= 1;
    }


}
