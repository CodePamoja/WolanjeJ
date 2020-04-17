package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Reg03Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_03);
    }

    public void movetoreg04(View view) {
        Intent movetoreg4 = new Intent(this,Reg_04.class);
        startActivity(movetoreg4);
    }
}