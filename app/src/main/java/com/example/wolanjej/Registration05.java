package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class Registration05 extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_registration05);

        setToolBar();
        imageView = (ImageView)findViewById(R.id.image_holder);
        imageView.setImageResource(R.mipmap.group_5);
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this,MainActivity.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    public void movetoLog(MenuItem item) {
        Intent movetoLogo = new Intent(this,CardLink.class);
        startActivity(movetoLogo);
    }

    public void sendtoVerification(View view) {
        Intent movetoLogo = new Intent(this,Registartion06.class);
        startActivity(movetoLogo);
    }
}