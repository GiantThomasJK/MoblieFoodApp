package com.example.orderfoodapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.models.FeaturedVerModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    List<FeaturedVerModel> list;

    public FavouriteAdapter(List<FeaturedVerModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavouriteAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_ver_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(list.get(position).getImage()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.timing.setText(list.get(position).getTiming());
        holder.rating.setText(list.get(position).getRating());
        holder.pricing.setText(list.get(position).getPrice());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,timing,rating,pricing;
        CardView homeveritem;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            homeveritem = itemView.findViewById(R.id.favourite_cardview);
            imageView = itemView.findViewById(R.id.fav_img);
            name = itemView.findViewById(R.id.fav_name);
            timing = itemView.findViewById(R.id.fav_timing);
            rating = itemView.findViewById(R.id.fav_rating);
            pricing = itemView.findViewById(R.id.fav_price);
        }
    }
}
