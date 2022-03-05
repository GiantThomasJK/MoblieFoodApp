package com.example.orderfoodapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.activities.MenuDetailActivity;
import com.example.orderfoodapp.models.HomeVerModel;
import com.example.orderfoodapp.models.RecommendModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder>{

    Context context;
    ArrayList<RecommendModel> list;

    public RecommendAdapter(Context context, ArrayList<RecommendModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecommendAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(list.get(position).getImage()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.timing.setText(list.get(position).getTiming());
        holder.rating.setText(list.get(position).getRating());


        holder.homeveritem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MenuDetailActivity.class);
                intent.putExtra("rcmitem",list.get(position));
                holder.itemView.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,timing,rating;
        CardView homeveritem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            homeveritem = itemView.findViewById(R.id.recommend_ver_item);
//            imageView = itemView.findViewById(R.id.rcm_img);
//            name = itemView.findViewById(R.id.rcm_name);
//            timing = itemView.findViewById(R.id.rcm_timing);
//            rating = itemView.findViewById(R.id.rcm_rating);
        }
    }
}
