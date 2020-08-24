package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wolanjeAfrica.wolanjej.models.AvailableBuses;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.BookBus03Adapter;

import java.util.ArrayList;
import java.util.List;

public class BookBus03 extends AppCompatActivity implements BookBus03Adapter.OnBusFlightListener {
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private List<AvailableBuses> availableBuses = new ArrayList<>();
    private Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_bus03);
        recyclerView = findViewById(R.id.recyclerViewBookBus03);
        AirBusFlightDetails03();
        setToolBar(tb);
    }
    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbar2);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,BookBusSelectedDate02.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveToLogo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(moveToLogo);
                    }
                }
        );

    }
    public void AirBusFlightDetails03(){

        String imageUrl = "https://images.pexels.com/photos/2954199/pexels-photo-2954199.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";
        availableBuses.add(new AvailableBuses( imageUrl,"Kenya Airways", "2hr", "30m", "(1 stop)","USD 500","7 tickets left", "NRB", "11:00am", "USA", "9:00pm"));
        availableBuses.add(new AvailableBuses( imageUrl,"Emirates Airlines", "2hr", "50m", "(1 stop)","USD 500","7 tickets left", "NRB", "11:00am", "USA", "9:00pm"));
        availableBuses.add(new AvailableBuses( imageUrl,"Turkish AirLines", "2hr", "40m", "(0 stops)","USD 500","7tickets left", "NRB", "11:00am", "USA", "9:00pm"));
        availableBuses.add(new AvailableBuses( imageUrl,"Srilankan Airlines", "2hr", "40m", "(0 stops)","USD 500","7tickets left", "NRB", "11:00am", "USA", "9:00pm"));
        iniRecyclerView();
    }

    private void iniRecyclerView(){
        layoutManager = new LinearLayoutManager(BookBus03.this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recyclerViewBookBus03);
        recyclerView.setLayoutManager(layoutManager);
        BookBus03Adapter adapter = new BookBus03Adapter(getApplicationContext(), availableBuses, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void OnBusFlightClick(int position) {
        Intent intent = new Intent(getApplicationContext(), BusInfo04.class);
        intent.putExtra("Bus Details",  availableBuses.get(position));
        startActivity(intent);
    }
}