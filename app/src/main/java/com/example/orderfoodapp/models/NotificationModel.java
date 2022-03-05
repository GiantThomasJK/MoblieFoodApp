package com.example.orderfoodapp.models;

import com.google.gson.annotations.SerializedName;

public class NotificationModel {

    @SerializedName("_id")
    private String _id;

    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private String address;

    @SerializedName("phone")
    private String phone;

    @SerializedName("user")
    private String user;


    @SerializedName("total")
    private double total;

    @SerializedName("total_all")
    private double total_all;

    @SerializedName("ship")
    private int ship;

    @SerializedName("status")
    private String status;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTotal(float total) {
        this.total = total;
    }



    public void setTotal_all(float total_all) {
        this.total_all = total_all;
    }

    public int getShip() {
        return ship;
    }

    public void setShip(int ship) {
        this.ship = ship;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public NotificationModel(String _id, String name, String address, String phone, double total, double total_all, int ship, String status) {
        this._id = _id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.total = total;
        this.total_all = total_all;
        this.ship = ship;
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal_all() {
        return total_all;
    }

    public void setTotal_all(double total_all) {
        this.total_all = total_all;
    }

    //    public NotificationModel(String image, String name, String price, String qty, String status) {
//        this.image = image;
//        this.name = name;
//        this.price = price;
//        this.qty = qty;
//        this.status = status;
//    }



}
