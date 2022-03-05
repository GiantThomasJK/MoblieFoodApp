package com.example.orderfoodapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.adapters.NotificationAdapter;
import com.example.orderfoodapp.models.NotifiModel;
import com.example.orderfoodapp.models.NotificationModel;
import com.example.orderfoodapp.models.OrderItem;
import com.example.orderfoodapp.models.OrderModel;
import com.facebook.login.Login;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {


    public static List<NotificationModel> list = new ArrayList<>();
    public static NotificationAdapter notificationAdapter;
    RecyclerView recyclerView;

   public NotificationActivity(){

   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = findViewById(R.id.notification_rec);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        notificationAdapter = new NotificationAdapter(list);
        recyclerView.setAdapter(notificationAdapter);



        list.clear();
//        for(int i=0;i<LoginActivity.usercurrent.getOrderitem().size();i++) {
//            APIController.apiService.getOrderitem(LoginActivity.usercurrent.getOrderitem().get(i)).enqueue(new Callback<List<OrderItem>>() {
//                @Override
//                public void onResponse(Call<List<OrderItem>> call, Response<List<OrderItem>> response) {
//                    List<OrderItem> lists = response.body();
//                    for(OrderItem orderItem:lists){
//                        list.add(new NotificationModel();
//                    }
//                    Log.d("Sucess","call notification thanh cong "+ lists.size());
//                    notificationAdapter.notifyDataSetChanged();
//
//                }
//
//                @Override
//                public void onFailure(Call<List<OrderItem>> call, Throwable t) {
//                    Log.d("Failed",t.getMessage());
//
//                }
//            });

            APIController.apiService.getOrderUser(LoginActivity.usercurrent.get_id()).enqueue(new Callback<List<NotificationModel>>() {
                @Override
                public void onResponse(Call<List<NotificationModel>> call, Response<List<NotificationModel>> response) {

                    List<NotificationModel> notificationModelList = response.body();


                    for (NotificationModel notificationModel : notificationModelList)
                    {
                      list.add(new NotificationModel(notificationModel.get_id(),notificationModel.getName(),notificationModel.getAddress(),notificationModel.getPhone(),
                               notificationModel.getTotal(),notificationModel.getTotal_all(),notificationModel.getShip(),notificationModel.getStatus()));
                        notificationAdapter.notifyDataSetChanged();

                    }


                }

                @Override
                public void onFailure(Call<List<NotificationModel>> call, Throwable t) {

                }
            });

        }


//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.clear();
    }
}