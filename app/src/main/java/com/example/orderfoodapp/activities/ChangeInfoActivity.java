package com.example.orderfoodapp.activities;

import static com.example.orderfoodapp.ui.gallery.UserInfomationFragment.MY_REQUEST_CODE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.RestrictionsManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.API.User;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.ui.gallery.UserInfomationFragment;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;
import gun0912.tedbottompicker.TedRxBottomPicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeInfoActivity extends AppCompatActivity {

    TextView userName , Email ;
    EditText changeFullname , changeAddress , changePhone;
    FloatingActionButton uploadpicture;
    CircleImageView userImage;
    private FirebaseAuth mAuth;
    Button upload , save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        userImage = findViewById(R.id.user_img_change);
        userName = findViewById(R.id.username);
        Email =  findViewById(R.id.email);

//        uploadpicture = findViewById(R.id.upload_picture);

        changeFullname = findViewById(R.id.change_fullname);
        changePhone = findViewById(R.id.change_phone);

        upload = findViewById(R.id.btn_upload);
        save = findViewById(R.id.btn_update);

        changeFullname.setText(LoginActivity.usercurrent.getFullname());
        changePhone.setText(LoginActivity.usercurrent.getPhone());


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions();
            }
        });

//        setUserInfo();
//
//        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
//
//        if (signInAccount != null){
//            userName.setText(signInAccount.getDisplayName());
//            Email.setText(signInAccount.getEmail());
//            Picasso.get().load(signInAccount.getPhotoUrl()).into(userImage);
//
//
//        }


        APIController.apiService.getUserCurrent(LoginActivity.usercurrent.get_id()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Email.setText(user.getEmail());
                userName.setText(user.getFullname());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(ChangeInfoActivity.this)
//                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start() ;

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickChange();

               }
        });


    }

    private void requestPermissions() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImagePicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(ChangeInfoActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    private void openImagePicker() {
        TedRxBottomPicker.OnImageSelectedListener listener = new TedRxBottomPicker.OnImageSelectedListener() {
            @Override
            public void onImageSelected(Uri uri) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    userImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
//        TedRxBottomPicker tedBottomPicker = new TedRxBottomPicker.Builder(ChangeInfoActivity.this)
//                .setOnImageSelectedListener(listener)
//                .create();
//        tedBottomPicker.show(getSupportFragmentManager());



    }

    private void clickChange() {
        String newFullname = changeFullname.getText().toString().trim();
        String newPhone = changePhone.getText().toString().trim();


        APIController.apiService.changeInfo(LoginActivity.usercurrent.get_id(), new User(changeFullname.getText().toString().trim(), LoginActivity.usercurrent.getPassword(),changePhone.getText().toString().trim(),LoginActivity.usercurrent.getEmail())).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("ch", "respone change password: "+ response.toString());
                Toast.makeText(ChangeInfoActivity.this, "UPDATED", Toast.LENGTH_LONG).show();
               //getFragment(new UserInfomationFragment());
                startActivity(new Intent(ChangeInfoActivity.this, LoginActivity.class));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ChangeInfoActivity.this, "Change fullname and phone number fail", Toast.LENGTH_LONG).show();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        userImage.setImageURI(uri);

    }

    //    private void setUserInfo() {
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            for (UserInfo profile : user.getProviderData()) {
//                // Id of the provider (ex: google.com)
//                String providerId = profile.getProviderId();
//
//                // UID specific to the provider
//                String uid = profile.getUid();
//
//                // Name, email address, and profile photo Url
//                String name = profile.getDisplayName();
//                String email = profile.getEmail();
//                Uri photoUrl = profile.getPhotoUrl();
//
//                userName.setText(name);
//                Email.setText(email);
//                Glide.with(getApplicationContext()).load(user.getPhotoUrl()).error(R.drawable.ic_person_flat).into(userImage);
//
//
//            }
//        }
//    }


    public void Save(View view) {
        startActivity(new Intent(ChangeInfoActivity.this, UserInfoActivity.class));

    }
}