package com.example.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeTwo extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

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


    public void backtohomevisibility(View view) {
        findViewById(R.id.loansholder).setVisibility(View.INVISIBLE);
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
}