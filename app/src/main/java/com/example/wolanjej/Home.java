package com.example.wolanjej;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Home extends AppCompatActivity {
    Toolbar tb;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);
        tb = findViewById(R.id.toolbarhome);
        drawer = findViewById(R.id.drawer_layout);

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

    public void movetoSettings48(MenuItem item){
        Intent intent = new Intent(this, Settings48.class);
        startActivity(intent);

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
}