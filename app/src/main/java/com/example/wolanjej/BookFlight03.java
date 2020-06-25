package com.example.wolanjej;

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

import com.example.wolanjej.models.AvailableFlights;
import com.example.wolanjej.recyclerAdapters.BookFlight03Adapter;
import com.example.wolanjej.recyclerAdapters.RecyclerViewHomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookFlight03 extends AppCompatActivity {

    private TextView textView, textView1, textView2, textView4;
    private RecyclerView recyclerView;
    private ImageView imageView;
    private LinearLayoutManager layoutManager;
    private List<AvailableFlights> historyList = new ArrayList<>();
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

        layoutManager = new LinearLayoutManager(BookFlight03.this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recyclerViewBookFlight03);
        recyclerView.setLayoutManager(layoutManager);
        BookFlight03Adapter adapter = new BookFlight03Adapter(BookFlight03.this, historyList);
        recyclerView.setAdapter(adapter);

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
//    TODO: get filght details
}
