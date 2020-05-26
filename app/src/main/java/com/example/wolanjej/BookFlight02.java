package com.example.wolanjej;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

public class BookFlight02 extends AppCompatActivity {
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_flight02);

        calendarView = findViewById(R.id.calender);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

            }
        });

    }
    public void MovetoBookFlightO3(View view) {
        startActivity(new Intent(this,BookFlight02.class));
    }
}