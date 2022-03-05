package com.example.orderfoodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderfoodapp.API.User;
import com.example.orderfoodapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class UserInfoActivity extends AppCompatActivity {

    TextView userName , Email ;
    RoundedImageView userImg;
    private FirebaseAuth firebaseAuth;
    private ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        userName = findViewById(R.id.username);
        Email = findViewById(R.id.email);
        //logout = findViewById(R.id.logout);
        firebaseAuth = FirebaseAuth.getInstance();
        userImg = findViewById(R.id.user_img);
        logout = findViewById(R.id.btn_logout);


//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            for (UserInfo profile : user.getProviderData()) {
//                // Id of the provider (ex: google.com)
//                String providerId = profile.getProviderId();
//
//                // UID specific to the provider
//                String uid = profile.getUid();
//
//                // Name, email address, and profile photo Url
//                String name = profile.getDisplayName();
//                String email = profile.getEmail();
//                Uri photoUrl = profile.getPhotoUrl();
//                userName.setText(name);
//                Email.setText(email);
//                Picasso.get().load(photoUrl).error(R.drawable.ic_person_flat).into(userImg);
//
//
//            }
//        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void ChangePassword(View view) {

        startActivity(new Intent(UserInfoActivity.this, WelcomeActivity.class));

    }
    public void payment(View view){
        startActivity(new Intent(UserInfoActivity.this, PaymentActivity.class));

    }
}