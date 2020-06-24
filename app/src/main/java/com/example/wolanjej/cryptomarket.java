package com.example.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.wolanjej.pagerAdapters.CryptomarketAdapter;
import com.google.android.material.tabs.TabLayout;

public class cryptomarket extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryptomarket);
        setToolBar();

        tabLayout = findViewById(R.id.crytotabLayout);
        viewPager = findViewById(R.id.cryptoviewPager);

        tabLayout.addTab(tabLayout.newTab().setText("crypto Market"));
        tabLayout.addTab(tabLayout.newTab().setText("Live Rate"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final CryptomarketAdapter adapter = new CryptomarketAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");

        final Intent moveToLogo = new Intent(this,CryptoBalance.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }
}