package com.example.orderfoodapp.models;

import com.example.orderfoodapp.API.Categories;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Products {

    @SerializedName("products")
    private ArrayList<HomeVerModel> products;

    public Products(ArrayList<HomeVerModel> products) {
        this.products = products;
    }

    public ArrayList<HomeVerModel> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<HomeVerModel> products) {
        this.products = products;
    }
}
