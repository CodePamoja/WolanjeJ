package com.example.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.card.MaterialCardView;

public class LinkAccount11 extends AppCompatActivity implements View.OnClickListener {

    private MaterialCardView card_westernunion, card_worldplay, card_unionpay, card_mastercard, card_visa, card_paypal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_account11);
        setToolBar();
        //defining cards
        card_westernunion = (MaterialCardView) findViewById(R.id.card_westernunion);
        card_worldplay = (MaterialCardView) findViewById(R.id.card_worldplay);
        card_unionpay = (MaterialCardView) findViewById(R.id.card_unionpay);
        card_mastercard = (MaterialCardView) findViewById(R.id.card_mastercard);
        card_visa = (MaterialCardView) findViewById(R.id.card_visa);
        card_paypal = (MaterialCardView) findViewById(R.id.card_paypal);

        //addclick listener
        card_westernunion.setOnClickListener(this);
        card_worldplay.setOnClickListener(this);
        card_unionpay.setOnClickListener(this);
        card_mastercard.setOnClickListener(this);
        card_visa.setOnClickListener(this);
        card_paypal.setOnClickListener(this);


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
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.bShadeGray));


    }


    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
//        final Intent movetoLogo = new Intent(this,MainActivity.class);
//        tb.setNavigationOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(movetoLogo);
//                    }
//                }
//        );

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.card_westernunion: i = new Intent(this,Registration06.class);startActivity(i); break;
            case R.id.card_worldplay: i = new Intent(this,
                    Home.class);startActivity(i); break;
            default:break;
        }
    }

    public void moveToHome(View view) {
        Intent movetohome = new Intent(this,Home.class);
        movetohome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(movetohome);
        finish();
    }
}