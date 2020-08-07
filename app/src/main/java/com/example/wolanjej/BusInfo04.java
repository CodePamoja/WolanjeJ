package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wolanjej.models.AvailableBuses;
import com.example.wolanjej.models.AvailableFlights;
import com.squareup.picasso.Picasso;

public class BusInfo04 extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8;
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_info04);
        imageView = findViewById(R.id.flightIconBusInfo04);
        textView1 = findViewById(R.id.TextBusInfo01);
        textView2 = findViewById(R.id.flightDurationText02);
        textView3 = findViewById(R.id.Textfilghtcharge03);
        textView4 = findViewById(R.id.flightticketsBus04);
        textView5 = findViewById(R.id.townBusFrom);
        textView6 = findViewById(R.id.depatureBInfo04);
        textView7 = findViewById(R.id.TownBusTo);
        textView8 = findViewById(R.id.arrivalBInfo04);

        Intent intent = getIntent();
        AvailableBuses availableBuses = intent.getParcelableExtra("Bus Details");

        String mImage = availableBuses.getmBusCompanyImg();
        String mAirWaysId = availableBuses.getmBusCompanyId();
        String mFlightHour = availableBuses.getmBusFlightHour();
        String mFlightMin = availableBuses.getmBusFlightMin();
        String mFlightStops = availableBuses.getmBusFlightStops();
        String mFlightCosts = availableBuses.getmBusFlightCosts();
        String mTicketsLeft = availableBuses.getmBusTicketsLeft();
        String mFlightFrom = availableBuses.getmBusFlightFrom();
        String mFlightDepTime = availableBuses.getmBusFlightDepTime();
        String mFlightTo = availableBuses.getmBusFlightTo();
        String mFlightArrivalTime = availableBuses.getmBusFlightArrivalTime();

        Picasso.get().load(String.valueOf(mImage)).into(imageView);
        textView1.setText(mAirWaysId);
        textView2.setText(mFlightHour);
        textView3.setText(mFlightCosts);
        textView4.setText(mTicketsLeft);
        textView5.setText(mFlightFrom);
        textView6.setText(mFlightDepTime);
        textView7.setText(mFlightTo);
        textView8.setText(mFlightArrivalTime);
    }

    public void MoveToBus05(View view) {
        Intent intent = new Intent(getApplicationContext(), BusChooseSeat05.class);
        startActivity(intent);
    }
}