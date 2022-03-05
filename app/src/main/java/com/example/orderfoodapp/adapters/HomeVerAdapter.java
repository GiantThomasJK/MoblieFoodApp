package com.example.orderfoodapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.security.identity.CipherSuiteNotSupportedException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.activities.LoginActivity;
import com.example.orderfoodapp.activities.MenuDetailActivity;
import com.example.orderfoodapp.models.FavoriteModel;
import com.example.orderfoodapp.models.FeaturedVerModel;
import com.example.orderfoodapp.models.HomeVerModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder> {

    int overAllTotalAmount;
    Context context;
    ArrayList<HomeVerModel> list;
    ArrayList<FeaturedVerModel> featuredVerModels;
    String totalAmount ;
    int layout;

    public HomeVerAdapter(Context context, ArrayList<HomeVerModel> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(list.get(position).getImage()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.homeveritem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MenuDetailActivity.class);
                intent.putExtra("item",list.get(position));
                holder.itemView.getContext().startActivity(intent);

            }
        });
      holder.cbHeart.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if (holder.cbHeart.isChecked()) {
                  APIController.apiService.postfavorite(LoginActivity.usercurrent.get_id(),new FavoriteModel(list.get(position).get_id())).enqueue(new Callback<List<String>>() {
                      @Override
                      public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                          Log.d("Success","success");
                      }

                      @Override
                      public void onFailure(Call<List<String>> call, Throwable t) {
                          Log.d("Failed",t.getMessage());
                      }
                  });
              }
              else{
                  APIController.apiService.deletefavorite(LoginActivity.usercurrent.get_id(),list.get(position).get_id()).enqueue(new Callback<List<String>>() {
                      @Override
                      public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                      }

                      @Override
                      public void onFailure(Call<List<String>> call, Throwable t) {

                      }
                  });
              }
          }
      });
        APIController.apiService.getfavorite(LoginActivity.usercurrent.get_id()).enqueue(new Callback<List<HomeVerModel>>() {
            @Override
            public void onResponse(Call<List<HomeVerModel>> call, Response<List<HomeVerModel>> response) {
                List<HomeVerModel> lists = response.body();
                for(HomeVerModel homeVerModel:lists){
                    if(homeVerModel.get_id().equals(list.get(position).get_id())){
                        holder.cbHeart.setChecked(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<HomeVerModel>> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public void filterList(ArrayList<HomeVerModel> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,timing,rating;
        CheckBox cbHeart;
        CardView homeveritem;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            homeveritem = itemView.findViewById(R.id.home_ver_item);
            imageView = itemView.findViewById(R.id.ver_img);
            name = itemView.findViewById(R.id.name);
            timing = itemView.findViewById(R.id.timing);
            rating = itemView.findViewById(R.id.rating);
            cbHeart = itemView.findViewById(R.id.cbHeart);

        }
    }
}
