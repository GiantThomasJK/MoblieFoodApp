package com.example.orderfoodapp.API;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class cate {

    @SerializedName("categories")
    private ArrayList<Categories> categories;

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }
}
