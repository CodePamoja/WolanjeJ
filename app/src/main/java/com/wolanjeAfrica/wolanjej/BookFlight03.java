package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolanjeAfrica.wolanjej.models.AvailableFlights;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.BookFlight03Adapter;

import java.util.ArrayList;
import java.util.List;

public class BookFlight03 extends AppCompatActivity implements BookFlight03Adapter.OnFlightListener {

    private TextView textView, textView1, textView2, textView4;
    private RecyclerView recyclerView;
    private ImageView imageView;
    private LinearLayoutManager layoutManager;
    private List<AvailableFlights> availableFlights = new ArrayList<>();
    private String sessionID;
    private String AGENTNO;
    private SharedPreferences pref;
    private Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_flight03);


        pref = getApplication().getSharedPreferences("LogIn", Context.MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");

        textView = findViewById(R.id.fromBook03);
        textView1 = findViewById(R.id.toBook03);
        textView2 = findViewById(R.id.dateBook03);
        textView4 = findViewById(R.id.travellersBook03);
        imageView = findViewById(R.id.imgTOpB03);
        recyclerView = findViewById(R.id.recyclerViewBookFlight03);

        AirFlightDetails03();

        setToolBar(tb);
    }
    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbar2);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,BookFlight02.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }
    public  void moveTobK04(View V){
        startActivity(new Intent(this,Bookflight04.class));
    }

    @Override
    public void OnFlightClick(int position) {
        Intent intent = new Intent(getApplicationContext(), Bookflight04.class);
        intent.putExtra("Flight Details", availableFlights.get(position));

        startActivity(intent);
    }
    public void AirFlightDetails03(){

        String imageUrl = "https://images.pexels.com/photos/2954199/pexels-photo-2954199.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";
        availableFlights.add(new AvailableFlights( imageUrl,"Kenya Airways", "2hr", "30m", "(1 stop)","USD 500","7 tickets left", "NRB", "11:00am", "USA", "9:00pm"));
        availableFlights.add(new AvailableFlights( imageUrl,"Emirates Airlines", "2hr", "50m", "(1 stop)","USD 500","7 tickets left", "NRB", "11:00am", "USA", "9:00pm"));
        availableFlights.add(new AvailableFlights( imageUrl,"Turkish AirLines", "2hr", "40m", "(0 stops)","USD 500","7tickets left", "NRB", "11:00am", "USA", "9:00pm"));
        availableFlights.add(new AvailableFlights( imageUrl,"Srilankan Airlines", "2hr", "40m", "(0 stops)","USD 500","7tickets left", "NRB", "11:00am", "USA", "9:00pm"));
        InitiateRecyclerView();
    }

    private void InitiateRecyclerView(){

        layoutManager = new LinearLayoutManager(BookFlight03.this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recyclerViewBookFlight03);
        recyclerView.setLayoutManager(layoutManager);
        BookFlight03Adapter adapter = new BookFlight03Adapter(getApplicationContext(), availableFlights, this);
        recyclerView.setAdapter(adapter);
    }
}
