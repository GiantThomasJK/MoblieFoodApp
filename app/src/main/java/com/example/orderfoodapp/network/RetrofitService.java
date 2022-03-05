package com.example.orderfoodapp.network;

import com.example.orderfoodapp.models.Categories;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("categories")
    Call<java.util.List<Categories>> getCategories();

}
