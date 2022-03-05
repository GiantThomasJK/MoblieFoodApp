package com.example.orderfoodapp.models;

import com.google.gson.annotations.SerializedName;

public class OrderItem {
    @SerializedName("product")
    private String product;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("total")
    private double total;

    @SerializedName("_id")
    private String id;

    @SerializedName("order")
    private String order;

    public OrderItem(String product, int quantity, double total, String id, String order) {
        this.product = product;
        this.quantity = quantity;
        this.total = total;
        this.id = id;
        this.order = order;
    }

    public OrderItem(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
