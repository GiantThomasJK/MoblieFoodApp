package com.example.orderfoodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.orderfoodapp.R;

public class AddressUser extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_user);



        LoadingDialog loadingDialog = new LoadingDialog(AddressUser.this);
        loadingDialog.startLoadingDialog();

        Intent intent = new Intent(AddressUser.this,MapActivity.class);
        startActivity(intent);




    }
}