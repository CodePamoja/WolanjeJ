package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Reg_02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_02);
    }

    public void movetoreg3(View view) {

        startActivity(new Intent(this,Reg03Activity.class));
    }

    public void loadCopyOfId(View view) {

    }
}