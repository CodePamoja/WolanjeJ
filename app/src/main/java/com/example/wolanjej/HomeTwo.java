package com.example.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.wolanjej.models.Model;
import com.example.wolanjej.recyclerAdapters.MyAdapter;
import com.example.wolanjej.recyclerAdapters.RecyclerViewHomeAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class HomeTwo extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {


    private DrawerLayout drawer;
    private Toolbar toolbar;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);

        toolbar = findViewById(R.id.toolbarhome2);
        drawer = findViewById(R.id.drawer_layout_home_two);

        MaterialCardView materialCardView = findViewById(R.id.card1_home_two);
        materialCardView.setOnClickListener((View.OnClickListener) this);
        


        //     this  belongs to  screen 18
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        MyAdapter myAdapter = new MyAdapter(this, getMylist());
        mRecyclerView.setAdapter(myAdapter);


        Button viewall = (Button) findViewById(R.id.btn_view_all_home_two);
        viewall.setOnClickListener((View.OnClickListener) this);

        /*Button transferMoney = (Button) findViewById(R.id.transfer_money_button_hm2);
        transferMoney.setOnClickListener((View.OnClickListener) this);
*/
        MaterialCardView transfer101 = (MaterialCardView) findViewById(R.id.transfer101);
        transfer101.setOnClickListener((View.OnClickListener) this);

        MaterialCardView income_details101 = (MaterialCardView) findViewById(R.id.income_details101);
        income_details101.setOnClickListener((View.OnClickListener) this);

        MaterialCardView wallet101 = (MaterialCardView) findViewById(R.id.wallet101);
        wallet101.setOnClickListener((View.OnClickListener) this);

        MaterialCardView services101 = (MaterialCardView) findViewById(R.id.services101);
        services101.setOnClickListener((View.OnClickListener) this);

        MaterialCardView exchange101 = (MaterialCardView) findViewById(R.id.exchange101);
        exchange101.setOnClickListener((View.OnClickListener) this);

        MaterialCardView crypto101 = (MaterialCardView) findViewById(R.id.crypto101);
        crypto101.setOnClickListener((View.OnClickListener) this);


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



        findViewById(R.id.loanactivator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                findViewById(R.id.bottom_navigation_home_two).setVisibility(View.INVISIBLE);

                findViewById(R.id.loansholder).setVisibility(View.VISIBLE);
            }
        });
        ViewPager2 vp2 = findViewById(R.id.viewpager2);
        vp2.setAdapter(new LoansAdapter(this));
        TabLayout tb = findViewById(R.id.tabs);
        TabLayoutMediator tbmed = new TabLayoutMediator(tb, vp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("History");
                        break;

                    case 1:
                        tab.setText("Paid");
                        break;
                    case 2:
                        tab.setText("Failed");
                        break;
                }
            }
        });
        tbmed.attach();


        findViewById(R.id.loanscard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
findViewById(R.id.loansholder).setVisibility(View.VISIBLE);
                findViewById(R.id.bottom_navigation_home_two).setVisibility(View.INVISIBLE);

            }
        });

        transferListDetails();
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
            case R.id.transfer_money_button: i = new Intent(this,MainTransfer36.class);startActivity(i);
                break;
            default:break;
        }

    }
    public ArrayList<Model> getMylist() {
        ArrayList<Model>models = new ArrayList<>();
        Model m = new Model();
        m.setTitle("Pay Tv");
        m.setImage(R.drawable.ic_exchange);
        models.add(m);

        Model m3 = new Model();
        m3.setTitle("Saved Billers");
        m3.setImage(R.mipmap.group_18);
        models.add(m3);

        Model m1 = new Model();
        m1.setTitle("Electricity");
        m1.setImage(R.drawable.ic_services);
        models.add(m);

        Model m2 = new Model();
        m2.setTitle("Pay Internet");
        m2.setImage(R.drawable.ic_wallet);
        models.add(m2);

        Model m4 = new Model();
        m4.setTitle("Buy Airtime");
        m4.setImage(R.drawable.ic_wallet);
        models.add(m4);

        return models;
    }


    public void backtohomevisibility(View view) {
        Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
        //findViewById(R.id.loansholder).setVisibility(View.INVISIBLE);
    }

    public void displayPopUp(View view) {
        PopupMenu popup = new PopupMenu(this,view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.pop_up_menu_for_loan_reason);
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.others){
findViewById(R.id.specific).setVisibility(View.VISIBLE);
return true;
        }else{
return true;
        }
    }

    public void openloanspop(View view) {
        findViewById(R.id.loansholder).setVisibility(View.INVISIBLE);
findViewById(R.id.Loansbox).setVisibility(View.VISIBLE);
    }

    public void movotopin(View view) {
        findViewById(R.id.Loansbox).setVisibility(View.INVISIBLE);
        findViewById(R.id.loans2).setVisibility(View.VISIBLE);


    }

    public void close_poup_loans(View view) {
        findViewById(R.id.Loansbox).setVisibility(View.INVISIBLE);
        findViewById(R.id.bottom_navigation_home_two).setVisibility(View.VISIBLE);

    }

    private void transferListDetails(){

        //        mImage and mNames ArrayList go here
//        mImageUrls.add("https://pixabay.com/photos/tree-sunset-amazing-beautiful-736885/");
//        mNames.add("John");

        initTransferRecyclerList();
    }
    private void initTransferRecyclerList(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeTwo.this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewHomeTwo);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewHomeAdapter adapter = new RecyclerViewHomeAdapter( mNames, mImageUrls, HomeTwo.this);
        recyclerView.setAdapter(adapter);

    }
}