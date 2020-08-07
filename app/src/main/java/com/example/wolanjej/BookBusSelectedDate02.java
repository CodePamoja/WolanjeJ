package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BookBusSelectedDate02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_bus_selected_date02);
    }
    public void MoveToBookBus03(View view){
        Intent intent = new Intent(this, BookBus03.class);
        startActivity(intent);
    }
}