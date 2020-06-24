package com.example.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wolanjej.models.Model;
import com.example.wolanjej.recyclerAdapters.MyAdapter;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class Screen16 extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen18);

        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        MyAdapter myAdapter = new MyAdapter(this, getMylist());
        mRecyclerView.setAdapter(myAdapter);


        MaterialCardView transfer101 = (MaterialCardView) findViewById(R.id.transfer101);
        transfer101.setOnClickListener(this);

        MaterialCardView income_details101 = (MaterialCardView) findViewById(R.id.income_details101);
        income_details101.setOnClickListener(this);

        MaterialCardView wallet101 = (MaterialCardView) findViewById(R.id.wallet101);
        wallet101.setOnClickListener(this);

        MaterialCardView services101 = (MaterialCardView) findViewById(R.id.services101);
        services101.setOnClickListener(this);

        MaterialCardView exchange101 = (MaterialCardView) findViewById(R.id.exchange101);
        exchange101.setOnClickListener(this);

        MaterialCardView crypto101 = (MaterialCardView) findViewById(R.id.crypto101);
        crypto101.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.income_details101:
                i = new Intent(this, IncomeDetails.class);
                startActivity(i);
                break;
            case R.id.wallet101:
                i = new Intent(this, Home.class);
                startActivity(i);
                break;
            case R.id.services101:
                i = new Intent(this, services.class);
                startActivity(i);
                break;
            case R.id.exchange101:
                i = new Intent(this, Home.class);
                startActivity(i);
                break;
            case R.id.crypto101:
                i = new Intent(this, CryptoBalance.class);
                startActivity(i);
                break;
            case R.id.transfer101:
                i = new Intent(this, MainTransfer36.class);
                startActivity(i);
                break;
        }
    }
    public ArrayList<Model> getMylist() {
        ArrayList<Model>models = new ArrayList<>();
        Model m = new Model();
        m.setTitle("Pay Tv");
        m.setImage(R.drawable.ic_exchange);
        models.add(m);

        Model m3 = new Model();
        m3.setTitle("Saved Billers");
        m3.setImage(R.mipmap.group_18);
        models.add(m3);

        Model m1 = new Model();
        m1.setTitle("Electricity");
        m1.setImage(R.drawable.ic_services);
        models.add(m);

        Model m2 = new Model();
        m2.setTitle("Pay Internet");
        m2.setImage(R.drawable.ic_wallet);
        models.add(m2);

        Model m4 = new Model();
        m4.setTitle("Buy Airtime");
        m4.setImage(R.drawable.ic_wallet);
        models.add(m4);

        return models;
    }

}
