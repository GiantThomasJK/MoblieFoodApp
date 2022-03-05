package com.example.orderfoodapp.API;

import com.google.gson.annotations.SerializedName;

public class Categories {
    @SerializedName("name")
    private String name;

    @SerializedName("_id")
    private String _id;

    @SerializedName("picture")
    private String picture;

    public Categories(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getname() {
        return name;
    }
    public String getPicture() {
        return picture;
    }


}
