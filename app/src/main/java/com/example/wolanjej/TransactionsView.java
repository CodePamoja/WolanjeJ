//package com.example.wolanjej;
//
//import android.annotation.TargetApi;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.AsyncTask;
//import android.os.Build;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.SearchView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.viewpager.widget.ViewPager;
//
//import com.google.android.material.tabs.TabLayout;
//
//import java.util.List;
//
//public class TransactionsView extends AppCompatActivity {
//
//    TabLayout tabLayout;
//    RecyclerView recyclerView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_transactions_view);
//
//        setToolBar();
//
//        tabLayout=(TabLayout)findViewById(R.id.tabLayoutTrans);
//        recyclerView=(RecyclerView)findViewById(R.id.transaction_list);
//
//        tabLayout.addTab(tabLayout.newTab().setText("Sent"));
//        tabLayout.addTab(tabLayout.newTab().setText("Received"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final TransactionMangerAdapter adapter = new TransactionMangerAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
//        recyclerView.setAdapter(adapter);
//
//        recyclerView.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                recyclerView.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//    }
//
//    private void setToolBar() {
//        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(tb);
//        getSupportActionBar().setTitle("");
//
//        final Intent moveToLogo = new Intent(this,Home.class);
//        tb.setNavigationOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(moveToLogo);
//                    }
//                }
//        );
//
//    }
//
//    public void payContinue(View v) {
//        switch(v.getId()) {
//            case R.id.btn_continue_pay:
//                final Intent moveToPin = new Intent(this,EnterPinPay.class);
//                startActivity(moveToPin);
//                break;
//        }
//    }
//
//    public void payNowElectric(View v) {
//        switch(v.getId()) {
//            case R.id.btnPayElectric:
//                findViewById(R.id.show_pay_now).setVisibility(View.VISIBLE);
//                findViewById(R.id.floatBill).setVisibility(View.INVISIBLE);
//                break;
//        }
//    }
//    public void close_pay_now(View view) {
//        findViewById(R.id.show_pay_now).setVisibility(View.INVISIBLE);
//
//        findViewById(R.id.floatBill).setVisibility(View.VISIBLE);
//
//    }
//
//}
