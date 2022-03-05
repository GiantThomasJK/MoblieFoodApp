package com.example.orderfoodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.splashscreen.SplashScreen1;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;


    TextView textView;
    LottieAnimationView lottieAnimationView,logoapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView = findViewById(R.id.logo);
        lottieAnimationView = findViewById(R.id.lottie);
        logoapp = findViewById(R.id.logo_res);

        logoapp.animate().translationX(1600).setDuration(1000).setStartDelay(4000);
        textView.animate().translationX(-1400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationX(1600).setDuration(1000).setStartDelay(4000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, SplashScreen1.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_TIME_OUT);




    }

}