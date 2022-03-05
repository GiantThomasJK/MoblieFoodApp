package com.example.orderfoodapp.models;

import com.google.gson.annotations.SerializedName;

public class FavoriteModel {

    @SerializedName("favorite")
     String favorite;

    public FavoriteModel(String favorite) {
        this.favorite = favorite;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
