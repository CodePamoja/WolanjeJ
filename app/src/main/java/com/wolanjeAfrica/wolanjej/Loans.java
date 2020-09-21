package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.wolanjeAfrica.wolanjej.pagerAdapters.LoansAdapter;

public class Loans extends AppCompatActivity implements View.OnClickListener {
    private static String ClassType;
    private ArrayAdapter adapter;
    private BottomSheetDialog bottomSheetDialog;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar tb;
    private Spinner spinner;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loans);
        button1 = (Button) findViewById(R.id.applyLoanBtn);
        button1.setOnClickListener(Loans.this);

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        switch (className) {
            case "Home":
            case "HomeTwo":
                ClassType = intentExtra.getStringExtra(Home.EXTRA_CLASS_TYPE);
                break;
            default:
                break;
        }
        createViewPager(); //init viewpager on loans view
        setToolBar(tb);

    }

    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbar);
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

    private void createViewPager() {

        tabLayout = findViewById(R.id.tabLayoutLoans);
        viewPager = findViewById(R.id.loansV_viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("History"));
        tabLayout.addTab(tabLayout.newTab().setText("Paid"));
        tabLayout.addTab(tabLayout.newTab().setText("Failed"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoansAdapter loansAdapter = new LoansAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(loansAdapter);

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

    public void OpenBootomSheetLoan() {
        bottomSheetDialog = new BottomSheetDialog(
                Loans.this, R.style.BottomSheetDialogTheme
        );
        View bottomSheetViewOpenLoan = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.loans12_bottom_sheet, (ConstraintLayout) findViewById(R.id.loansbottomSheets)
                );
        Button button = (Button) bottomSheetViewOpenLoan.findViewById(R.id.buttonReqLoanSheet);
        spinner = bottomSheetViewOpenLoan.findViewById(R.id.useOfFundsSpinner);
        adapter = ArrayAdapter.createFromResource(this,    // setting array-adapter belonging to spinner
                R.array.loanUseOfFunds, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                Intent intent = new Intent(Loans.this, EnterPin.class);
                intent.putExtra("Class", ClassType);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        bottomSheetDialog.setContentView(bottomSheetViewOpenLoan);
        bottomSheetDialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.applyLoanBtn:
                OpenBootomSheetLoan();
                break;
            default:
                break;
        }

    }

}