package com.example.orderfoodapp.activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.orderfoodapp.R;

public class OTPVerificationDialog extends Dialog {

    private EditText otpET1 , otpET2,otpET3,otpET4;
    private TextView resendBtn;
    private Button verifyBtn;


    private int resendTime = 60;
    private Boolean resendEnable = false;

    private final String addemailTxt;

    private int selectedETPosition = 0;


    public OTPVerificationDialog(@NonNull Context context, String addemail) {
        super(context);
        this.addemailTxt = addemail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(getContext().getResources().getColor(android.R.color.transparent)));
        setContentView(R.layout.otp_dialog_layout);

        otpET1 = findViewById(R.id.otpET1);
        otpET2 = findViewById(R.id.otpET2);
        otpET3 = findViewById(R.id.otpET3);
        otpET4 = findViewById(R.id.otpET4);

        resendBtn = findViewById(R.id.resendBtn);
        verifyBtn = findViewById(R.id.verifyBtn);

        final TextView addemail = findViewById(R.id.EmailAddress);


        otpET1.addTextChangedListener(textWatcher);
        otpET2.addTextChangedListener(textWatcher);
        otpET3.addTextChangedListener(textWatcher);
        otpET4.addTextChangedListener(textWatcher);


        showKeyboard(otpET1);

        startCountDown();

        addemail.setText(addemailTxt);

        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resendEnable){

                    startCountDown();
                }
            }
        });

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String getOTP = otpET1.getText().toString() + otpET2.getText().toString() + otpET3.getText().toString() + otpET4.getText().toString();

                if (getOTP.length() == 4){

                }
            }
        });
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0){
                if (selectedETPosition == 0){
                    selectedETPosition = 1;
                    showKeyboard(otpET2);
                }
                else if (selectedETPosition == 1){
                    selectedETPosition = 2;
                    showKeyboard(otpET3);
                }
                else if (selectedETPosition == 2){
                    selectedETPosition = 3;
                    showKeyboard(otpET4);
                }
                else {
                    verifyBtn.setBackgroundColor(R.drawable.round_back_red_100);
                }
            }
        }
    };

    private void showKeyboard(EditText otpET){
        otpET.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otpET, InputMethodManager.SHOW_IMPLICIT);


    }

    private void startCountDown(){
        resendEnable = false;
        resendBtn.setTextColor(Color.parseColor("#99000000"));

        new CountDownTimer(resendTime * 1000 , 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                resendBtn.setText("Resend Code (" + (millisUntilFinished / 1000)+")");
            }

            @Override
            public void onFinish() {
                resendEnable = true;
                resendBtn.setTextColor(getContext().getResources().getColor(android.R.color.holo_blue_dark));
            }
        }.start();
    }

    @Override
    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_DEL){

            if (selectedETPosition == 3){
                selectedETPosition = 2;
                showKeyboard(otpET3);
            }

            else if (selectedETPosition == 2){
                selectedETPosition = 1;
                showKeyboard(otpET2);
            }

            else if (selectedETPosition == 1){
                selectedETPosition = 0;
                showKeyboard(otpET1);
            }

            verifyBtn.setBackgroundColor(R.drawable.round_back_brown_100);
            return true;
        }
        else {
            return super.onKeyUp(keyCode, event);

        }
    }
}
