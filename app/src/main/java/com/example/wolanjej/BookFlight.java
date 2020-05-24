package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class BookFlight extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_flight);

        tabLayout = findViewById(R.id.tabLayout_book_flight);
        viewPager = findViewById(R.id.bookFlightViewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Book"));
        tabLayout.addTab(tabLayout.newTab().setText("My Bookings"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final BookFlightAdapter adapter = new BookFlightAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
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
    public void MoveToBookFlight02(View view) {
        startActivity(new Intent(this,BookFlight02.class));
    }

}