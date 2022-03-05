package com.example.orderfoodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.orderfoodapp.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class PaymentDetail extends AppCompatActivity {

    TextView txtId,txtAmount,txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);
        txtId = findViewById(R.id.textid);
        txtAmount = findViewById(R.id.textAmount);
        txtStatus = findViewById(R.id.textStatus);

        Intent intent = getIntent();

        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jsonObject.getJSONObject("respone"),intent.getStringExtra("PaymentAmount"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void showDetails(JSONObject respone, String paymentAmount) {
        try {
            txtId.setText(respone.getString("id"));
            txtStatus.setText(respone.getString("status"));
            txtAmount.setText(respone.getString(String.format("$",paymentAmount)));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}