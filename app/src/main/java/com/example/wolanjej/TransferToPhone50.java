package com.example.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.wolanjej.recyclerAdapters.SelectUserAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransferToPhone50 extends AppCompatActivity {
    private Button button;
    private EditText text;
    private String phoneNumber = "phone1";
    private String phoneName;
    private String phoneCompany;
    private SharedPreferences pref;

    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_phone50);
        setToolBar();

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        switch (className) {
            case "MainTransfer36":
                break;
            case "ContactsView":
                break;
            case "SelectUserAdapter": {
                String CheckphoneNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
                this.phoneName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);

                EditText tvtext = findViewById(R.id.transContactAmount);
                tvtext.setText(CheckphoneNumber);
                break;
            }
            case "ConfirmTransferToPhone52": {

                //SharedPreferences values for TransferToPhone52 activity class eg token
                pref = getApplication().getSharedPreferences("ConfirmTransferToPhone52", MODE_PRIVATE);
                this.phoneNumber = pref.getString("phone", "");
                this.phoneName = pref.getString("phoneName", "");
                String sendAmount = pref.getString("amount", "");
                this.phoneCompany = pref.getString("phoneCompany", "");

                String CheckphoneNumber = "+" + pref.getString("phone", "");

                EditText tvtext = findViewById(R.id.transContactAmount);
                tvtext.setText(CheckphoneNumber);

                tvtext = findViewById(R.id.transAmountPhone);
                tvtext.setText(sendAmount);

                break;
            }
        }

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
                        startActivity(movetoLogo);
                    }
                }
        );
    }


    public void moveToContact(View view) {
//        Log.e("session before contact", sessionID);
        Intent move = new Intent(this, ContactsView.class);
        move.putExtra("Class", "TransferToPhone50");
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
        Log.e("TAG phone number last", phonenumber);
        if (phonenumber != "Fasle") {
            valuesConferm(phonenumber, amount);
        }
    }

    public void valuesConferm(String phone, String amount) {
//        Log.e("session before contact", sessionID);

        //SharedPreferences values for login eg token
//        pref = getApplication().getSharedPreferences("ConfirmTransferToPhone52", MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();

        //adding values to SharedPreferences
        // make sure that in the getsharedPreferences the key value should be the same as the intent putextra class value

        pref = getApplicationContext().getSharedPreferences("ConfirmTransferToPhone52", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("phoneCompany", phoneCompany);
        editor.putString("phoneName", phoneName);
        editor.putString("phone", phone);
        editor.putString("amount", amount);
        editor.commit();

        Intent move = new Intent(this, ConfirmTransferToPhone52.class);
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