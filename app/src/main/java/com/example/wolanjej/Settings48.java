package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings48 extends AppCompatActivity {

    private Switch fingerswitch;
    private TextView mytext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings48);

        if (Build.VERSION.SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        setToolBar();

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



    public void movetoIdentityCard(View view) {
        Intent move = new Intent(this, IdentifyCard51.class);
        startActivity(move);
    }
    public void movetoPwdPIN(View view) {
        Intent move = new Intent(this, Pin49.class);
        startActivity(move);
    }

    public void controlfingerprint(View view) {
        fingerswitch = (Switch)findViewById(R.id.fingercontrol);

        if(fingerswitch.isChecked()){
            Toast.makeText(this, "Enabled fingerprint", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Disabled fingerprint", Toast.LENGTH_SHORT).show();

        }

    }

    public void controlnotification(View view) {
        fingerswitch = (Switch)findViewById(R.id.notiswitch);

        if(fingerswitch.isChecked()){
            Toast.makeText(this, "Enabled Notifications", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Disabled Notifications", Toast.LENGTH_SHORT).show();

        }
    }

    public void controlwallet(View view) {
        fingerswitch = (Switch)findViewById(R.id.unlockwallet);

        if(fingerswitch.isChecked()){
            Toast.makeText(this, "Wallet unlocked", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Wallet locked", Toast.LENGTH_SHORT).show();

        }
    }
}