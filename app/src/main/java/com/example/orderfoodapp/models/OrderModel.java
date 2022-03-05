package com.example.orderfoodapp.models;

import com.google.gson.annotations.SerializedName;

public class OrderModel {
    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private String address;

    @SerializedName("phone")
    private String phone;

    @SerializedName("user")
    private String user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("total")
    private float total;

    @SerializedName("total_all")
    private float total_all;

    @SerializedName("status")
    private float status;

    public OrderModel(String id, String name, String address, String phone, float total, float total_all, float status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.total = total;
        this.total_all = total_all;
        this.status = status;
    }

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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getTotal_all() {
        return total_all;
    }

    public void setTotal_all(float total_all) {
        this.total_all = total_all;
    }

    public OrderModel() {
    }

    public OrderModel(String name, String address, String phone, float total) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.total = total;
    }


}
