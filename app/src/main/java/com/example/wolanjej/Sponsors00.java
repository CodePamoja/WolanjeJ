package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class Sponsors00 extends AppCompatActivity {
    private MaterialCardView materialCardView1,materialCardView2,materialCardView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors00);
        materialCardView1 = findViewById(R.id.card_new_application);
        materialCardView2 = findViewById(R.id.card_new_appliants);
        materialCardView3 = findViewById(R.id.card_saved_application);

        materialCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sponsors00.this, Sponsor01.class);
                startActivity(intent);
            }
        });
    }
}