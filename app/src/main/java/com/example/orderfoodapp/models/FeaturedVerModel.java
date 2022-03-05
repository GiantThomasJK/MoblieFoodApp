package com.example.orderfoodapp.models;

public class FeaturedVerModel {
    String image;
    String name;
    String price;
    String rating;
    String timing;

    public FeaturedVerModel(String image, String name, String price, String rating, String timing) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.timing = timing;
    }

    public FeaturedVerModel(String image, String name, String price, String rating) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }
}
