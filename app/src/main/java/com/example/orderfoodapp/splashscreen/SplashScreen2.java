package com.example.orderfoodapp.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.orderfoodapp.R;

public class SplashScreen2 extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen2.this, SplashScreen3.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_TIME_OUT);

    }

    public void skip(View view) {
        startActivity(new Intent(SplashScreen2.this, SplashScreen3.class));

    }
}