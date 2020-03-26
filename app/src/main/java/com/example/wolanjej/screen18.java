package com.example.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class screen18 extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "screen18";
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    private MaterialCardView  transfer, income_details, wallet, services, exchange, crypto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen18);


        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        myAdapter = new MyAdapter(this,getMylist());
        mRecyclerView.setAdapter(myAdapter);


        transfer = (MaterialCardView) findViewById(R.id.transfer);
        income_details = (MaterialCardView) findViewById(R.id.income_details);
        wallet = (MaterialCardView) findViewById(R.id.wallet);
        services = (MaterialCardView) findViewById(R.id.services);
        exchange = (MaterialCardView) findViewById(R.id.exchange);
        crypto = (MaterialCardView) findViewById(R.id.crypto);


          transfer.setOnClickListener(this);
          income_details.setOnClickListener(this);
          wallet.setOnClickListener(this);
          services.setOnClickListener(this);
          exchange.setOnClickListener(this);
          crypto.setOnClickListener(this);


    }
    //    }

    public void close_show_ple(View view) {
        Log.e("yes","pressed");
        findViewById(R.id.screen_16).setVisibility(View.INVISIBLE);

    }

    public ArrayList<Model> getMylist() {
        ArrayList<Model>models = new ArrayList<>();
        Model m = new Model();
        m.setTitle("Pay Tv");
        m.setImage(R.mipmap.group_18a);
        models.add(m);

        Model m3 = new Model();
        m3.setTitle("Saved Billers");
        m3.setImage(R.mipmap.group_18);
        models.add(m3);

        Model m1 = new Model();
        m1.setTitle("Electricity");
        m1.setImage(R.mipmap.group_18c);
        models.add(m);

        Model m2 = new Model();
        m2.setTitle("Pay Internet");
        m2.setImage(R.mipmap.group_18d);
        models.add(m2);

        Model m4 = new Model();
        m4.setTitle("Buy Airtime");
        m4.setImage(R.mipmap.group_18d);
        models.add(m4);

        return models;
    }

    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.transfer: i = new Intent(this, screen18.class);startActivity(i);break;
            case R.id.income_details: i = new Intent(this, IncomeDetails.class);startActivity(i);break;
            case R.id.wallet: i = new Intent(this, Home.class);startActivity(i);break;
            case R.id.services: i = new Intent(this, services.class);startActivity(i);break;
            case R.id.exchange: i = new Intent(this, screen18.class);startActivity(i);break;
            case R.id.crypto: i = new Intent(this,CryptoBalance.class);startActivity(i);break;

            default:
                break;
        }
    }

}