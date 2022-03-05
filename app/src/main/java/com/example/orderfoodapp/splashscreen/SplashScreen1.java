package com.example.orderfoodapp.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.activities.SplashScreen;


public class SplashScreen1 extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen1);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen1.this, SplashScreen2.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_TIME_OUT);
    }

    public void skip(View view) {
        startActivity(new Intent(SplashScreen1.this, SplashScreen2.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.slude_out_left);

    }



}