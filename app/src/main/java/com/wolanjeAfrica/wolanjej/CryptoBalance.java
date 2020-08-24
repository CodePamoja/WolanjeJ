package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.models.Model2;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.MyAdapter2;
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
        setActionBarColor();

    }

    private void setActionBarColor() {
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorWhite));
    }

    private void settoolbar(){
        Toolbar tb= (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetologo = new Intent(this,Home.class);
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
        m.setImage(R.drawable.ic_bitcoin);
        m.setBackgroundColor(getResources().getColor(R.color.colorWarmpurple));
        models.add(m);

        Model2 m1 = new Model2();
        m1.setTitle("Ethereum");
        m1.setTitle2("5.80112");
        m1.setTitle3("ETH");
        m1.setTitle4("+1243(13%)");
        m1.setImage(R.drawable.ic_etherium);
        m1.setBackgroundColor(getResources().getColor(R.color.reddish_orange));
        models.add(m1);

        Model2 m2 = new Model2();
        m2.setTitle("Electricity");
        m2.setTitle2("5.80112");
        m2.setTitle3("ETH");
        m2.setTitle4("+1243(13%)");
        m2.setImage(R.drawable.ic_bitcoin);
        m2.setBackgroundColor(getResources().getColor(R.color.colorGreenBlue));
        models.add(m2);

        return models;
    }

    @Override
    public void onClick(View v) {

    }
}