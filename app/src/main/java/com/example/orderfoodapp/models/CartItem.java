package com.example.orderfoodapp.models;

import com.google.gson.annotations.SerializedName;

public class CartItem {

    @SerializedName("_id")
    String id;

    @SerializedName("product")
    String product;

    @SerializedName("quantity")
    String quantity;

    @SerializedName("total")
    String total;

    @SerializedName("cart")
    String cart;

    public CartItem(String product, String quantity) {
        this.product = product;
        this.quantity = quantity;
    }


    public CartItem(String id, String product, String quantity, String total, String cart) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
        this.cart = cart;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", quantity='" + quantity + '\'' +
                ", total='" + total + '\'' +
                ", cart='" + cart + '\'' +
                '}';
    }
}
