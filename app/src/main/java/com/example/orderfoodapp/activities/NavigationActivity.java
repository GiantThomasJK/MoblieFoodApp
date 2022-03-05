package com.example.orderfoodapp.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.ui.MyCartFragment;
import com.example.orderfoodapp.ui.gallery.DailyMealFragment;
import com.example.orderfoodapp.ui.gallery.SearchFragment;
import com.example.orderfoodapp.ui.gallery.UserInfomationFragment;
import com.example.orderfoodapp.ui.home.HomeFragment;
import com.example.orderfoodapp.ui.slideshow.FavouriteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class NavigationActivity extends AppCompatActivity{

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_bottom);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navigationView = findViewById(R.id.btn_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.nav_search:
                        fragment = new SearchFragment();
                        break;

                    case R.id.nav_shopping:
                        fragment = new MyCartFragment();
                        break;

                    case R.id.nav_acc:
                        fragment = new UserInfomationFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
                return true;
            }
        });


    }
}
