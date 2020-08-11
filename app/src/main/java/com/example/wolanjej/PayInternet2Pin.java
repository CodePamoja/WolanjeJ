package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PayInternet2Pin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_internet2);
    }

    public void ConfirmPymentOfInternetOrWater(View view) {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("Class", "PayInternet2Pin");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}