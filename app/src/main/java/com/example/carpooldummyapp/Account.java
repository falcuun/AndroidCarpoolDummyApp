package com.example.carpooldummyapp;

enum TIP_NALOGA {VOZAC, PUTNIK}


public class Account {
    private String Ime;
    private String Prezime;
    private String Telefon;
    private String Email;

    public Account(String ime, String prezime, String telefon, String Email) {
        Ime = ime;
        Prezime = prezime;
        Telefon = telefon;
    }

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
