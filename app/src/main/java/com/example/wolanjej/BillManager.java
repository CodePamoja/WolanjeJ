package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

public class BillManager extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button button1, button2;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_manager);

        button1 = findViewById(R.id.btnPayElectric);
        button2 = findViewById(R.id.btnCancelBill);




        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Unpaid"));
        tabLayout.addTab(tabLayout.newTab().setText("Paid"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final BillManagerAdapter adapter = new BillManagerAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
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
        setToolBar();

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");

        final Intent moveToLogo = new Intent(this, Home.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }

    public void payContinue(View v) {
        switch (v.getId()) {
            case R.id.btn_continue_pay:
                final Intent moveToPin = new Intent(this, EnterPinPay.class);
                startActivity(moveToPin);
                break;
        }
    }

    public void payNowElectric(View v) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                BillManager.this, R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.pay_bill_now, (LinearLayout) findViewById(R.id.payBillNow)
                );
        bottomSheetView.findViewById(R.id.closeBillNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetView.findViewById(R.id.btn_continue_pay).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        TODO:
                    }
                }
        );
        bottomSheetView.findViewById(R.id.btnCancelBill).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        TODO: intent
                    }
                }
        );
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }

    public void close_pay_now(View view) {
        findViewById(R.id.show_pay_now).setVisibility(View.INVISIBLE);

        findViewById(R.id.floatBill).setVisibility(View.VISIBLE);

    }
}