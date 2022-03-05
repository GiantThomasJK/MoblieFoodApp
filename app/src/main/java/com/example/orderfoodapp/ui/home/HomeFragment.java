package com.example.orderfoodapp.ui.home;

import android.app.NativeActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.API.Categories;
import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.activities.AppUtil;
import com.example.orderfoodapp.activities.MapActivity;
import com.example.orderfoodapp.activities.NavigationActivity;
import com.example.orderfoodapp.adapters.HomeFoodAdapter;
import com.example.orderfoodapp.adapters.HomeHorAdapter;
import com.example.orderfoodapp.adapters.HomeVerAdapter;
import com.example.orderfoodapp.adapters.HomeVoucherAdapter;
import com.example.orderfoodapp.adapters.UpdateVerticalRec;
import com.example.orderfoodapp.models.HomeFoodModel;
import com.example.orderfoodapp.models.HomeVerModel;
import com.example.orderfoodapp.models.HomeVoucherModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.example.orderfoodapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements UpdateVerticalRec {

    RecyclerView homeHorizontalRec,homeVerticalRec,homeVoucherRec,homeFoodRec,homeRecommendRec;
    //List<Categories> homeHorModelList;
    HomeHorAdapter homeHorAdapter;
    ArrayList<Categories> homeHorModelList;
    TextView addUser;




    ///////////
    ArrayList<HomeVerModel> recommendModelArrayList;
    HomeVerAdapter recommendAdapter;

    ImageView imageView;


    /////
    ArrayList<HomeVerModel> homeVerModelList;
    HomeVerAdapter homeVerAdapter;

    /////////

    ArrayList<HomeVoucherModel> homeVoucherModels;
    HomeVoucherAdapter homeVoucherAdapter;

    //////
    ArrayList<HomeFoodModel> homeFoodModels;
    HomeFoodAdapter homeFoodAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home,container,false);

        homeFoodRec = root.findViewById(R.id.home_food_new);
        homeVoucherRec = root.findViewById(R.id.home_dis_item);
        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
        homeVerticalRec = root.findViewById(R.id.home_ver_rec);
        homeRecommendRec = root.findViewById(R.id.recommend_ver_rec);
        addUser = root.findViewById(R.id.address_user);
        addUser.setText(AppUtil.mAddress);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
                addUser.setText(AppUtil.mAddress);

            }
        });





        ////////////////

        homeFoodModels = new ArrayList<>();

        homeFoodModels.add(new HomeFoodModel(R.drawable.banner1));
        homeFoodModels.add(new HomeFoodModel(R.drawable.banner2));
        homeFoodModels.add(new HomeFoodModel(R.drawable.banner3));

        homeFoodAdapter = new HomeFoodAdapter(getActivity(),homeFoodModels);
        homeFoodRec.setAdapter(homeFoodAdapter);
        homeFoodRec.setAdapter(homeFoodAdapter);
        homeFoodRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));



        /////////



        APIController.apiService.getCategories().enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                List<Categories> categoriesList = response.body();

                for (Categories categories: categoriesList)
                {
                    homeHorModelList.addAll(categoriesList);

                    break;
                }
                homeHorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {

            }
        });


        homeHorModelList = new ArrayList<>();
        homeHorAdapter = new HomeHorAdapter(this,getActivity(),homeHorModelList);
        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);


        //////////////////

        recommendModelArrayList = new ArrayList<>();
        recommendAdapter = new HomeVerAdapter(getActivity(),recommendModelArrayList,R.layout.recommend_item);
        homeRecommendRec.setAdapter(recommendAdapter);
        homeRecommendRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        homeRecommendRec.setHasFixedSize(true);
        homeRecommendRec.setNestedScrollingEnabled(false);


        APIController.apiService.getPopular().enqueue(new Callback<List<HomeVerModel>>() {
            @Override
            public void onResponse(Call<List<HomeVerModel>> call, Response<List<HomeVerModel>> response) {
                List<HomeVerModel> recommendModelList = response.body();

                for (HomeVerModel recommendModel: recommendModelList)
                {
                    recommendModelArrayList.addAll(recommendModelList);
                    break;
                }
                recommendAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<HomeVerModel>> call, Throwable t) {

            }
        });



            //////////////////
            homeVerModelList = new ArrayList<>();

        homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModelList,R.layout.home_vertical_item);
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        //////////////
        homeVoucherModels = new ArrayList<>();
        homeVoucherModels.add(new HomeVoucherModel(R.drawable.banner4));
        homeVoucherModels.add(new HomeVoucherModel(R.drawable.banner5));

        homeVoucherAdapter = new HomeVoucherAdapter(getActivity(),homeVoucherModels);
        homeVoucherRec.setAdapter(homeVoucherAdapter);
        homeVoucherRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        return  root;
    }

    private void PutDataIntoRecyclerView(ArrayList<Categories> categoriesList) {
        homeHorAdapter = new HomeHorAdapter(this,getActivity(),categoriesList);
        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);
    }

//
    @Override
    public void callback(int position, ArrayList<HomeVerModel> list) {

        homeVerAdapter = new HomeVerAdapter(getContext(),list,R.layout.home_vertical_item);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);

    }


}