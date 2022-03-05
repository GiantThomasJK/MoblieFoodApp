package com.example.orderfoodapp.models;

import com.google.gson.annotations.SerializedName;

public class Forgot {

    @SerializedName("email")
    private String email;


    public Forgot(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
