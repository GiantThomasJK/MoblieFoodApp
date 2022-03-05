package com.example.orderfoodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.models.HomeFoodModel;
import com.example.orderfoodapp.models.HomeVoucherModel;

import java.util.ArrayList;

public class HomeFoodAdapter extends RecyclerView.Adapter<HomeFoodAdapter.ViewHolder>{

    Context context;
    ArrayList<HomeFoodModel> list;

    public HomeFoodAdapter(Context context, ArrayList<HomeFoodModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeFoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeFoodAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_food_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFoodAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.food_new_img);

        }
    }
}
