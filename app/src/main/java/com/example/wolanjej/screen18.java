package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class screen18 extends AppCompatActivity implements View.OnClickListener {

    private CardView saved_billers, electricity, pay_internet, purchace_air, transfer, income_details, wallet, services, exchange, crypto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen18);

        //defining cards
        saved_billers = (CardView) findViewById(R.id.saved_billers);
        electricity = (CardView) findViewById(R.id.electricity);
        pay_internet = (CardView) findViewById(R.id.pay_internet);
        purchace_air = (CardView) findViewById(R.id.purchase_air);
        transfer = (CardView) findViewById(R.id.transfer);
        income_details = (CardView) findViewById(R.id.income_details);
        wallet = (CardView) findViewById(R.id.wallet);
        services = (CardView) findViewById(R.id.services);
        exchange = (CardView) findViewById(R.id.exchange);
        crypto = (CardView) findViewById(R.id.crypto);

        //addclick listener
        saved_billers.setOnClickListener((View.OnClickListener) this);
        electricity.setOnClickListener((View.OnClickListener) this);
        pay_internet.setOnClickListener((View.OnClickListener) this);
        purchace_air.setOnClickListener((View.OnClickListener) this);
        transfer.setOnClickListener((View.OnClickListener) this);
        income_details.setOnClickListener((View.OnClickListener) this);
        wallet.setOnClickListener((View.OnClickListener) this);
        services.setOnClickListener((View.OnClickListener) this);
        exchange.setOnClickListener((View.OnClickListener) this);
        crypto.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.saved_billers: i = new Intent(this, screen18.class);startActivity(i);break;
            case R.id.electricity: i = new Intent(this, Registration05.class);startActivity(i);break;
            case R.id.pay_internet: i = new Intent(this, screen18.class);startActivity(i);break;
            case R.id.purchase_air: i = new Intent(this, Registration05.class);startActivity(i);break;
            case R.id.transfer: i = new Intent(this, screen18.class);startActivity(i);break;
            case R.id.income_details: i = new Intent(this, Registration05.class);startActivity(i);break;
            case R.id.wallet: i = new Intent(this, screen18.class);startActivity(i);break;
            case R.id.services: i = new Intent(this, Registration05.class);startActivity(i);break;
            case R.id.exchange: i = new Intent(this, screen18.class);startActivity(i);break;
            case R.id.crypto: i = new Intent(this, Registration05.class);startActivity(i);break;

            default:
                break;
        }
    }
}