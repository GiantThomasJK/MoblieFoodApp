package com.example.orderfoodapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.API.TEST.Message;
import com.example.orderfoodapp.MainActivity;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.models.Forgot;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyActivity extends AppCompatActivity {
    EditText edtEmail;
    Button btnSend;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        edtEmail = findViewById(R.id.edtEmail);
        btnSend = findViewById(R.id.btnSend);
        firebaseAuth = FirebaseAuth.getInstance();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPassword();
            }
        });

    }
    private void forgotPassword(){
        String email=edtEmail.getText().toString().trim();
        if(email.isEmpty())
        {
            edtEmail.setError("Nhập Email");
            return;
        }

        APIController.apiService.forgot(new Forgot(email)).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Toast.makeText(getApplicationContext(), "New password sent to gmail, please check gmail", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }
}