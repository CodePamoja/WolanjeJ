package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolanjej.pagerAdapters.BillManagerAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import okhttp3.Response;

public class BillManager extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button button1, button2;
    private TextView textView, textView1, textView2, textView3, textView4;
    private JSONObject jsonObject;
    private String sessionID;
    private String AGENTNO;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_manager);
        SetViewPager();
        setToolBar();

        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");

        button1 = findViewById(R.id.btnPayElectric);
        button2 = findViewById(R.id.btnCancelBill);


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

    private void SetViewPager() {
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


    public void billBottomSheet(View view) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                BillManager.this, R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.billmanager28, (LinearLayout) findViewById(R.id.billManagerView)
                );
        bottomSheetView.findViewById(R.id.bmClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetView.findViewById(R.id.bmButtonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewUserBill();
            }
        });

        bottomSheetView.findViewById(R.id.bmCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }
    private void NewUserBill(){
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.billmanager28, (LinearLayout) findViewById(R.id.billManagerView)
                );

        textView1 = bottomSheetView.findViewById(R.id.bmAccountName);
        final String accountName = textView1.getText().toString();

        textView2 = bottomSheetView.findViewById(R.id.bmNickname);
        final String nickName = textView2.getText().toString();

        textView3 = bottomSheetView.findViewById(R.id.bmAccountNo);
        final String accountNumber = textView3.getText().toString();

        textView4 = bottomSheetView.findViewById(R.id.bmPayBillNo);
        final String payBillNo = textView4.getText().toString();

        jsonObject = new JSONObject();

        try {
            jsonObject.put("product_name",nickName);
            jsonObject.put("account_no", accountNumber);
        }catch (JSONException e){
            e.printStackTrace();
        }
        UserBill userBill = new UserBill(this);
        userBill.execute();
    }

    public static class UserBill extends AsyncTask<Void, Void, Response> {

        private WeakReference<BillManager> weakReference;
        public UserBill(BillManager billManager) {
            weakReference = new WeakReference<>(billManager);
        }

        @Override
        protected Response doInBackground(Void... voids) {
            BillManager billManager = weakReference.get();
            String url = "/bills";
            OkhttpConnection okConn = new OkhttpConnection();
            Response result = null;
            result = okConn.setProfileDetails(url, billManager.jsonObject.toString(),billManager. sessionID);
            return result;
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            System.out.println("BillManager RESPONSE !!" + response);
        }
    }
}