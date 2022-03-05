package com.example.orderfoodapp.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.API.User;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.activities.ChangeInfoActivity;
import com.example.orderfoodapp.activities.ChangePasswordActivity;
import com.example.orderfoodapp.activities.FavouriteActivity;
import com.example.orderfoodapp.activities.LoginActivity;
import com.example.orderfoodapp.activities.NotificationActivity;
import com.example.orderfoodapp.activities.PaymentActivity;
import com.example.orderfoodapp.databinding.FragmentHomeBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.firestore.FirebaseFirestore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfomationFragment extends Fragment {

    TextView userName , Email ;
    ImageView userImage;
    ImageView btn_logout;
    Button btn_fav;
    Button btn_change;
    Button btn_changeinfo , btn_changepayment, btn_myOder;
    FragmentHomeBinding binding;
//    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth =FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = mAuth.getCurrentUser();
    private DatabaseReference reference;
    private String userID;
    public static final int MY_REQUEST_CODE = 10;
    private LoginActivity loginActivity;
    SharedPreferences sharedPreferences;
    private static final String SHARE_PREF_NAME = "mypref";
    private static final String KEY_ID = "UserID" ;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_user_info,container,false);

        userImage = root.findViewById(R.id.user_img);
        userName = root.findViewById(R.id.username);
        btn_changeinfo = root.findViewById(R.id.btn_changeinfo);

        Email =  root.findViewById(R.id.email);
//        loginActivity = (LoginActivity) getActivity();
        sharedPreferences = getActivity().getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);

        btn_changepayment = root.findViewById(R.id.change_payment);
        btn_changepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(intent);
            }
        });
        btn_change = root.findViewById(R.id.btn_change);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        btn_changeinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangeInfoActivity.class);
                startActivity(intent);
            }
        });

        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        btn_fav = root.findViewById(R.id.btn_fav);
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FavouriteActivity.class);
                startActivity(intent);
            }
        });

        btn_myOder = root.findViewById(R.id.my_order);
        btn_myOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }

        });


        btn_logout = root.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        APIController.apiService.getUserCurrent(LoginActivity.usercurrent.get_id()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Email.setText(user.getEmail());
                userName.setText(user.getFullname());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
