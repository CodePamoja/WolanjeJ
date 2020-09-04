package com.wolanjeAfrica.wolanjej;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.wolanjeAfrica.wolanjej.recyclerAdapters.SelectUserAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransferToWalletSingle37 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] selectUser = {"Single Transfers", "Multiple Transfers"};
    public static final String EXTRA_MESSAGE = "com.example.wolanjej.MESSAGE";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_PROVIDER = "com.example.wolanjej.PROVIDER";
    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    private Button button;
    private Spinner spin;
    private EditText text;
    private String phoneNumber;
    private String sessionID;
    private String phoneName;
    private String AGENTNO;
    private String phoneCompany;
    private String amount;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_wallet_single37);
        setToolBar();
        pref = getApplication().getSharedPreferences("LogIn", Context.MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");


        spin = (Spinner) this.findViewById(R.id.select_user);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, selectUser);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        fetchClassIntent();
        setActionBarColor();

    }

    private void setActionBarColor() {
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.bShadeGray));
    }

    private void fetchClassIntent() {

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        switch (className) {
            case "EnterPin":
                break;
            case "MainTransfer36":
                break;
            case "TransferToWalletMultiple40":
                this.sessionID = intentExtra.getStringExtra(TransferToWalletMultiple40.EXTRA_SESSION);
                this.AGENTNO = intentExtra.getStringExtra(TransferToWalletMultiple40.EXTRA_AGENTNO);
                break;
            case "SelectUserAdapter": {
                this.phoneNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
                String userName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);
                this.phoneName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);

                EditText tvtext = findViewById(R.id.walluserName);
                tvtext.setText(userName);
                break;
            }
            case "ConfirmSingleTransfer40": {
                this.phoneNumber = "+" + intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENUMBER);
                String userName = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENAME);
                this.sessionID = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_SESSION);
                this.AGENTNO = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_AGENTNO);
                this.phoneName = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENAME);
                String sendAmount = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_AMOUNT);
                String sendMessage = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_MESSAGE);

                EditText tvtext = findViewById(R.id.walluserName);
                tvtext.setText(userName);

                tvtext = findViewById(R.id.walltAmount);
                tvtext.setText(sendAmount);

                tvtext = findViewById(R.id.walltMessage);
                tvtext.setText(sendMessage);
                break;
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Log.e("session before contact", sessionID);
        Toast.makeText(getApplicationContext(), selectUser[position], Toast.LENGTH_LONG).show();
        if ("Multiple Transfers".equals(selectUser[position])) {
            Intent move = new Intent(this, TransferToWalletMultiple40.class);
            move.putExtra("Class", "TransferToWalletSingle37");
            move.putExtra(EXTRA_SESSION, sessionID);
            move.putExtra(EXTRA_AGENTNO, AGENTNO);
            startActivity(move);
            finish();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, MainTransfer36.class);
        tb.setNavigationOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Log.e("set toolbar sess", sessionID);
                        movetoLogo.putExtra("Class", "TransferToWalletSingle37");
                        movetoLogo.putExtra(EXTRA_SESSION, sessionID);
                        movetoLogo.putExtra(EXTRA_AGENTNO, AGENTNO);
                        startActivity(movetoLogo);
                    }
                }
        );
    }

    public void moveToContact(View view) {
        Log.e("session before contact", sessionID);
        Intent move = new Intent(this, ContactsView.class);
        move.putExtra("Class", "TransferToWalletSingle37");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        move.putExtra(EXTRA_CLASSTYPE, "wallet");
        startActivity(move);
        finish();
    }

    public void movetoSingle() {
        Intent move = new Intent(this, TransferToWalletSingle37.class);
        startActivity(move);
    }

    public void movetoMultiple() {
        Intent move = new Intent(this, TransferToWalletMultiple40.class);
        startActivity(move);
    }

    public void transferMoney(View view) {
        text = findViewById(R.id.walltAmount);
        String amount = text.getText().toString();

        text = findViewById(R.id.walltMessage);
        String message = text.getText().toString();

        if (amount.isEmpty()) {
            text.requestFocus();
            Toast.makeText(this, "please provide all details", Toast.LENGTH_SHORT).show();
            return;
        }


        String phone = phoneNumber;
        Log.e("TAG phone number check", "button pressed to transfer money" + phone);
        if (phone == null) {
            Toast.makeText(this, "please provide recipient", Toast.LENGTH_SHORT).show();
            return;
        }
        String phonenumber = changePhoneNo(phone, view);
        Log.e("session after contact", sessionID);
        Log.e("TAG phone number last", phonenumber);
        if (!phonenumber.equals("Fasle")) {
            valuesConferm(phonenumber, amount, message);
        }
    }

    public void valuesConferm(String phone, String amount, String message) {
        Log.e("session before contact", sessionID);
        Intent move = new Intent(this, ConfirmSingleTransfer40.class);
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_MESSAGE, message);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        move.putExtra(EXTRA_PHONENUMBER, phone);
        move.putExtra(EXTRA_AMOUNT, amount);
        startActivity(move);
    }

    public String changePhoneNo(String inputPhone, View view) {
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