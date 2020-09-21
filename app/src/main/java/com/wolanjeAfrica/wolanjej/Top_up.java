package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.wolanjeAfrica.wolanjej.Utils.CheckPhoneNumber;
import com.wolanjeAfrica.wolanjej.ViewModels.UserBalanceViewModel;
import com.wolanjeAfrica.wolanjej.models.BalanceModel;

import java.util.List;
import java.util.Map;

public class Top_up extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_PROVIDER = "com.example.wolanjej.PROVIDER";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_PARENTCLASSNAME = "com.example.wolanjej.PARENTCLASSNAME";
    private static final String TAG = "TopUp";
    private String[] selectNumber = {"My Number", "Other Number"};
    private String phoneCompany;
    private TextView textView1;
    private String sessionId;
    private String AGENTNO;
    private Toolbar tb;
    private static String MY_BALANCE;
    private SharedPreferences pref;
    private static String className;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        //SharedPreferences values for login eg token, user registered number
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionId = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");


        textView1 = (TextView) findViewById(R.id.balance_layout_top_up);

        className = getIntent().getStringExtra("Class");

        Spinner spin = (Spinner) this.findViewById(R.id.select_number);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, selectNumber);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        UserBalanceViewModel userBalanceViewModel = new ViewModelProvider(this).get(UserBalanceViewModel.class);
        userBalanceViewModel.getUserBalance(Top_up.this, sessionId).observe(this, new Observer<List<BalanceModel>>() {
            @Override
            public void onChanged(List<BalanceModel> balanceModels) {
                for (BalanceModel b : balanceModels) {
                    MY_BALANCE = b.getBalance();
                    textView1.setText("KES" + MY_BALANCE);
                }
            }
        });

        setToolBar();
    }

    private void setToolBar() {
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, Home.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if ("Other Number".equals(selectNumber[position])) {
            Intent move = new Intent(this, TopupOtherNumber.class);
            move.putExtra("Class", "Top_up");
            startActivity(move);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void moveToBuy(View view) {
        EditText text = findViewById(R.id.amount_top_up);
        String amount = text.getText().toString();
        String key = null;
        String value = null;
        if (amount.isEmpty()) {
            text.requestFocus();
            Toast.makeText(getApplicationContext(), "please provide Amount", Toast.LENGTH_LONG).show();
            return;
        }
        int x = Integer.parseInt(amount);
        if (amount != null) {
            if (x >= 10) {
                if (x <= 70000) {
                    Map<String, String> map = CheckPhoneNumber.getInstance().checkPhoneNo(Top_up.this, "+" + AGENTNO);
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        key = entry.getKey();
                        value = entry.getValue();
                    }
                    if (value != null && !value.equals("Fasle")) {
                        movetoPin(value, amount, key);
                    } else {
                        Toast.makeText(this, "invalid phone", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "The Amount is above 70000", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "The Amount is below 10", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Enter Amount", Toast.LENGTH_LONG).show();
        }

    }

    public void movetoPin(String phone, String amount, String provider) {
        Intent move = new Intent(this, EnterPin.class);
        move.putExtra("Class", "Top_up");
        move.putExtra(EXTRA_PROVIDER, provider);
        move.putExtra(EXTRA_PHONENUMBER, phone);
        move.putExtra(EXTRA_AMOUNT, amount);
        move.putExtra(EXTRA_PARENTCLASSNAME, className);
        startActivity(move);
    }

    public void addAmount1(View view) {

        TextView tvAdd = findViewById(R.id.amount_display01);
        String value = (String) tvAdd.getText();

        EditText tvtext = findViewById(R.id.amount_top_up);
        tvtext.setText(value);
    }

    public void addAmount2(View view) {
        TextView tvAdd = findViewById(R.id.amount_display02);
        String value = (String) tvAdd.getText();

        EditText tvtext = findViewById(R.id.amount_top_up);
        tvtext.setText(value);
    }

    public void addAmount3(View view) {
        TextView tvAdd = findViewById(R.id.amount_display03);
        String value = (String) tvAdd.getText();

        EditText tvtext = findViewById(R.id.amount_top_up);
        tvtext.setText(value);
    }

    public void addAmount4(View view) {
        TextView tvAdd = findViewById(R.id.amount_display04);
        String value = (String) tvAdd.getText();

        EditText tvtext = findViewById(R.id.amount_top_up);
        tvtext.setText(value);
    }

}
