package com.example.carpooldummyapp;

public class Voznja {
    private String Polazno_Mesto;
    private String Dolazno_Mesto;
    private String Vreme_Polaska;
    private String Vreme_Dolaska;

    public Voznja(String polazno_Mesto, String dolazno_Mesto, String vreme_Polaska, String vreme_Dolaska) {
        Polazno_Mesto = polazno_Mesto;
        Dolazno_Mesto = dolazno_Mesto;
        Vreme_Polaska = vreme_Polaska;
        Vreme_Dolaska = vreme_Dolaska;
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
    public String toString(){
        return "Polazno Mesto je: " + Polazno_Mesto + " Dolazno Mesto je: " + Dolazno_Mesto
                + " Vreme Polaska je: " + Vreme_Polaska + " Ocekivano Vreme Dolaska je: " + Vreme_Dolaska;
    }
}
