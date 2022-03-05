package com.example.orderfoodapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.adapters.FeaturedVerAdapter;
import com.example.orderfoodapp.adapters.featuredAdapter;
import com.example.orderfoodapp.models.FeaturedModel;
import com.example.orderfoodapp.models.FeaturedVerModel;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment {




    ////////////////////////////////////////Featured Hor
  List<FeaturedModel> featuredModelList;
  RecyclerView recyclerView;
  featuredAdapter featuredAdapter;

    ////////////////////////////////////////Featured Ver
    List<FeaturedVerModel> featuredVerModelList;
    RecyclerView recyclerView2;
    FeaturedVerAdapter featuredVerAdapter;



    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);


        ////////////////////////////////////////Featured Hor
       /* recyclerView = view.findViewById(R.id.featured_hor_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        featuredModelList = new ArrayList<>();

        featuredModelList.add(new FeaturedModel(R.drawable.tokbokki2));
        featuredModelList.add(new FeaturedModel(R.drawable.tokbokki3));
        featuredModelList.add(new FeaturedModel(R.drawable.tokbokki1));

        featuredAdapter = new featuredAdapter(featuredModelList);
        recyclerView.setAdapter(featuredAdapter); */


        ////////////////////////////////////////Featured Ver

        recyclerView2 = view.findViewById(R.id.featured_ver_rec);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

//        featuredVerModelList = new ArrayList<>();
//        featuredVerModelList.add(new FeaturedVerModel(R.drawable.toto1,"Tokbokki Cheese","$29.99","4.8","8:00 - 22:00"));
//        featuredVerModelList.add(new FeaturedVerModel(R.drawable.toto2,"Tokbokki Spicy","$29.99","4.8","8:00 - 22:00"));
//        featuredVerModelList.add(new FeaturedVerModel(R.drawable.toto3,"Tokbokki Special","$29.99","4.8","8:00 - 22:00"));
//
//
//        featuredVerAdapter = new FeaturedVerAdapter(featuredVerModelList);
        recyclerView2.setAdapter(featuredVerAdapter);

        return view;



    }

}