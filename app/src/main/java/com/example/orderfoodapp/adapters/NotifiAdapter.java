package com.example.orderfoodapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.models.CartModel;
import com.example.orderfoodapp.models.NotifiModel;

import java.util.List;

public class NotifiAdapter extends RecyclerView.Adapter<NotifiAdapter.ViewHolder>{

    List<NotifiModel> list;

    public NotifiAdapter(List<NotifiModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotifiAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notifi_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,rating,price,btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.detailed_img);
            name = itemView.findViewById(R.id.detailed_name);
            rating= itemView.findViewById(R.id.detailed_rating);
            price = itemView.findViewById(R.id.detailed_price);
            btn = itemView.findViewById(R.id.delivery_btn);
        }

    }
}
