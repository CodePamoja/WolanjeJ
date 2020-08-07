package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wolanjej.pagerAdapters.BookABusAdapter;
import com.example.wolanjej.pagerAdapters.Qr_codeAdapter;
import com.google.android.material.tabs.TabLayout;

public class Qr_code22 extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code22);

        tabLayout = findViewById(R.id.tabLayout_qr_code);
        viewPager = findViewById(R.id.bookQrViewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Code"));
        tabLayout.addTab(tabLayout.newTab().setText("Scan"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        setViewPager();
        setToolBar(tb);
    }
    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbar_qr_code);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,TopupOtherNumber.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveToLogo.putExtra("Class", "Qr_code22");
                        startActivity(moveToLogo);
                    }
                }
        );

    }
    private void setViewPager(){
        final Qr_codeAdapter adapter = new Qr_codeAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
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
}