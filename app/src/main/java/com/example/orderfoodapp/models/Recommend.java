package com.example.orderfoodapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Recommend {

    @SerializedName("products")
    private ArrayList<RecommendModel> products;

    public Recommend(ArrayList<RecommendModel> products) {
        this.products = products;
    }

    public ArrayList<RecommendModel> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<RecommendModel> products) {
        this.products = products;
    }
}
