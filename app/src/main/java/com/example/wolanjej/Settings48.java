package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

public class Settings48 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings48);

        if (Build.VERSION.SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Button button = (Button) findViewById(R.id.btn_profile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movetoIdentityCard();
            }
        });

        Button button1 = (Button) findViewById(R.id.btn_pwdPIN);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movetoPwdPIN();
            }
        });

    }
    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
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


    public void movetoIdentityCard() {
        Intent move = new Intent(this, IdentifyCard51.class);
        startActivity(move);
    }
    public void movetoPwdPIN() {
        Intent move = new Intent(this, Pin49.class);
        startActivity(move);
    }
}