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
    private String AGENTNO;
    private String phoneCompany;
    private String amount;

    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    public static final String EXTRA_PROVIDER = "com.example.wolanjej.PROVIDER";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";

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
            this.AGENTNO = intentExtra.getStringExtra(MainTransfer36.EXTRA_AGENTNO);
        }else if(className.equals("ContactsView")) {
            this.sessionID = intentExtra.getStringExtra(ContactsView.EXTRA_SESSION);
            this.AGENTNO = intentExtra.getStringExtra(ContactsView.EXTRA_AGENTNO);
        }else if (className.equals("SelectUserAdapter")){
            String CheckphoneNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
            this.sessionID = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_SESSION);
            this.phoneName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);
            this.AGENTNO = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_AGENTNO);

            EditText tvtext =  findViewById(R.id.transContactAmount);
            tvtext.setText(CheckphoneNumber);
        }else if (className.equals("ConfirmTransferToPhone52")){
            String CheckphoneNumber = "+"+intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_PHONENUMBER);
            this.sessionID = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_SESSION);
            this.AGENTNO = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_AGENTNO);
            this.phoneName = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_PHONENAME);
            String sendAmount = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_AMOUNT);

            EditText tvtext =  findViewById(R.id.transContactAmount);
            tvtext.setText(CheckphoneNumber);

            tvtext = findViewById(R.id.transAmountPhone);
            tvtext.setText(sendAmount);

        }

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
                        Log.e("set toolbar sess", sessionID);
                        movetoLogo.putExtra(EXTRA_SESSION, sessionID);
                        movetoLogo.putExtra(EXTRA_AGENTNO, AGENTNO);
                        movetoLogo.putExtra("Class","TransferToPhone50");
                        startActivity(movetoLogo);
                    }
                }
        );
    }


    public void moveToContact(View view) {
//        Log.e("session before contact", sessionID);
        Intent move = new Intent(this, ContactsView.class);
        move.putExtra("Class","TransferToPhone50");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
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
        move.putExtra(EXTRA_PROVIDER, phoneCompany);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        move.putExtra(EXTRA_PHONENUMBER, phone);
        move.putExtra(EXTRA_AMOUNT, amount);
        startActivity(move);
    }

    public String changePhoneNo(String inputPhone, View view){
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
                Toast.makeText(getApplicationContext(), "Safaricom Number", Toast.LENGTH_LONG).show();
                String replPhone3 = "null";
                phoneCompany = "safaricom";
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
                patt = Pattern.compile(airtel);
                match = patt.matcher(replPhone2);
                if (match.find()) {
                    Toast.makeText(getApplicationContext(), "Airtel Number", Toast.LENGTH_LONG).show();
                    String replPhone3 = "null";
                    phoneCompany = "airtel";
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
                }else {
                    patt = Pattern.compile(telkom);
                    match = patt.matcher(replPhone2);
                    if (match.find()) {
                        Toast.makeText(getApplicationContext(), "Telkom Number", Toast.LENGTH_LONG).show();
                        String replPhone3 = "null";
                        phoneCompany = "telkom";
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
                    }else {
                        Toast.makeText(getApplicationContext(), "Please enter a valid mobile number 'Safaricom only'", Toast.LENGTH_LONG).show();
                        Log.e("TAG phone No not check", replPhone2);
                        moveToContact(view);
                    }
                }

            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a mobile number ", Toast.LENGTH_LONG).show();
            moveToContact(view);
        }

        return validPhoneNo;
    }

}