package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Reg_04 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_04);
    }

    public void movetoLogin(View view) {
        Intent movetologin = new Intent(this,LogIn.class);
        startActivity(movetologin);
    }
}