package com.example.orderfoodapp.API;

import com.example.orderfoodapp.models.CartItem;
import com.example.orderfoodapp.models.FavoriteModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    @SerializedName("_id")
    private String _id;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("password")
    private String password;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("cart")
    private String idcart;

    @SerializedName("favorite")
    private ArrayList<String> favoriteitem;

    @SerializedName("order")
    private ArrayList<String> orderitem;


    public User(String password) {
        this.password = password;
    }



    public User(String fullname, String password, String phone, String email) {
        this.fullname = fullname;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public User(String fullname, String password, String phone, String email, String idcart) {
        this.fullname = fullname;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.idcart = idcart;
    }

    public User(String _id, String fullname, String password, String phone, String email, String idcart, ArrayList<String> favoriteitem, ArrayList<String> orderitem) {
        this._id = _id;
        this.fullname = fullname;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.idcart = idcart;
        this.favoriteitem = favoriteitem;
        this.orderitem = orderitem;
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIdcart() {
        return idcart;
    }

    public void setIdcart(String idcart) {
        this.idcart = idcart;
    }

    public ArrayList<String> getFavoriteitem() {
        return favoriteitem;
    }

    public void setFavoriteitem(ArrayList<String> favoriteitem) {
        this.favoriteitem = favoriteitem;
    }

    public ArrayList<String> getOrderitem() {
        return orderitem;
    }

    public void setOrderitem(ArrayList<String> orderitem) {
        this.orderitem = orderitem;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullname='" + fullname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
