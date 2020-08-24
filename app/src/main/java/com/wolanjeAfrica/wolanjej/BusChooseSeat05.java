package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BusChooseSeat05 extends AppCompatActivity {
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_choose_seat05);
        setToolBar(tb);
    }
    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,BusInfo04.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveToLogo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(moveToLogo);
                    }
                }
        );

    }

    public void MoveToBookBus06(View view) {
        Intent intent = new Intent(getApplicationContext(), BookBus06.class);
        startActivity(intent);
    }
}