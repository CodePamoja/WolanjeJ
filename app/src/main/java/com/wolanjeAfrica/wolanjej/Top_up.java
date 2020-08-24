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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Top_up extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] selectNumber = {"My Number", "Other Number"};
    private String phoneCompany;
    private String AGENTNO;
    private static  final String TAG = "TopUp";
    Toolbar tb;

    private SharedPreferences pref;

    public static final String EXTRA_PROVIDER = "com.example.wolanjej.PROVIDER";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        //SharedPreferences values for login eg token, user registered number
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
//        this.sessionID = pref.getString("session_token", "");
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
        if (amount.isEmpty()) {
            text.requestFocus();
            Toast.makeText(getApplicationContext(), "please provide Amount", Toast.LENGTH_LONG).show();
            return;
        }
        int x = Integer.parseInt(amount);
        if (amount != null) {
            if (x >= 10) {
                if (x <= 70000) {
                    String phoneNumber = checkPhoneNo("+" + AGENTNO);
                    if (phoneNumber != "Fasle") {
                        movetoPin(AGENTNO, amount, phoneCompany);
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

    public String checkPhoneNo(String inputPhone) {
        String validPhoneNo = "Fasle";
        String safaricom = "^(?:254|\\+254|0)?(7(?:(?:[129][0-9])|(?:0[0-9])|(?:6[8-9])|(?:5[7-9])|(?:4[5-6])|(?:4[8])|(4[0-3]))[0-9]{6})$";
        String telkom = "^(?:254|\\+254|0)?(7(?:(?:[7][0-9]))[0-9]{6})$";
        String airtel = "^(?:254|\\+254|0)?(7(?:(?:[3][0-9])|(?:5[0-6])|(?:6[2])|(8[0-9]))[0-9]{6})$";
        Pattern patt;
        Matcher match;
        if (!inputPhone.isEmpty()) {
            String replPhone1 = inputPhone.trim();
            String replPhone2 = replPhone1.replaceAll("\\s", "");
            patt = Pattern.compile(safaricom);
            match = patt.matcher(replPhone2);
            if (match.find()) {
//                Toast.makeText(getApplicationContext(), "Safaricom Number", Toast.LENGTH_LONG).show();
                Log.d(TAG, "checkPhoneNo:  sfaricom number");
                String replPhone3 = "null";
                phoneCompany = "safaricom";
                if (replPhone2.startsWith("0")) {
                    replPhone3 = replPhone2.replaceFirst("0", "\\254");
                    Log.e("TAG phone starts 0", replPhone3);
                    validPhoneNo = replPhone3;
                } else if (replPhone2.startsWith("7")) {
                    replPhone3 = replPhone2.replaceFirst("7", "\\254");
                    Log.e("TAG phone starts 7", replPhone3);
                    validPhoneNo = replPhone3;
                } else if (replPhone2.startsWith("+")) {
                    validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                    Log.e("TAG phone number +", validPhoneNo);
                }
            } else {
                patt = Pattern.compile(airtel);
                match = patt.matcher(replPhone2);
                if (match.find()) {
                    Toast.makeText(getApplicationContext(), "Airtel Number", Toast.LENGTH_LONG).show();
                    String replPhone3 = "null";
                    phoneCompany = "airtel";
                    if (replPhone2.startsWith("0")) {
                        replPhone3 = replPhone2.replaceFirst("0", "\\254");
                        Log.e("TAG phone starts 0", replPhone3);
                        validPhoneNo = replPhone3;
                    } else if (replPhone2.startsWith("7")) {
                        replPhone3 = replPhone2.replaceFirst("7", "\\254");
                        Log.e("TAG phone starts 7", replPhone3);
                        validPhoneNo = replPhone3;
                    } else if (replPhone2.startsWith("+")) {
                        validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                        Log.e("TAG phone number +", validPhoneNo);
                    }
                } else {
                    patt = Pattern.compile(telkom);
                    match = patt.matcher(replPhone2);
                    if (match.find()) {
                        Toast.makeText(getApplicationContext(), "Telkom Number", Toast.LENGTH_LONG).show();
                        String replPhone3 = "null";
                        phoneCompany = "telkom";
                        if (replPhone2.startsWith("0")) {
                            replPhone3 = replPhone2.replaceFirst("0", "\\254");
                            Log.e("TAG phone starts 0", replPhone3);
                            validPhoneNo = replPhone3;
                        } else if (replPhone2.startsWith("7")) {
                            replPhone3 = replPhone2.replaceFirst("7", "\\254");
                            Log.e("TAG phone starts 7", replPhone3);
                            validPhoneNo = replPhone3;
                        } else if (replPhone2.startsWith("+")) {
                            validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                            Log.e("TAG phone number +", validPhoneNo);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter a valid mobile number 'Safaricom only'", Toast.LENGTH_LONG).show();
                        Log.e("TAG phone No not check", replPhone2);
                    }
                }

            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a mobile number ", Toast.LENGTH_LONG).show();
        }

        return validPhoneNo;
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
