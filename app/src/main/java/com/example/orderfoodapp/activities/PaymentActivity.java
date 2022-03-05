package com.example.orderfoodapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.orderfoodapp.API.TEST.APIController;
import com.example.orderfoodapp.API.User;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.models.OrderItem;
import com.example.orderfoodapp.models.OrderModel;
import com.example.orderfoodapp.payment.Config;
import com.example.orderfoodapp.ui.MyCartFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    public static final int PAYPAL_REQUEST_CODE = 70;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    List<User> mListUser;
    TextView username , address , phone , amount;
    Button btnPay,btnAddress;
    String price="";
    int PLACE_PICKER_REQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        username = findViewById(R.id.edtpay_username);
        address = findViewById(R.id.edtAddress);
        phone = findViewById(R.id.edtpay_phone);
        amount = findViewById(R.id.amount);
        btnPay = findViewById(R.id.btnPay);


        if (isServiceOK()){
            init();
        }
        username.setText("Full name " + LoginActivity.usercurrent.getFullname());
        phone.setText("Phone number " + LoginActivity.usercurrent.getPhone());
        address.setText("Address " + AppUtil.mAddress);


//        btnAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
////                try {
////                    startActivityForResult(builder.build(PaymentActivity.this), PLACE_PICKER_REQUEST);
////                } catch (GooglePlayServicesRepairableException e) {
////                    e.printStackTrace();
////                } catch (GooglePlayServicesNotAvailableException e) {
////                    e.printStackTrace();
////                }
//
////                Intent intent = new Intent(PaymentActivity.this,MapActivity.class);
////                startActivity(intent);
//
//            }
//        });




        float am= (float) 33.33;

        if(MyCartFragment.total_price.getText().toString().trim().isEmpty()){
            amount.setText(0+"");
        }else{
            amount.setText(MyCartFragment.total_price.getText().toString().trim());
        }
        Intent intent = new Intent(this,PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);


        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= username.getText().toString();
                String addre= address.getText().toString();
                String p= phone.getText().toString();
                String u= LoginActivity.usercurrent.get_id();
                APIController.apiService.createOrderForUser(u, new OrderModel(name, addre, p, am)).enqueue(new Callback<OrderModel>() {
                    @Override
                    public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {

                        OrderModel orderModel= response.body();
                        String idOrder= orderModel.getId();
                        Log.d("c","id order ddaay: "+ idOrder);
                        for (int i=0; i<MyCartFragment.list2.size(); i++){
                            Log.d("c", "list ::: "+ MyCartFragment.list2.get(i).getProduct()+ " quantity: "+ MyCartFragment.list2.get(i).getTotal());
                            APIController.apiService.addItemOrderToOrder(idOrder, new OrderItem(MyCartFragment.list2.get(i).getProduct(), Integer.parseInt(MyCartFragment.list2.get(i).getQuantity()))).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    Toast.makeText(PaymentActivity.this, "Payment successfully", Toast.LENGTH_SHORT).show();
                                    LoadingDialog loadingDialog = new LoadingDialog(PaymentActivity.this);
                                    loadingDialog.startLoadingDialog();
                                    Intent intent1 = new Intent(PaymentActivity.this,NotificationActivity.class);
                                    startActivity(intent1);
                                }
                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Toast.makeText(PaymentActivity.this, "Payment failed", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<OrderModel> call, Throwable t) {
                        Toast.makeText(PaymentActivity.this, "Payment failed", Toast.LENGTH_SHORT).show();
                    }
                });


                processPayment();
            }
        });




    }

    private void init(){
//        btnAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(PaymentActivity.this,MapActivity.class);
//                startActivity(intent);
//
//            }
//        });
    }
    public boolean isServiceOK(){
        Log.d(String.valueOf(PaymentActivity.this),"isService OK");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(PaymentActivity.this);

        if (available == ConnectionResult.SUCCESS){
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(PaymentActivity.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "We can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void processPayment() {
//        price = amount.getText().toString();
//        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(100),"USD",
//                "Checkout",PayPalPayment.PAYMENT_INTENT_SALE);
//
//        Intent intent = new Intent(this, com.paypal.android.sdk.payments.PaymentActivity.class);
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
//        intent.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT,payPalPayment);
//        startActivityForResult(intent,PAYPAL_REQUEST_CODE);
        Intent intent = new Intent(PaymentActivity.this,NotificationActivity.class);
        startActivity(intent);
    }
//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST){
            if (resultCode == RESULT_OK){

                Place place = PlacePicker.getPlace(data,this);
                StringBuilder stringBuilder = new StringBuilder();
                String latitdue = String.valueOf(place.getLatLng().latitude);
                String longtidue = String.valueOf(place.getLatLng().longitude);
                stringBuilder.append("LATITUE :");
                stringBuilder.append(latitdue);
                stringBuilder.append("\n");
                stringBuilder.append("LONGITUDE");
                stringBuilder.append(longtidue);
                address.setText(stringBuilder.toString());


            }
        }
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

}