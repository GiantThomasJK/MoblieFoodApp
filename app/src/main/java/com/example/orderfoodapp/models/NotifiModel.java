package com.example.orderfoodapp.models;

public class NotifiModel {
    int image;
    String name;
    String price;
    String rating;
    String quantity;
    String btn;

    public NotifiModel(int image, String name, String price, String rating, String quantity, String btn) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.quantity = quantity;
        this.btn = btn;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }


    public void setImage() {
        this.image = image;

    }
}
