package com.example.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeTwo extends AppCompatActivity {

    DrawerLayout drawer;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);

        toolbar = findViewById(R.id.toolbarhome2);
        drawer = findViewById(R.id.drawer_layout_home_two);
        setToolBar();


        findViewById(R.id.mytopupcard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
            }
        });

    }
    private void setToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(
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
    public void maketoast(MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);

        findViewById(R.id.bottom_navigation_home_two).setVisibility(View.INVISIBLE);
        findViewById(R.id.show_ple).setVisibility(View.VISIBLE);

    }
    public void close_show_ple(View view) {
        findViewById(R.id.show_ple).setVisibility(View.INVISIBLE);

        findViewById(R.id.bottom_navigation_home_two).setVisibility(View.VISIBLE);

    }
    public void opensevrvices(MenuItem item) {
        Intent intent = new Intent(this, services.class);
        startActivity(intent);
    }
    public void openIncomingDetails(MenuItem item) {
        Intent intent = new Intent(this, IncomeDetails.class);
        startActivity(intent);
    }
    public void openLearningHub(MenuItem item) {
        Log.e("Found","Troal");
        Intent intent = new Intent(this, Hub.class);
        startActivity(intent);
    }
    public void openProfile(MenuItem item) {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }
    public void movetoSettings48(MenuItem item){
        Intent intent = new Intent(this, Settings48.class);
        startActivity(intent);

    }

}