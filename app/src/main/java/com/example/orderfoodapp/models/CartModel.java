package com.example.orderfoodapp.models;

public class CartModel {

    int image;
    String name;
    String price;
    String qty;

    public CartModel(int image, String name, String price, String qty) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
