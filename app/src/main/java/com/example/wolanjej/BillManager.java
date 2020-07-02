package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolanjej.pagerAdapters.BillManagerAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Response;

public class BillManager extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView textView, textView1, textView2, textView3, textView4;
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



    }

    private void setToolBar() {
        Toolbar tb = findViewById(R.id.toolbar);
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
        tabLayout =  findViewById(R.id.tabLayout);
        viewPager =  findViewById(R.id.viewPager);

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
        String accountName = textView1.getText().toString();

        textView2 = bottomSheetView.findViewById(R.id.bmNickname);
        String nickName = textView2.getText().toString();

        textView3 = bottomSheetView.findViewById(R.id.bmAccountNo);
        String accountNumber = textView3.getText().toString();

        textView4 = bottomSheetView.findViewById(R.id.bmPayBillNo);
        String payBillNo = textView4.getText().toString();


        //My class
        new AddNewBill(accountName,nickName,accountNumber,payBillNo,sessionID);

    }

    @SuppressLint("StaticFieldLeak")
    public class AddNewBill extends AsyncTask<Void, Void, Response>{


        String Account_Name,Add_Nickname,Enter_Account_No,Enter_Paybill_No,id;
        JSONObject savebill  = new JSONObject();

        public AddNewBill(String account_Name, String add_Nickname, String enter_Account_No, String enter_Paybill_No, String id) {
            Account_Name = account_Name;
            Add_Nickname = add_Nickname;
            Enter_Account_No = enter_Account_No;
            Enter_Paybill_No = enter_Paybill_No;
            this.id = id;
        }

        @Override
        protected void onPreExecute() {

            try {
                savebill.put("",Account_Name);
                savebill.put("", Add_Nickname);
                savebill.put("",Enter_Account_No);
                savebill.put("", Enter_Paybill_No);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response result;
            String url = "/bills";
            OkhttpConnection okConn = new OkhttpConnection();
            result = okConn.setProfileDetails(url, savebill.toString(),id);
            return result;        }
        @Override
        protected void onPostExecute(Response response) {
   try {
                new MaterialAlertDialogBuilder(getApplicationContext())
                        .setTitle(Account_Name)
                        .setMessage(Objects.requireNonNull(response.body()).string())
                        .show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("BillManager RESPONSE !!" + response);
        }
    }
}