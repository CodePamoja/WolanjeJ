package com.example.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;

public class CryptoBalance extends AppCompatActivity implements View.OnClickListener{
    RecyclerView mRecyclerView;
    MyAdapter2 myAdapter;
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_balance);

        mRecyclerView = findViewById(R.id.recycler_view1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        myAdapter = new MyAdapter2(this,getMylist());
        mRecyclerView.setAdapter(myAdapter);

        lineChart = (LineChart) findViewById(R.id.linegraph);
        ArrayList<String> xAxes = new ArrayList<>();
        ArrayList<String> yAxes = new ArrayList<>();

        settoolbar();

    }
    private void settoolbar(){
        Toolbar tb= (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetologo = new Intent(this,screen18.class);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(movetologo);
            }
        });

    }

    private ArrayList<Model2> getMylist() {

        ArrayList<Model2>models = new ArrayList<>();
        Model2 m = new Model2();
        m.setTitle("Bitcoin");
        m.setTitle2("9.88032");
        m.setTitle3("BTC");
        m.setTitle4("+1243(13%)");
        m.setImage(R.mipmap.bitcoin2);
        m.setBackgroundColor(getResources().getColor(R.color.colorWarmpurple));
        models.add(m);

        Model2 m1 = new Model2();
        m1.setTitle("Ethereum");
        m1.setTitle2("5.80112");
        m1.setTitle3("ETH");
        m1.setTitle4("+1243(13%)");
        m1.setImage(R.mipmap.etherium);
        m1.setBackgroundColor(getResources().getColor(R.color.reddish_orange));
        models.add(m1);

        Model2 m2 = new Model2();
        m2.setTitle("Electricity");
        m2.setTitle2("5.80112");
        m2.setTitle3("ETH");
        m2.setTitle4("+1243(13%)");
        m2.setImage(R.mipmap.bitcoin2);
        m2.setBackgroundColor(getResources().getColor(R.color.colorGreenBlue));
        models.add(m2);

        return models;
    }

    @Override
    public void onClick(View v) {

    }
}