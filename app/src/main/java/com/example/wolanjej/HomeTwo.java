package com.example.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class HomeTwo extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);

        toolbar = findViewById(R.id.toolbarhome2);
        drawer = findViewById(R.id.drawer_layout_home_two);

        MaterialCardView materialCardView = findViewById(R.id.card1_home_two);
        materialCardView.setOnClickListener(this);
        


        //     this  belongs to  screen 18
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        MyAdapter myAdapter = new MyAdapter(this, getMylist());
        mRecyclerView.setAdapter(myAdapter);


        Button viewall = (Button) findViewById(R.id.btn_view_all_home_two);
        viewall.setOnClickListener(this);

        Button transferMoney = (Button) findViewById(R.id.transfer_money_button_hm2);
        transferMoney.setOnClickListener(this);

        MaterialCardView transfer101 = (MaterialCardView) findViewById(R.id.transfer101);
        transfer101.setOnClickListener(this);

        MaterialCardView income_details101 = (MaterialCardView) findViewById(R.id.income_details101);
        income_details101.setOnClickListener(this);

        MaterialCardView wallet101 = (MaterialCardView) findViewById(R.id.wallet101);
        wallet101.setOnClickListener(this);

        MaterialCardView services101 = (MaterialCardView) findViewById(R.id.services101);
        services101.setOnClickListener(this);

        MaterialCardView exchange101 = (MaterialCardView) findViewById(R.id.exchange101);
        exchange101.setOnClickListener(this);

        MaterialCardView crypto101 = (MaterialCardView) findViewById(R.id.crypto101);
        crypto101.setOnClickListener(this);


        setToolBar();


        findViewById(R.id.mytopupcard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.btn_view_all_home_two).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                findViewById(R.id.screen_16).setVisibility(View.VISIBLE);
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
    public void maketoast02(View view) {
        drawer.closeDrawer(GravityCompat.START);

        findViewById(R.id.bottom_navigation_home_two).setVisibility(View.INVISIBLE);
        findViewById(R.id.show_ple).setVisibility(View.VISIBLE);
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
    public void moveMyWallet(MenuItem item){
        Intent intent = new Intent(this, HomeTwo.class);
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
    public void chooseAnotherAccount(MenuItem item) {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
    public void close_screen18(View view) {
        Log.e("yes","pressed");
        findViewById(R.id.screen_16).setVisibility(View.INVISIBLE);

    }
    public void openNotifications52(MenuItem item) {
        Intent intent = new Intent(this, Notifications52.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.card1_home_two: i = new Intent(this,Top_up.class);startActivity(i);
            break;
            case R.id.income_details101: i = new Intent(this, IncomeDetails.class);startActivity(i);
                break;
            case R.id.wallet101: i = new Intent(this, Home.class);startActivity(i);
                break;
            case R.id.services101: i = new Intent(this, services.class);startActivity(i);
                break;
            case R.id.exchange101: i = new Intent(this, Home.class);startActivity(i);
                break;
            case R.id.crypto101: i = new Intent(this, CryptoBalance.class);startActivity(i);
                break;
            case R.id.transfer101: i = new Intent(this, MainTransfer36.class);startActivity(i);
            break;
            case R.id.transfer_money_button_hm2: i = new Intent(this,MainTransfer36.class);startActivity(i);
                break;
            default:break;
        }

    }
    public ArrayList<Model> getMylist() {
        ArrayList<Model>models = new ArrayList<>();
        Model m = new Model();
        m.setTitle("Pay Tv");
        m.setImage(R.mipmap.group_18a);
        models.add(m);

        Model m3 = new Model();
        m3.setTitle("Saved Billers");
        m3.setImage(R.mipmap.group_18);
        models.add(m3);

        Model m1 = new Model();
        m1.setTitle("Electricity");
        m1.setImage(R.mipmap.group_18c);
        models.add(m);

        Model m2 = new Model();
        m2.setTitle("Pay Internet");
        m2.setImage(R.mipmap.group_18d);
        models.add(m2);

        Model m4 = new Model();
        m4.setTitle("Buy Airtime");
        m4.setImage(R.mipmap.group_18d);
        models.add(m4);

        return models;
    }

}