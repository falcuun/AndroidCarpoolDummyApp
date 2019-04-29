package com.example.carpooldummyapp;

import java.io.Serializable;

enum ACCOUNT_TYPE {DRIVER, PASSENGER}


public class Account implements Serializable {
    private String Name;
    private String Surname;
    private String Phone;
    private String Email;
    private int Rating;

    public int getRating() {
        return Rating / 5;
    }

    public void addRating(int rating) {
        Rating += rating;
    }

    ACCOUNT_TYPE ACCOUNT_TYPE;

    public Account(String Name, String Surname, String Phone, String email, int rating, ACCOUNT_TYPE ACCOUNT_TYPE) {
        this.Name = Name;
        this.Surname = Surname;
        this.Phone = Phone;
        this.Email = email;
        this.ACCOUNT_TYPE = ACCOUNT_TYPE;
        this.Rating = rating;
    }

    public ACCOUNT_TYPE getACCOUNT_TYPE() {
        return ACCOUNT_TYPE;
    }

    public void setACCOUNT_TYPE(ACCOUNT_TYPE ACCOUNT_TYPE) {
        this.ACCOUNT_TYPE = ACCOUNT_TYPE;
    }

    public Account(){}

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }
}
