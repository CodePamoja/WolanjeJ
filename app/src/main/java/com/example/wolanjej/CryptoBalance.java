package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class CryptoBalance extends AppCompatActivity implements View.OnClickListener{
    RecyclerView mRecyclerView;
    MyAdapter2 myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_balance);

        mRecyclerView = findViewById(R.id.recycler_view1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        myAdapter = new MyAdapter2(this,getMylist());
        mRecyclerView.setAdapter(myAdapter);

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
        m1.setImage(R.mipmap.bitcoin);
        m1.setBackgroundColor(getResources().getColor(R.color.reddish_orange));
        models.add(m1);

        Model2 m2 = new Model2();
        m2.setTitle("Electricity");
        m2.setTitle2("5.80112");
        m2.setTitle3("ETH");
        m2.setTitle4("+1243(13%)");
        m2.setImage(R.mipmap.bitcoin);
        m2.setBackgroundColor(getResources().getColor(R.color.colorGreenBlue));
        models.add(m2);

        return models;
    }

    @Override
    public void onClick(View v) {

    }
}