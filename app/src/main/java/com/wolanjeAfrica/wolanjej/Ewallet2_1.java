package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Ewallet2_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ewallet2_1);
    }

    public void SendWalletTopUpConfirmation(View view) {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("Class", "Ewallet2_1");
        startActivity(intent);
    }
}