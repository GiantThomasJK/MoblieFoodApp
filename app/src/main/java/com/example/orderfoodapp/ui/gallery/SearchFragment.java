package com.example.orderfoodapp.ui.gallery;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.adapters.HomeVerAdapter;
import com.example.orderfoodapp.adapters.SearchAdapter;
import com.example.orderfoodapp.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    ArrayList<HomeVerModel> homeVerModelList;
    HomeVerAdapter homeVerAdapter;
    RecyclerView homeVerticalRec;
    SearchAdapter searchAdapter;
    EditText searchEdt;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.activity_search_fragment,container,false);
        homeVerticalRec = root.findViewById(R.id.search_rec);
        searchEdt = root.findViewById(R.id.search_edt);
        homeVerModelList = new ArrayList<>();

        searchAdapter = new SearchAdapter(getActivity(),homeVerModelList);
        homeVerticalRec.setAdapter(searchAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));



        APIController.apiService.getPopular().enqueue(new Callback<List<HomeVerModel>>() {
            @Override
            public void onResponse(Call<List<HomeVerModel>> call, Response<List<HomeVerModel>> response) {
                List<HomeVerModel> homeVerModelList1 = response.body();

                for (HomeVerModel homeVerModel: homeVerModelList1)
                {
                    homeVerModelList.addAll(homeVerModelList1);
                    break;
                }
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<HomeVerModel>> call, Throwable t) {

            }
        });

        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                    filter(editable.toString());
            }
        });

        return root;
    }

    private void filter(String text) {
            ArrayList<HomeVerModel> filteredList = new ArrayList<>();

            for (HomeVerModel item : homeVerModelList){
                if (item.getName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))){
                    filteredList.add(item);
                }
            }
        searchAdapter.filterList(filteredList);
    }
}