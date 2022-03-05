package com.example.orderfoodapp.models;

import com.example.orderfoodapp.API.User;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginRespone {

    @SerializedName("users")
    private ArrayList<User> users;

    public LoginRespone(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
