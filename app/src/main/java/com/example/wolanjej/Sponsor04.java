package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.card.MaterialCardView;

public class Sponsor04 extends AppCompatActivity {

    Toolbar tb;

    MaterialCardView card1,card2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor04);
        setToolBar(tb);

        card1 = findViewById(R.id.male);
        card2 =findViewById(R.id.female);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                card1.setBackgroundColor(getResources().getColor(R.color.blue_grey));
                card2.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                findViewById(R.id.tickmale).setVisibility(View.VISIBLE);
                findViewById(R.id.tickfemale).setVisibility(View.INVISIBLE);

            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                card1.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                card2.setCardBackgroundColor(getResources().getColor(R.color.blue_grey));
                findViewById(R.id.tickmale).setVisibility(View.INVISIBLE);
                findViewById(R.id.tickfemale).setVisibility(View.VISIBLE);

            }
        });
    }
    private void setToolBar(Toolbar tb) {
        tb = findViewById(R.id.toolbarsponsor);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,HomeTwo.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }

    public void movetosponsor5(View view) {
        startActivity(new Intent(this,Sponsor05.class));
    }
    /*private void genderSelector(MaterialCardView cardone, MaterialCardView cardtwo){
        cardone.setBackgroundColor(getResources().getColor(R.color.blue_grey));
        cardtwo.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
        findViewById(R.id.tickmale).setVisibility(View.VISIBLE);
        findViewById(R.id.tickfemale).setVisibility(View.INVISIBLE);

    }*/
}
