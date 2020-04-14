package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Response;

public class TransferToPhone50 extends AppCompatActivity {
    private Button button;
    private EditText text;
    private String phoneNumber = "phone1";
    private String sessionID;
    private String phoneName;
    private String classType;
    private String amount;
    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_phone50);
        setToolBar();

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        if(className.equals("MainTransfer36")) {
            this.sessionID = intentExtra.getStringExtra(MainTransfer36.EXTRA_SESSION);
        }else if (className.equals("SelectUserAdapter")){
            String CheckphoneNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
            this.sessionID = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_SESSION);
            this.phoneName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);
//            Log.e("phone number", phoneNumber);
            Toast.makeText(getApplicationContext(), "Check phone Number test", Toast.LENGTH_LONG).show();
            EditText tvtext =  findViewById(R.id.transContactAmount);
            tvtext.setText(CheckphoneNumber);
        }

//        Intent moveIN = getIntent();
//        this.phoneNumber = moveIN.getStringExtra(ConfirmTransferToPhone52.EXTRA_PHONENUMBER);
//        this.sessionID = moveIN.getStringExtra(ConfirmTransferToPhone52.EXTRA_SESSION);
//        this.phoneName = moveIN.getStringExtra(ConfirmTransferToPhone52.EXTRA_PHONENAME);
//        this.amount = moveIN.getStringExtra(ConfirmTransferToPhone52.EXTRA_AMOUNT);
//
//        EditText ttext =  findViewById(R.id.transAmountPhone);
//        ttext.setText(amount);


    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this,MainTransfer36.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );
    }

//    public void movetoConfirm() {
//        Intent move = new Intent(this, ConfirmTransferToPhone52.class);
//        startActivity(move);
//    }

    public void moveToContact(View view) {
//        Log.e("session before contact", sessionID);
        Intent move = new Intent(this, ContactsView.class);
        move.putExtra("Class","TransferToPhone50");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_CLASSTYPE, "phone");
        startActivity(move);
    }

    public void transferMoney(View view) {
        text = findViewById(R.id.transContactAmount);
        String phone = text.getText().toString();

        text = findViewById(R.id.transAmountPhone);
        String amount = text.getText().toString();

        Log.e("TAG phone number check", "button pressed to transfer money");
        String phonenumber = changePhoneNo(phone, view);
        Log.e("session after contact", sessionID);
        Log.e("TAG phone number last", phonenumber);
        if(phonenumber!="Fasle"){
            valuesConferm(phonenumber, amount);
        }
    }

    public void valuesConferm(String phone, String amount){
        Log.e("session before contact", sessionID);
        Intent move = new Intent(this, ConfirmTransferToPhone52.class);
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_PHONENUMBER, phone);
        move.putExtra(EXTRA_AMOUNT, amount);
        startActivity(move);
    }

    public String changePhoneNo(String inputPhone, View view){
        String validPhoneNo = "Fasle";
        String pattern = "^(?:254 |\\+254|0)?(7(?:(?:[12][0-9])|(?:0[0-8])|(?:9[0-2]))[0-9]{6})$";
        Pattern patt = Pattern.compile(pattern);
        Matcher match;
        if (!inputPhone.isEmpty()) {
            String replPhone1 = inputPhone.trim();
            String replPhone2 = replPhone1.replaceAll("\\s", "");
            match = patt.matcher(replPhone2);
            if (match.find()) {
                Toast.makeText(getApplicationContext(), "MATCH", Toast.LENGTH_LONG).show();
                String replPhone3 = "null";
                if(replPhone2.startsWith("0")){
                    replPhone3 = replPhone2.replaceFirst("0", "\\254");
                    Log.e("TAG phone starts 0", replPhone3);
                    validPhoneNo = replPhone3;
                }else if(replPhone2.startsWith("7")){
                    replPhone3 = replPhone2.replaceFirst("7", "\\254");
                    Log.e("TAG phone starts 7", replPhone3);
                    validPhoneNo = replPhone3;
                }else if(replPhone2.startsWith("+")){
                    validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]","");
                    Log.e("TAG phone number +", validPhoneNo);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please enter a valid mobile number 'Safaricom only'", Toast.LENGTH_LONG).show();
                Log.e("TAG phone No not check", replPhone2);
                moveToContact(view);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a mobile number ", Toast.LENGTH_LONG).show();
            moveToContact(view);
        }

        return validPhoneNo;
    }


}