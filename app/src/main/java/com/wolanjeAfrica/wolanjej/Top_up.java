package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.wolanjeAfrica.wolanjej.Utils.CheckPhoneNumber;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Top_up extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_PROVIDER = "com.example.wolanjej.PROVIDER";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    private static final String TAG = "TopUp";
    private String[] selectNumber = {"My Number", "Other Number"};
    private String phoneCompany;
    private String AGENTNO;
    private Toolbar tb;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        //SharedPreferences values for login eg token, user registered number
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        // this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");

        Spinner spin = (Spinner) this.findViewById(R.id.select_number);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, selectNumber);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

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
                    }else {
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
