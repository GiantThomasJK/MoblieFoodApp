package com.example.orderfoodapp.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.orderfoodapp.API.ArrayCart;
import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.activities.LoginActivity;
import com.example.orderfoodapp.activities.MenuDetailActivity;
import com.example.orderfoodapp.activities.NotificationActivity;
import com.example.orderfoodapp.activities.PaymentActivity;
import com.example.orderfoodapp.adapters.CartAdapter;
import com.example.orderfoodapp.adapters.IOnClickItemCart;
import com.example.orderfoodapp.models.CartItem;
import com.example.orderfoodapp.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyCartFragment extends Fragment {

    Button buynow;
    public static TextView total_price;
    int overAllTotalAmount;

    public static List<CartItem> list;
    public static List<CartItem> list2;
    public static CartAdapter cartAdapter;
    RecyclerView recyclerView;
    List<HomeVerModel> cart;

    int overTotalPrice = 0;
    public MyCartFragment() {
        // Required empty public constructor
    }

    IOnClickItemCart iOnClickItemCart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);
        recyclerView = view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        total_price = view.findViewById(R.id.total_price);

        buynow = view.findViewById(R.id.buynow);

        list = new ArrayList<>();
        list2 = new ArrayList<>();

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(intent);

            }
        });

        Tongtien();

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                String tien = "0 $";
                list.remove(position);
                cartAdapter.updateL(list);
                total_price.setText(tien);
            }
        });

        cartAdapter = new CartAdapter(getContext(),list);

        cartAdapter.setiOnClickItemCart(new IOnClickItemCart() {
            @Override
            public void iClickItemCart(int i, float price) {
                float tong=0;
                    if(i>=0){
                        list2.add(list.get(i));
                        tong+=price;
                        total_price.setText(tong+"");
                    }
                    else{
                        list2.remove(-100-i);
                        if (tong>price)
                            tong-=price;
                        total_price.setText(tong+"");
                    }
                }


        });

        APIController.apiService.getmycart(LoginActivity.usercurrent.getIdcart()).enqueue(new Callback<List<CartItem>>() {
            @Override
            public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
                Log.d("mycart",LoginActivity.usercurrent.getIdcart());
                //list.clear();
                List<CartItem> lists = response.body();
                long tongtien = 0;
                for (CartItem cartItem:lists)
                {
                    list.add(cartItem);

                   //tongtien += ((long) Integer.valueOf(cartItem.getTotal())) * Integer.parseInt(cartItem.getQuantity()));

                }
                cartAdapter.notifyDataSetChanged();
                total_price.setText(String.valueOf(tongtien));
            }

            @Override
            public void onFailure(Call<List<CartItem>> call, Throwable t) {
                Log.d("Failed",t.getMessage());
            }
        });

        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
//        for(int i=0;i<MenuDetailActivity.rcmitems.size();i++)
//        {list
//            list.add(new ArrayCart(MenuDetailActivity.items.get(i).getName(),MenuDetailActivity.items.get(i).getPrice(),MenuDetailActivity.quality.get(i),MenuDetailActivity.items.get(i).getImage()));
//        }


        total_price.setText(MenuDetailActivity.tongtien+" $");

//        GetPopularAPI popularAPI = retrofit.create(GetPopularAPI.class);
//        Call<List<HomeVerModel>> call = popularAPI.getPopular();
//
//        call.enqueue(new Callback<List<HomeVerModel>>() {
//            @Override
//            public void onResponse(Call<List<HomeVerModel>> call, Response<List<HomeVerModel>> response) {
//                List<HomeVerModel> myCartList = response.body();
//
//                for (HomeVerModel homeVerModel:myCartList)
//                {
//                    for(int i=0;i<MenuDetailActivity.items.size();i++)
//                    {
//                        if(homeVerModel.getName().equals(MenuDetailActivity.names.get(i))) {
//                            list.add(new MyCart(homeVerModel.getName(),homeVerModel.getPrice(),"1",homeVerModel.getImage()));
//                        }
//                    }
//                }
//                cartAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<HomeVerModel>> call, Throwable t) {
//
//            }
//        });




        return view;
    }

    private void Tongtien() {
            long tongtien = 0 ;
            for (int i = 0 ; i < list.size() ; i++)
            {
             //tongtien += (Integer.valueOf(list.get(i).getTotal()) * Integer.valueOf(list.get(i).getQuantity()));

            }
            //total_price.setText(String.valueOf(tongtien));
        }





}