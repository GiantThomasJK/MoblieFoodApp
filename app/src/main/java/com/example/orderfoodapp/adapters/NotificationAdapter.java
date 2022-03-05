package com.example.orderfoodapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.models.HomeVerModel;
import com.example.orderfoodapp.models.NotificationModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    List<NotificationModel> list;

    public NotificationAdapter(List<NotificationModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {

//        APIController.apiService.getProduct(list.get(position).getIdproduct()).enqueue(new Callback<HomeVerModel>() {
//            @Override
//            public void onResponse(Call<HomeVerModel> call, Response<HomeVerModel> response) {
//                HomeVerModel homeVerModel = response.body();
//                Picasso.get().load(homeVerModel.getImage()).into(holder.imageView);
//                holder.name.setText(homeVerModel.getName());
//            }
//
//            @Override
//            public void onFailure(Call<HomeVerModel> call, Throwable t) {
//
//            }
//        });


        holder.name.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getAddress());
        holder.phonenumber.setText(list.get(position).getPhone());
        holder.status.setText(list.get(position).getStatus());
        holder.total.setText(list.get(position).getTotal()+"");
        holder.totalamount.setText(list.get(position).getTotal_all()+"");
        holder.ship.setText(list.get(position).getShip()+"");



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,address,phonenumber,total,ship,totalamount,status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.not_name_user);
            address= itemView.findViewById(R.id.not_address);
            phonenumber = itemView.findViewById(R.id.not_phone_number);
            status = itemView.findViewById(R.id.not_status);
            total = itemView.findViewById(R.id.not_total);
            totalamount = itemView.findViewById(R.id.not_total_amout);
            ship = itemView.findViewById(R.id.not_ship);



        }
    }
}
