package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class Reg_04 extends AppCompatActivity {

    private TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_04);

        tx = (TextView)findViewById(R.id.mytlc);
        tx.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void movetoLogin(View view) {
        Intent movetologin = new Intent(this,LogIn.class);
        startActivity(movetologin);
    }
}