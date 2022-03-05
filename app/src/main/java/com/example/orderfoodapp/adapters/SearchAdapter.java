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
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.activities.MenuDetailActivity;
import com.example.orderfoodapp.models.HomeVerModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Context context;
    ArrayList<HomeVerModel> list;

    public SearchAdapter(Context context, ArrayList<HomeVerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(list.get(position).getImage()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.pricing.setText(list.get(position).getPrice() + " $");
        holder.timing.setText(list.get(position).getTiming());
        holder.homeveritem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MenuDetailActivity.class);
                intent.putExtra("item",list.get(position));
                holder.itemView.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<HomeVerModel> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,timing,pricing;
        CardView homeveritem;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            homeveritem = itemView.findViewById(R.id.search_ver_item);
            imageView = itemView.findViewById(R.id.search_img);
            name = itemView.findViewById(R.id.search_name);
            timing = itemView.findViewById(R.id.search_timing);
            pricing = itemView.findViewById(R.id.search_price);
        }
    }
}
