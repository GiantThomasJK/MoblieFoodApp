package com.example.orderfoodapp.API;

import com.example.orderfoodapp.models.CartItem;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.http.GET;

public class ArrayCart {

    @SerializedName("cartitem")
    private ArrayList<CartItem> cartItems;

    public ArrayCart(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
