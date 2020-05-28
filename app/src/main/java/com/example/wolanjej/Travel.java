package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

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
        materialCardView4 = findViewById(R.id.booking_ticket_card);

    }

    @Override
    public void onClick(View v) {

    }
}