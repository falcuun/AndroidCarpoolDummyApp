package com.example.carpooldummyapp;

import java.util.ArrayList;

public class DriverAccount extends Account {

    private String Model_Vozila;
    private ArrayList<Voznja> Trenutne_Voznje;
    private TIP_NALOGA tip = TIP_NALOGA.VOZAC;
    private int Reputacija = 0;

    public DriverAccount(String ime, String prezime, String telefon, String Email, String model_Vozila) {
        super(ime, prezime, telefon, Email);
        this.Model_Vozila = model_Vozila;
    }

    public String getModel_Vozila() {
        return Model_Vozila;
    }

    public void setModel_Vozila(String model_Vozila) {
        Model_Vozila = model_Vozila;
    }

    public ArrayList<Voznja> getTrenutne_Voznje() {
        return Trenutne_Voznje;
    }

    public void setTrenutne_Voznje(ArrayList<Voznja> trenutne_Voznje) {
        Trenutne_Voznje = trenutne_Voznje;
    }


    public void Dobra_Reputacija(){
        Reputacija += 1;
    }

    public void Losa_Reputacija(){
        Reputacija -= 1;
    }
}
