package com.example.orderfoodapp.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.API.Categories;
import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.models.HomeVerModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {

    UpdateVerticalRec updateVerticalRec;
    Activity activity;
    List<Categories> list;
    ArrayList<HomeVerModel> homeVerList;
    int layout;


    boolean check = true;
    boolean select = true;
    int row_index = -1;

    public HomeHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, List<Categories> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        homeVerList = new ArrayList<>();
        Picasso.get().load(list.get(position).getPicture()).into(holder.imageView);
        holder.name.setText(list.get(position).getname());



        if(check) {
            Filter("61af109791d9e4219cd24c1e",0);
            updateVerticalRec.callback(position, homeVerList);
            check = false;
        }
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = position;
                    notifyDataSetChanged();

                    if(position == 0){
                        Filter("61af109791d9e4219cd24c1e",position);
                    }

                    else if (position == 1){
                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        Filter("61af10c091d9e4219cd24c20",position);
                        for (HomeVerModel homeVerModel : homeVerList){
                            if(homeVerModel.getOwner_category().equals("61af10c091d9e4219cd24c20")){
                                homeVerModels.add(homeVerModel);
                            }
                        }
                        updateVerticalRec.callback(position, homeVerModels);
                    }

                    else if (position == 2){
                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                         Filter("61af11d591d9e4219cd24c23",position);
                       for (HomeVerModel homeVerModel : homeVerList){
                           if(homeVerModel.getOwner_category().equals("61af11d591d9e4219cd24c23")){
                               homeVerModels.add(homeVerModel);
                           }
                     }
                        updateVerticalRec.callback(position,homeVerModels);

                    }
                    else if (position == 3){


                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        Filter("61af11eb91d9e4219cd24c25",position);
                        for (HomeVerModel homeVerModel : homeVerList){
                            if(homeVerModel.getOwner_category().equals("61af11eb91d9e4219cd24c25")){
                                homeVerModels.add(homeVerModel);
                            }
                        }
                        updateVerticalRec.callback(position,homeVerModels);
                    }
                }
            });

            if (select){
                if (position == 0){
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
                    select = false;
                }

            }
            else {
                if (row_index == position){
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);

                }else {
                    holder.cardView.setBackgroundResource(R.drawable.default_bg);

                }
            }


        }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.hor_img);

            //set image

            name = itemView.findViewById(R.id.hor_text);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
    private void Filter(String category,int position){
        homeVerList.clear();


        APIController.apiService.getPopular().enqueue(new Callback<List<HomeVerModel>>() {
            @Override
            public void onResponse(Call<List<HomeVerModel>> call, Response<List<HomeVerModel>> response) {
                List<HomeVerModel> homeVerModelList = response.body();

                for (HomeVerModel homeVerModel : homeVerModelList){
                    if(homeVerModel.getOwner_category().equals(category)){
                        homeVerList.add(homeVerModel);
                    }
                }
                Log.d("aaa",homeVerList.size()+"");
                updateVerticalRec.callback(position, homeVerList);
            }

            @Override
            public void onFailure(Call<List<HomeVerModel>> call, Throwable t) {

            }
        });


    }


}
