package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Scholarship05 extends AppCompatActivity {

    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship05);
        setToolBar(tb);
    }
    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbarscholarship);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,Scholarship04.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }


    public void MoveToScholarShip06(View view) {
        Intent intent = new Intent(Scholarship05.this, Scholarship06.class);
        startActivity(intent);
    }
}
