package com.example.orderfoodapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.API.User;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.ui.gallery.UserInfomationFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangePasswordActivity extends AppCompatActivity {


    EditText edt_new_pass , edt_confirm_pass;
    Button btn_changepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edt_new_pass = findViewById(R.id.edt_new_password);
        edt_confirm_pass = findViewById(R.id.edt_confirm_newpass);
        btn_changepass = findViewById(R.id.btn_changepass);

        btn_changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_new_pass.getText().toString().trim().compareTo(edt_confirm_pass.getText().toString().trim())==0)
                    clickChange();
                else {
                    Toast.makeText(getBaseContext(), "Password do not match", Toast.LENGTH_LONG).show();
                }
            }
        });





    }
    public void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();

    }

    private void clickChange() {

        Log.d("ch", "current id::: "+ LoginActivity.usercurrent.get_id());
        String newpass = edt_new_pass.getText().toString().trim();
        String confirmpass = edt_confirm_pass.getText().toString().trim();
        String idUser = null;
        if(TextUtils.isEmpty(newpass)){
            edt_new_pass.setError("Please enter a valid new password.");
            return;
        }
        if(newpass.length()<=6)
        {
            edt_new_pass.setError("Password needs more than 6 characters");
            return;
        }
        if (!confirmpass.equals(newpass)){
            edt_confirm_pass.requestFocus();
            edt_confirm_pass.setError("Passwords do not match");
            return;
        }

        APIController.apiService.changePassword(LoginActivity.usercurrent.get_id(), new User(edt_new_pass.getText().toString().trim())).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("ch", "respone change password: "+ response.toString());
                Toast.makeText(ChangePasswordActivity.this, "Change password successfully, please login again", Toast.LENGTH_LONG).show();
//                getFragment(new UserInfomationFragment());
                startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ChangePasswordActivity.this, "Change password fail", Toast.LENGTH_LONG).show();
            }
        });


    }

    public void UserInfo(View view) {

        finish();

    }


    public void back(View view) {
        finish();
    }
}