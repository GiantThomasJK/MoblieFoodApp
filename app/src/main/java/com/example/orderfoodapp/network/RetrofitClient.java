package com.example.orderfoodapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    Retrofit retrofit = null;
    public static String base_url ="https://giahaotran-github-io-y592o.ondigitalocean.app/";

   public  Retrofit getClient(String baseUrl){
       if (retrofit == null){
           retrofit = new Retrofit.Builder()
                   .baseUrl(base_url)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
       }
       return retrofit;
   }
}
