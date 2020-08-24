package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolanjeAfrica.wolanjej.models.AvailableFlights;
import com.squareup.picasso.Picasso;

public class Bookflight04 extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8;
    private Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookflight04);


        imageView = findViewById(R.id.flighticonBf04);
        textView1 = findViewById(R.id.flightAgencyBf04);
        textView2 = findViewById(R.id.flightduration);
        textView3 = findViewById(R.id.filghtcharge);
        textView4 = findViewById(R.id.flighttickets);
        textView5 = findViewById(R.id.countryfrom);
        textView6 = findViewById(R.id.depatureBf04);
        textView7 = findViewById(R.id.countryto);
        textView8 = findViewById(R.id.arrivalBf04);




        Intent intent = getIntent();
        AvailableFlights availableFlights = intent.getParcelableExtra("Flight Details");


        String mImage = availableFlights.getmAirwayImg();
        String mAirWaysId = availableFlights.getmAirWaysId();
        String mFlightHour = availableFlights.getmFlightHour();
        String mFlightMin = availableFlights.getmFlightMin();
        String mFlightStops = availableFlights.getmFlightStops();
        String mFlightCosts = availableFlights.getmFlightCosts();
        String mTicketsLeft = availableFlights.getmTicketsLeft();
        String mFlightFrom =availableFlights.getmFlightFrom();
        String mFlightDepTime = availableFlights.getmFlightDepTime();
        String mFlightTo = availableFlights.getmFlightTo();
        String mFlightArrivalTime = availableFlights.getmFlightArrivalTime();

        Picasso.get().load(String.valueOf(mImage)).into(imageView);
        textView1.setText(mAirWaysId);
        textView2.setText(mFlightHour);
        textView3.setText(mFlightCosts);
        textView4.setText(mTicketsLeft);
        textView5.setText(mFlightFrom);
        textView6.setText(mFlightDepTime);
        textView7.setText(mFlightTo);
        textView8.setText(mFlightArrivalTime);

//        setToolBar(tb);
    }
    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,BookFlight03.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }

    public void movetobook5(View view) {
        startActivity(new Intent(this,Bookflight05.class));
    }
}