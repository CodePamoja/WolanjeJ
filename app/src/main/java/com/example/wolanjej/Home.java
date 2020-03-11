package com.example.wolanjej;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener{
    Toolbar tb;
    DrawerLayout drawer;
    Button transferMoney, viewall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);
        tb = findViewById(R.id.toolbarhome);
        drawer = findViewById(R.id.drawer_layout);

        viewall = (Button)findViewById(R.id.btnviewall);
        viewall.setOnClickListener(this);

        transferMoney = (Button)findViewById(R.id.transfer_money_button);
        transferMoney.setOnClickListener(this);



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

    public void moveBillManagerPayment(MenuItem item){
        Intent intent = new Intent(this, BillManager.class);
        startActivity(intent);

    }

    public void moveBillManagerBiller(MenuItem item){
        Intent intent = new Intent(this, BillManager.class);
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
//    public void movetoTransfer(){
//        Intent move = new Intent(this, MainTransfer36.class);
//        startActivity(move);
//    }

    public void close_show_ple(View view) {
        findViewById(R.id.show_ple).setVisibility(View.INVISIBLE);

        findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.btnviewall: i = new Intent(this,screen18.class);startActivity(i); break;
            case R.id.transfer_money_button: i = new Intent(this,MainTransfer36.class);startActivity(i); break;
            default:break;
        }
    }
}