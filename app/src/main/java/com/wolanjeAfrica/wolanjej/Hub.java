package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Hub extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        setToolBar();
    }


    private void setToolBar() {
        androidx.appcompat.widget.Toolbar tb = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbarhome);
        setSupportActionBar(tb);
        final Intent movetoLogo = new Intent(this,Home.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );

    }
}