package com.example.orderfoodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.models.HomeVerModel;
import com.example.orderfoodapp.models.HomeVoucherModel;

import java.util.ArrayList;

public class HomeVoucherAdapter extends RecyclerView.Adapter<HomeVoucherAdapter.ViewHolder>{

    Context context;
    ArrayList<HomeVoucherModel> list;

    public HomeVoucherAdapter(Context context, ArrayList<HomeVoucherModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_discount_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.voucher_img);
        }
    }
}
