package com.example.orderfoodapp.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.activities.LoginActivity;
import com.example.orderfoodapp.activities.WelcomeActivity;

public class SplashScreen3 extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen3);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen3.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_TIME_OUT);

    }

    public void skip(View view) {
        startActivity(new Intent(SplashScreen3.this, LoginActivity.class));

    }
}