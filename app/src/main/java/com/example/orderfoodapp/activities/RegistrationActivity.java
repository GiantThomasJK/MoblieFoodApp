package com.example.orderfoodapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.API.User;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.models.LoginRespone;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtUsername , edtPass, edtEmail , edtConfirm , edtPhone;
    Button btn_signup;
    ArrayList<User> userList;
    ArrayList<LoginRespone> loginRespones = new ArrayList<>();
    FirebaseAuth mAuth;
//    private FirebaseFirestore firestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edtUsername = findViewById(R.id.edt_username);
        edtPass = findViewById(R.id.edt_password);
        edtEmail = findViewById(R.id.edt_email);
        edtConfirm = findViewById(R.id.edt_confirmpassword);
        edtPhone = findViewById(R.id.edt_phone);
        btn_signup = findViewById(R.id.btn_registion);


        mAuth = FirebaseAuth.getInstance();
//        firestore = FirebaseFirestore.getInstance();
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                clickRegis();


                Log.d("rg", "event rg:==================");

                String email= edtEmail.getText().toString().trim();
                String password= edtPass.getText().toString().trim();
                String fullname= edtUsername.getText().toString().trim();
                String phone= edtPhone.getText().toString().trim();
                String confirm = edtConfirm.getText().toString().trim();

                if (fullname.isEmpty()){
                    edtUsername.requestFocus();
                    edtUsername.setError("Please enter your username");
                }
                if(password.isEmpty()){
                    edtPass.requestFocus();
                    edtPass.setError("Please enter your password");
                    return;
                }
                if (password.length()<=6){
                    edtPass.requestFocus();
                    edtPass.setError("Password needs more than 6 characters");
                    return;
                }
                if(email.isEmpty()){
                    edtEmail.setError("Please enter your email");
                    edtEmail.requestFocus();
                    return;
                }
                if(phone.isEmpty()){
                    edtPhone.setError("Please enter your phone number");
                    edtPhone.requestFocus();
                    return;
                }
                if (!confirm.equals(password)){
                    edtConfirm.requestFocus();
                    edtConfirm.setError("Passwords do not match");
                    return;
                }

                APIController.apiService.register(new User(fullname, password, phone, email)).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(RegistrationActivity.this, "Sign Up Success", Toast.LENGTH_LONG).show();
                        OTPVerificationDialog otpVerificationDialog = new OTPVerificationDialog(RegistrationActivity.this,edtEmail.toString()+"");

                        otpVerificationDialog.setCancelable(true);
                        otpVerificationDialog.show();

                        Intent intent= new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_LONG).show();
                    }
                });
            }



        });

    }

    private void clickRegis() {

        String password = edtPass.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String fullname = edtUsername.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String confirm = edtConfirm.getText().toString().trim();

        if (fullname.isEmpty()){
            edtUsername.requestFocus();
            edtUsername.setError("Please enter your username");
        }
        if(password.isEmpty()){
            edtPass.requestFocus();
            edtPass.setError("Please enter your password");
            return;
        }
        if (password.length()<=6){
            edtPass.setError("Password needs more than 6 characters");
            return;
        }
        if(email.isEmpty()){
            edtEmail.setError("Please enter your email");
            edtEmail.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            edtPhone.setError("Please enter your phone number");
            edtPhone.requestFocus();
            return;
        }
        if (!confirm.equals(password)){
            edtConfirm.requestFocus();
            edtConfirm.setError("Passwords do not match");
            return;
        }


//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            String userID = mAuth.getCurrentUser().getUid();
//                            DocumentReference documentReference = firestore.collection("Users").document(userID);
//                            Map<String, Object> User = new HashMap<>();
//                            User.put("username", fullname);
//                            User.put("email", email);
//                            User.put("idUser",userID);
//                            User.put("password",password);
//                            documentReference.set(User).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    Log.d("TAG", "onSuccess: user profile is create for "+ userID);
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Log.d("TAG", "onFailure: "+ e.toString());
//                                }
//                            });
//
//                            Toast.makeText(RegistrationActivity.this, "Sign Up Success.",
//                                    Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
//                            startActivity(intent);
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Toast.makeText(RegistrationActivity.this, "Registration failed.",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });

        userList = new ArrayList<>();


    }


    public void login(View view) {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }


}