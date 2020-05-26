package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Sponsor03 extends AppCompatActivity {
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor03);
        setToolBar(tb);
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
    public void moveSponsor04(View view) {
        startActivity(new Intent(this,Sponsor04.class));
    }

    public void movetosponsor4(View view) {
    startActivity(new Intent(this,Sponsor04.class));
    }
}