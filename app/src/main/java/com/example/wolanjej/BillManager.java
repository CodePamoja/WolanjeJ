package com.example.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.example.wolanjej.RetrofitUtils.RetrofitClient;
import com.example.wolanjej.models.NewBillmodel;
import com.example.wolanjej.pagerAdapters.BillManagerAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

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

    }

    private void SetViewPager() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

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
                        Intent moveToPin = new Intent(getApplicationContext(), EnterPinPay.class);
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
                    String result = response.body().toString();
                    Log.d("TAG test CODE", result);
                    return;
                }
                Toast.makeText(getApplicationContext(), "Successfully Created A Bill" , Toast.LENGTH_LONG).show();
                String result = response.body().toString();
                Log.d("TAG test SUCCESS", result);
            }

            @Override
            public void onFailure(Call<NewBillmodel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

}