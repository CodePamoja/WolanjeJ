package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class Sponsor06 extends AppCompatActivity {

    Toolbar tb;

    MaterialCardView card1,card2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor06);
        setToolBar(tb);


        card1 = findViewById(R.id.full);
        card2 =findViewById(R.id.partial);


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                card1.setBackgroundColor(getResources().getColor(R.color.blue_grey));
                card2.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                findViewById(R.id.full_scholarship).setVisibility(View.VISIBLE);
                findViewById(R.id.partial_scholarship).setVisibility(View.INVISIBLE);


            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                card1.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                card2.setCardBackgroundColor(getResources().getColor(R.color.blue_grey));
                findViewById(R.id.full_scholarship).setVisibility(View.INVISIBLE);
                findViewById(R.id.partial_scholarship).setVisibility(View.VISIBLE);
                findViewById(R.id.thirty).setElevation(8);
                findViewById(R.id.fifty).setElevation(8);
                findViewById(R.id.seventyfive).setElevation(8);
                findViewById(R.id.thirty).setClickable(true);
                findViewById(R.id.fifty).setClickable(true);
                findViewById(R.id.seventyfive).setClickable(true);
            }
        });
        setToolBar(tb);
    }
    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,Sponsor05.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }
    public void movetosponsor7(View view) {
        startActivity(new Intent(this,Sponsor07.class));
    }

}
