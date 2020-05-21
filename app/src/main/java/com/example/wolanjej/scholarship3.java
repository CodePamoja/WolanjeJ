package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class scholarship3 extends AppCompatActivity {

    TextView Toolbartitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship3);
        Toolbartitle = findViewById(R.id.scholartitle);
        Toolbartitle.setText("Institution Information");
        setToolBar();
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbarscholarship);
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

    public void movetostage3(View view) {
        startActivity(new Intent(this,Scholarship04.class));
    }
}