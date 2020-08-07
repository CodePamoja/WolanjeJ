package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BusChooseSeat05 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_choose_seat05);
    }

    public void MoveToBookBus06(View view) {
        Intent intent = new Intent(getApplicationContext(), BookBus06.class);
        startActivity(intent);
    }
}