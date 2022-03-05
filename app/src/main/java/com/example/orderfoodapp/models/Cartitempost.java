package com.example.orderfoodapp.models;

import com.google.gson.annotations.SerializedName;

public class Cartitempost {
    @SerializedName("product")
    String product;

    @SerializedName("quantity")
    String quantity;

    public Cartitempost(String product, String quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
