package com.example.orderfoodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderfoodapp.API.ArrayCart;
import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.models.CartItem;
import com.example.orderfoodapp.models.Cartitempost;
import com.example.orderfoodapp.models.HomeVerModel;
import com.example.orderfoodapp.models.RecommendModel;
import com.example.orderfoodapp.ui.MyCartFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuDetailActivity extends AppCompatActivity {


    TextView dtlName , dtlrating, dtlPrice, dtlDiscrip ,dltTiming , dtlQuantity;
    ImageView imageView;
    Button btnAddtocart, btnBuynow;
    int count = 0;
    public static HomeVerModel item;
    public static RecommendModel rcmitem;
    public static List<HomeVerModel> items = new ArrayList<>();
    public static List<RecommendModel> rcmitems = new ArrayList<>();
    public static List<String> names= new ArrayList<>();
    public static List<String> quality = new ArrayList<>();
    public static double tongtien = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        dtlName = findViewById(R.id.name_dtl);
        dtlrating = findViewById(R.id.rating);
        dtlPrice = findViewById(R.id.price_dtl);
        dtlDiscrip = findViewById(R.id.description);
        dltTiming = findViewById(R.id.timing);
        dtlQuantity = findViewById(R.id.dtlquantity);
        imageView = findViewById(R.id.img_detail);
        btnAddtocart = findViewById(R.id.btnAddtoCart);
        btnBuynow = findViewById(R.id.buy_now_dtl);

        Intent intent = getIntent();
        if(intent!=null){
            if(intent.hasExtra("item")){
                item = intent.getParcelableExtra("item");
                Picasso.get().load(item.getImage()).into(imageView);
                dtlName.setText(item.getName());
                dtlrating.setText(item.getRating());
                dltTiming.setText(item.getTiming());
                dtlDiscrip.setText(item.getDescription());
                dtlPrice.setText(item.getPrice() +" $");
            }if (intent.hasExtra("rcmitem")){
                rcmitem = intent.getParcelableExtra("rcmitem");
                Picasso.get().load(rcmitem.getImage()).into(imageView);
                dtlName.setText(rcmitem.getName());
                dtlrating.setText(rcmitem.getRating());
                dltTiming.setText(rcmitem.getTiming());
                dtlDiscrip.setText(rcmitem.getDescription());
                dtlPrice.setText(rcmitem.getPrice() +" $");
            }
        }

//        data();
        btnAddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("item",dtlQuantity.getText().toString());
                APIController.apiService.addmycart(LoginActivity.usercurrent.getIdcart(),
                        new Cartitempost(item.get_id(),dtlQuantity.getText().toString())).enqueue(new Callback<List<CartItem>>() {
                    @Override
                    public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
                        Toast.makeText(MenuDetailActivity.this,"Successfully added to cart",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<List<CartItem>> call, Throwable t) {

                    }
                });

            }
        });

        btnBuynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("item",dtlQuantity.getText().toString());
                APIController.apiService.addmycart(LoginActivity.usercurrent.getIdcart(),
                        new Cartitempost(item.get_id(),dtlQuantity.getText().toString())).enqueue(new Callback<List<CartItem>>() {
                    @Override
                    public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
                        Toast.makeText(MenuDetailActivity.this,"Successfully added to cart",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MenuDetailActivity.this, MyCartFragment.class);
                        startActivity(intent1);

                    }

                    @Override
                    public void onFailure(Call<List<CartItem>> call, Throwable t) {

                    }
                });
            }
        });


    }
    private  void data(){
       Picasso.get().load(item.getImage()).into(imageView);
       dtlName.setText(item.getName());
       dtlrating.setText(item.getRating());
       dltTiming.setText(item.getTiming());
       dtlDiscrip.setText(item.getDescription());
       dtlPrice.setText(item.getPrice() +" $");

    }

    public void back(View view) {

        finish();

    }




    public void decrement(View view) {
        if (count <= 0)
            count = 1 ;
        else
         count--;

        dtlQuantity.setText("" + count);
    }

    public void increment(View view) {
        count++;
        dtlQuantity.setText("" + count);
    }
}