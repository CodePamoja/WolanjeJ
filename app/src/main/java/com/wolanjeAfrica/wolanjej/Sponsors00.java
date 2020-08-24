package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class Sponsors00 extends AppCompatActivity {
    private MaterialCardView materialCardView1,materialCardView2,materialCardView3;
    private Toolbar tb;

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

        setToolBar(tb);
    }
    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbar2);
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
}