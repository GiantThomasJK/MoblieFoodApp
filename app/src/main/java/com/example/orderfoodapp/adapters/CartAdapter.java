package com.example.orderfoodapp.adapters;

import android.annotation.SuppressLint;
import android.app.UiAutomation;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.API.ArrayCart;
import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.models.CartItem;
import com.example.orderfoodapp.models.HomeVerModel;
import com.example.orderfoodapp.ui.MyCartFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    List<CartItem> list;
    IOnClickItemCart iOnClickItemCart;
    float tinhtong=0;
    public IOnClickItemCart getiOnClickItemCart() {
        return iOnClickItemCart;
    }

    public void setiOnClickItemCart(IOnClickItemCart iOnClickItemCart) {
        this.iOnClickItemCart = iOnClickItemCart;
    }

    public CartAdapter(Context context, List<CartItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        APIController.apiService.getProduct(list.get(position).getProduct()).enqueue(new Callback<HomeVerModel>() {
            @Override
            public void onResponse(Call<HomeVerModel> call, Response<HomeVerModel> response) {
                HomeVerModel product = response.body();
                Picasso.get().load(product.getImage()).into(holder.imageView);
                holder.name.setText(product.getName());
            }

            @Override
            public void onFailure(Call<HomeVerModel> call, Throwable t) {

            }
        });

        holder.qty.setText(list.get(position).getQuantity());
        holder.price.setText(list.get(position).getTotal()+ " $");

        holder.cbAddItemOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.cbAddItemOrder.isChecked()){
                    tinhtong+=Float.parseFloat(list.get(position).getTotal());
                    iOnClickItemCart.iClickItemCart(position, tinhtong);
                    MyCartFragment.total_price.setText(tinhtong+"$");

                }else{
                    tinhtong-=Float.parseFloat(list.get(position).getTotal());
                    iOnClickItemCart.iClickItemCart(-100-position, tinhtong );
                    MyCartFragment.total_price.setText(tinhtong+"$");
                }


//                if(holder.cbAddItemOrder.isChecked()){
                  // tinhtong+=Double.parseDouble(list.get(position).getTotal());
//                    MyCartFragment.total_price.setText(tinhtong+"$");
//                }
//                else{
//                    tinhtong=tinhtong-Double.parseDouble(list.get(position).getTotal());
//                    MyCartFragment.total_price.setText(tinhtong+"$");
//                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIController.apiService.deletecartitem(list.get(position).getId()).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        list.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView,delete;
        TextView name,qty,price;
        CheckBox cbAddItemOrder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cbAddItemOrder= itemView.findViewById(R.id.cbAddItemOrder);
            imageView = itemView.findViewById(R.id.detailed_img);
            name = itemView.findViewById(R.id.detailed_name);
            qty= itemView.findViewById(R.id.detailed_quantity);
            price = itemView.findViewById(R.id.detailed_price);
            delete = itemView.findViewById(R.id.delete_item);

        }
    }

    public void updateL(List<CartItem> l){
        list= l;
        notifyDataSetChanged();
    }
}
