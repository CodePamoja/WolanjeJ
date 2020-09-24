package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.RetrofitClient;
import com.wolanjeAfrica.wolanjej.models.NewBillmodel;
import com.wolanjeAfrica.wolanjej.pagerAdapters.BillManagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BillManager extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView textView, textView1, textView2, textView3, textView4;
    private String sessionID;
    private String AGENTNO;
    private SharedPreferences pref;
    private ArrayAdapter adapter;
    private NewBillmodel newBillmodel;
    private Spinner spinner;
    Toolbar tb;
    private BottomSheetDialog bottomSheetDialogNeWBill;
    private View bottomSheetViewNewBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_manager);

        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");
        newBillmodel = new NewBillmodel();
        SetViewPager();
        setToolBar(tb);

    }


    private void setToolBar(Toolbar tb) {
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        final Intent moveToLogo = new Intent(this, Home.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }

    private void SetViewPager() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Unpaid"));
        tabLayout.addTab(tabLayout.newTab().setText("Paid"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.warm_purple));
        tabLayout.setTabTextColors(R.color.hard_gray,R.color.colorMidnight);

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
                        Intent moveToPin = new Intent(getApplicationContext(), EnterPin.class);
                        moveToPin.putExtra("Class", "BillManager");
                        startActivity(moveToPin);
                        bottomSheetDialog.dismiss();
                    }
                }
        );
        bottomSheetView.findViewById(R.id.btnCancelBill).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                }
        );
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }


    public void billBottomSheet(View view) {

        bottomSheetDialogNeWBill = new BottomSheetDialog(
                BillManager.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetViewNewBill = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.billmanager28, (ConstraintLayout) findViewById(R.id.billManagerView)
                );
        textView1 = bottomSheetViewNewBill.findViewById(R.id.bmAccountSaveBillName);
        textView2 = bottomSheetViewNewBill.findViewById(R.id.bmAccountNickName);
        textView3 = bottomSheetViewNewBill.findViewById(R.id.bmAccountNo);
        spinner = bottomSheetViewNewBill.findViewById(R.id.spinner_bill);

        adapter = ArrayAdapter.createFromResource(this,    // setting array-adapter belonging to spinner
                R.array.bill_products, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        bottomSheetViewNewBill.findViewById(R.id.bmClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialogNeWBill.dismiss();
            }
        });

        bottomSheetViewNewBill.findViewById(R.id.bmButtonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createANewBill();
            }
        });

        bottomSheetViewNewBill.findViewById(R.id.bmCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialogNeWBill.dismiss();
            }
        });
        bottomSheetDialogNeWBill.setContentView(bottomSheetViewNewBill);
        bottomSheetDialogNeWBill.show();

    }

    public void createANewBill() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + sessionID + "");

        String AccountName = textView1.getText().toString();
        String NickName = textView2.getText().toString();
        String account_number = textView3.getText().toString();
        if(account_number.isEmpty()){
            Toast.makeText(this, "account number is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        newBillmodel.setAccount_no(account_number);
        String product = spinner.getSelectedItem().toString();
        newBillmodel.setProduct_name(product);
        Retrofit retrofit = RetrofitClient.getInstance();
        JsonPlaceHolders jsonPlaceHolders = retrofit.create(JsonPlaceHolders.class);
        Call<NewBillmodel> call = jsonPlaceHolders.createNewBill(newBillmodel, headers);

        call.enqueue(new Callback<NewBillmodel>() {
            @Override
            public void onResponse(Call<NewBillmodel> call, @NotNull Response<NewBillmodel> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "code" + response.code(), Toast.LENGTH_LONG).show();
                    if (response.body() != null) {
                        String result = response.body().toString();
                        Log.d("TAG test CODE", result);
                    }else {
                        Toast.makeText(BillManager.this, "Sorry something went wrong", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                if (response.body() != null) {
                    Toast.makeText(getApplicationContext(), "Successfully Created A Bill", Toast.LENGTH_LONG).show();
                    String result = response.body().toString();
                    Log.d("TAG test SUCCESS", result);
                }else {
                    Toast.makeText(BillManager.this, "Sorry something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewBillmodel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

}