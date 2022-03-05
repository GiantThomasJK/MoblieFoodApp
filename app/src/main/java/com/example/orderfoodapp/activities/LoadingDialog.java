package com.example.orderfoodapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.view.LayoutInflater;

import com.example.orderfoodapp.R;

public class LoadingDialog {

    private Fragment fragment;
    private Activity activity;
    private AlertDialog dialog;


    public LoadingDialog(Activity myActivity){
        activity = myActivity;


    }
    public void LoadingDialogFragment(Fragment myFragment){
        fragment = myFragment;
    }

    void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custome_dialog, null));
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }

    void dismissDialog(){
        dialog.dismiss();
    }
}
