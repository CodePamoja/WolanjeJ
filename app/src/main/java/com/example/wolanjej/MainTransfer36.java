package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainTransfer36 extends AppCompatActivity {

    private CardView walletCard, bankCard, phoneCard;
    private String sessionID;
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transfer36);
        setToolBar();

        Intent intent = getIntent();
        this.sessionID = intent.getStringExtra(Home.EXTRA_SESSION);
//        Log.e("sessionID on mainTrans",intent.getStringExtra(LogIn.EXTRA_SESSION));

        walletCard = (CardView)findViewById(R.id.transfer_to_wallet);
        walletCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toWallet();
            }
        });

        bankCard = (CardView)findViewById(R.id.transfer_to_bank);
        bankCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBank();
            }
        });

        phoneCard = (CardView)findViewById(R.id.transfer_to_phone);
        phoneCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("sessionID to mpesa",sessionID);
                toPhone();
            }
        });
    }

    public void toWallet() {
        Intent move = new Intent(this, TransferToWalletSingle37.class);
        startActivity(move);
    }

    public void toBank() {
        Intent move = new Intent(this, TransferToBank44.class);
        startActivity(move);
    }

    public void toPhone() {
        Log.e("sessionID to mpesa",sessionID);
        Intent move = new Intent(this,TransferToPhone50.class);
        move.putExtra(EXTRA_SESSION, sessionID);
        startActivity(move);
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this,Home.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );

    }
}