package com.wolanjeAfrica.wolanjej;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wolanjeAfrica.wolanjej.pagerAdapters.Learning_HubAdapter;
import com.wolanjeAfrica.wolanjej.pagerAdapters.TransactionViewAdapter;

public class Learning_Hub2 extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private ImageButton imageButton;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning__hub2);
        CreateViewPager();
        linearLayout = (LinearLayout) findViewById(R.id.learningHub1);
        imageButton = (ImageButton)findViewById(R.id.btntoolbarlearninghub1);imageButton.setOnClickListener(this);
    }

    private void CreateViewPager() {

        TabLayout tabLayout = findViewById(R.id.tabLayoutLearningHub);
        viewPager = findViewById(R.id.learning_viewPager);


        tabLayout.addTab(tabLayout.newTab().setText("Featured"));
        tabLayout.addTab(tabLayout.newTab().setText("Innovation"));
        tabLayout.addTab(tabLayout.newTab().setText("Finance"));
        tabLayout.addTab(tabLayout.newTab().setText("Category"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorWhite));

        final Learning_HubAdapter adapter = new Learning_HubAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btntoolbarlearninghub1:
                linearLayout.setVisibility(View.GONE);
                break;
        }
    }
}