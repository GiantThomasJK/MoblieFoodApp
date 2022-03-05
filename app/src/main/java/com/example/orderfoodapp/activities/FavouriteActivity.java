package com.example.orderfoodapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.adapters.FavouriteAdapter;
import com.example.orderfoodapp.models.FavoriteModel;
import com.example.orderfoodapp.models.FeaturedVerModel;
import com.example.orderfoodapp.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteActivity extends AppCompatActivity {


    FavouriteAdapter favouriteAdapter;
    RecyclerView fav_rec;
    ArrayList<FeaturedVerModel> featuredVerModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        fav_rec = findViewById(R.id.favouvire_rec);


        featuredVerModels = new ArrayList<>();

        fav_rec.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        fav_rec.setHasFixedSize(true);
        fav_rec.setNestedScrollingEnabled(true);



        Log.d("iduser",LoginActivity.usercurrent.get_id());
        APIController.apiService.getfavorite(LoginActivity.usercurrent.get_id()).enqueue(new Callback<List<HomeVerModel>>() {
            @Override
            public void onResponse(Call<List<HomeVerModel>> call, Response<List<HomeVerModel>> response) {

                Log.d("Success","success");
                List<HomeVerModel> list = response.body();
                for(HomeVerModel homeVerModel:list){
                    featuredVerModels.add(new FeaturedVerModel(homeVerModel.getImage(),homeVerModel.getName(),"$ "+homeVerModel.getPrice(),homeVerModel.getRating(), homeVerModel.getTiming()));
                }
            }

            @Override
            public void onFailure(Call<List<HomeVerModel>> call, Throwable t) {
                Log.d("Failed",t.getMessage());
            }
        });

        favouriteAdapter = new FavouriteAdapter(featuredVerModels);
        fav_rec.setAdapter(favouriteAdapter);





    }
}