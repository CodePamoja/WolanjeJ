package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Ewallet3_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ewallet3_1);
    }

    public void withdrawConfirmation(View view) {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("Class", "Ewallet3_1");
        startActivity(intent);
    }
}