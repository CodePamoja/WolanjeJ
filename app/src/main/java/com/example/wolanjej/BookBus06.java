package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BookBus06 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_bus06);
    }

    public void MoveToBookBu09(View view) {
        Intent intent = new Intent(getApplicationContext(), EnterPin.class);
        intent.putExtra("Class", "BookBus06");
        startActivity(intent);
    }
}