package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class Travel extends AppCompatActivity  implements View.OnClickListener {
    MaterialCardView materialCardView1,materialCardView2,materialCardView3,materialCardView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        materialCardView1 = findViewById(R.id.flight_ticket_card);
        materialCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookFlight.class);
                startActivity(intent);
            }
        });
        materialCardView2 = findViewById(R.id.train_ticket_card);
        materialCardView3 = findViewById(R.id.bus_ticket_card);
        materialCardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookBus01.class);
                startActivity(intent);
            }
        });
        materialCardView4 = findViewById(R.id.booking_ticket_card);

        setToolBar();
    }
    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, services.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movetoLogo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(movetoLogo);
                    }
                }
        );
    }


    @Override
    public void onClick(View v) {

    }
}