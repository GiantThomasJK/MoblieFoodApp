package com.example.orderfoodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.API.User;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.models.LoginRespone;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername , edtPass;
    Button btnSignin;
    ArrayList<LoginRespone> mListUser;
    List<User> userArrayList;

    int RC_SIGN_IN = 123;
    ImageView btngoogle , btnFacebook;
    private String StringEmail ="";
    public static User usercurrent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.edt_username);
        edtPass = findViewById(R.id.edt_password);
        btnSignin = findViewById(R.id.button);
        mListUser = new ArrayList<>();

        getListUser();

//        OTPVerificationDialog otpVerificationDialog = new OTPVerificationDialog(LoginActivity.this,"giathinhkg1@gmail.com");
//
//        otpVerificationDialog.setCancelable(true);
//        otpVerificationDialog.show();

        LoadingDialog loadingDialog = new LoadingDialog(LoginActivity.this);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogin();
                LoadingDialog loadingDialog = new LoadingDialog(LoginActivity.this);

            }
        });


    }


    private void clickLogin() {

        String username = edtUsername.getText().toString().trim();
        String password = edtPass.getText().toString().trim();
        String idUser = null;
        if(TextUtils.isEmpty(username)){
            edtUsername.setError("Please enter a valid email address.");
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            edtPass.setError("Please enter a password");
            return;
        }
        if(password.length()<=4)
        {
            edtPass.setError("Password needs more than 4 characters");
            return;
        }

        APIController.apiService.Login(new User(password,username)).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();
                Toast.makeText(LoginActivity.this,"Success",Toast.LENGTH_SHORT).show();
                for(User user:userList){
                    if(user.getEmail().equals(username)){
                        usercurrent = user;
                        startActivity(new Intent(LoginActivity.this,AddressUser.class));
                        Toast.makeText(LoginActivity.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Logged in failed",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getListUser() {


        APIController.apiService.getUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                userArrayList = response.body();
                Log.d("login","success");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("login","falied");

            }
        });

    }

    public void Register(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

   public void mainActivity(View view) {
        startActivity(new Intent(LoginActivity.this, AddressUser.class));

    }

    public void forgot(View view) {
        startActivity(new Intent(LoginActivity.this, VerifyActivity.class));

    }

    public String getStringEmail() {
        return StringEmail;
    }

    public void setStringEmail(String stringEmail) {
        StringEmail = stringEmail;
    }
}