package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.android.material.card.MaterialCardView;

public class Sponsor02 extends AppCompatActivity {

    private Toolbar tb;
    private MaterialCardView materialCardView1, materialCardView2, materialCardView3, materialCardView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor02);

        materialCardView1 = findViewById(R.id.card_primary);
        materialCardView2 = findViewById(R.id.card_secondary);
        materialCardView3 = findViewById(R.id.card_tertiary);
        materialCardView4 = findViewById(R.id.card_VTI);

        materialCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                materialCardView1.setCardBackgroundColor(getResources().getColor(R.color.blue_grey));
                materialCardView2.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                materialCardView3.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                materialCardView4.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                findViewById(R.id.tick_primary).setVisibility(View.VISIBLE);
                findViewById(R.id.tick_secondary).setVisibility(View.INVISIBLE);
                findViewById(R.id.tick_tertiary).setVisibility(View.INVISIBLE);
                findViewById(R.id.tick_Vti).setVisibility(View.INVISIBLE);
            }
        });

        materialCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                materialCardView2.setCardBackgroundColor(getResources().getColor(R.color.blue_grey));
                materialCardView1.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                materialCardView3.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                materialCardView4.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                findViewById(R.id.tick_primary).setVisibility(View.INVISIBLE);
                findViewById(R.id.tick_secondary).setVisibility(View.VISIBLE);
                findViewById(R.id.tick_tertiary).setVisibility(View.INVISIBLE);
                findViewById(R.id.tick_Vti).setVisibility(View.INVISIBLE);

            }
        });

        materialCardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                materialCardView3.setCardBackgroundColor(getResources().getColor(R.color.blue_grey));
                materialCardView1.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                materialCardView2.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                materialCardView4.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                findViewById(R.id.tick_primary).setVisibility(View.INVISIBLE);
                findViewById(R.id.tick_secondary).setVisibility(View.INVISIBLE);
                findViewById(R.id.tick_tertiary).setVisibility(View.VISIBLE);
                findViewById(R.id.tick_Vti).setVisibility(View.INVISIBLE);
            }
        });

        materialCardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                materialCardView4.setCardBackgroundColor(getResources().getColor(R.color.blue_grey));
                materialCardView1.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                materialCardView2.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                materialCardView3.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                findViewById(R.id.tick_primary).setVisibility(View.INVISIBLE);
                findViewById(R.id.tick_secondary).setVisibility(View.INVISIBLE);
                findViewById(R.id.tick_tertiary).setVisibility(View.INVISIBLE);
                findViewById(R.id.tick_Vti).setVisibility(View.VISIBLE);
            }
        });

        setToolBar(tb);
    }
    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,Home.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }

    public void moveSponsor03(View view) {
        startActivity(new Intent(this,Sponsor03.class));
    }

}