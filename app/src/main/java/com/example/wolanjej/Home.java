package com.example.wolanjej;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
<<<<<<< HEAD
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
=======
>>>>>>> parent of 3a0d1d2... settings pages linked
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Toolbar tb;
    DrawerLayout drawer;
    Button transferMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);
        tb = findViewById(R.id.toolbarhome);
        drawer = findViewById(R.id.drawer_layout);

        transferMoney = (Button)findViewById(R.id.transfer_money_button);
        transferMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetoTransfer();
            }
        });

//            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,tb,R.string.Open_drawer,R.string.Close_drawer);
        //      drawer.addDrawerListener(toggle);
        //    toggle.syncState();


        setToolBar();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();}
    }


    private void setToolBar() {
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.openDrawer(GravityCompat.START);

                    }
                }
        );

    }

    public void closeMyDrawer(View view) {
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,
                menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void maketoast(MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);

        findViewById(R.id.bottom_navigation).setVisibility(View.INVISIBLE);
        findViewById(R.id.show_ple).setVisibility(View.VISIBLE);
 findViewById(R.id.show_ple).setVisibility(View.VISIBLE);

    }
    public void movetoTransfer(){
        Intent move = new Intent(this, MainTransfer36.class);
        startActivity(move);
    }

    public void close_show_ple(View view) {
        findViewById(R.id.show_ple).setVisibility(View.INVISIBLE);

        findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);

    }
}