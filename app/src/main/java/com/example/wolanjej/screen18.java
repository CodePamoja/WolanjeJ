package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class screen18 extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "screen18";
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    private CardView saved_billers, electricity, pay_internet, purchace_air, transfer, income_details, wallet, services, exchange, crypto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen18);


        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        myAdapter = new MyAdapter(this,getMylist());
        mRecyclerView.setAdapter(myAdapter);


        transfer = (CardView) findViewById(R.id.transfer);
        income_details = (CardView) findViewById(R.id.income_details);
        wallet = (CardView) findViewById(R.id.wallet);
        services = (CardView) findViewById(R.id.services);
        exchange = (CardView) findViewById(R.id.exchange);
        crypto = (CardView) findViewById(R.id.crypto);


          transfer.setOnClickListener(this);
          income_details.setOnClickListener(this);
          wallet.setOnClickListener(this);
          services.setOnClickListener(this);
          exchange.setOnClickListener(this);
          crypto.setOnClickListener(this);


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
        models.add(m1);

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
            case R.id.income_details: i = new Intent(this, Registration05.class);startActivity(i);break;
            case R.id.wallet: i = new Intent(this, screen18.class);startActivity(i);break;
            case R.id.services: i = new Intent(this, Registration05.class);startActivity(i);break;
            case R.id.exchange: i = new Intent(this, screen18.class);startActivity(i);break;
            case R.id.crypto: i = new Intent(this,CryptoBalance.class);startActivity(i);break;

            default:
                break;
        }
    }

}