package com.example.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.wolanjej.recyclerAdapters.SelectUserAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransferToBank44 extends AppCompatActivity {

    private Button button;
    private Spinner spin, spinner;
    private EditText text, editText, editText1, editText2, editText3, editText4;
    private String phoneNumber;
    private String sessionID;
    private String branchName;
    private String accNumber;
    private String amount;
    private String phoneCompany;
    private String phoneName;
    private ArrayAdapter<CharSequence> aa;
    private ArrayAdapter<CharSequence> adapter;
    private String bankSelected;
    private String AGENTNO;
    private static final String TAG = "TransferToBank";
    private SharedPreferences pref;


    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_MESSAGE = "com.example.wolanjej.MESSAGE";
    public static final String EXTRA_PROVIDER = "com.example.wolanjej.PROVIDER";
    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_HOLDERNAME = "com.example.wolanjej.HOLDERNAME";
    public static final String EXTRA_ACCOUNTNUMBER = "com.example.wolanjej.ACCOUNTNUMBER";
    public static final String EXTRA_BRANCHNAME = "com.example.wolanjej.BRANCHNAME";
    public static final String EXTRA_BANKSELECTED = "com.example.wolanjej.BANKSELECTED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_bank44);
        setToolBar();

        text = findViewById(R.id.bankAmount);
        editText = findViewById(R.id.bankMessage);
        editText1 = findViewById(R.id.holdName);
        editText2 = findViewById(R.id.accNumber);


        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        switch (className) {
            case "MainTransfer36":
                break;
            case "SelectUserAdapter": {
                this.phoneNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
                String userName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);
                this.phoneName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);

                editText1.setText(userName);
                break;
            }
            case "ConfirmTransferToBank46": {

                pref = getApplication().getSharedPreferences("ConfirmTransferToBank46", MODE_PRIVATE);
                this.accNumber = pref.getString("accNumber", "");
                this.phoneNumber = "+" + pref.getString("phone", "");
                this.phoneName = pref.getString("phoneName", "");
                this.branchName = pref.getString("branchName", "");
                this.bankSelected = pref.getString("bankSelected", "");

                String userName = pref.getString("holderName", "");
                String sendAmount = pref.getString("amount", "");
                String sendMessage = pref.getString("message", "");
                String sendAccNumber = pref.getString("accNumber", "");
                String sendBank = pref.getString("bankSelected", "");
                String sendBranch = pref.getString("branchName", "");


                editText1.setText(userName);
                text.setText(sendAmount);

                editText.setText(sendMessage);
                editText2.setText(sendAccNumber);

                break;
            }
        }

        spin = (Spinner) this.findViewById(R.id.selectBank);
        aa = ArrayAdapter.createFromResource(this,    // setting array-adapter belonging to spinner
                R.array.select_bank, android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);


        spinner = (Spinner) this.findViewById(R.id.branchName);
        adapter = ArrayAdapter.createFromResource(this,    // setting array-adapter belonging to spinner
                R.array.select_bank_branch, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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
                        movetoLogo.putExtra("Class", "TransferToBank44");
                        startActivity(movetoLogo);
                    }
                }
        );
    }

    public void moveToContact(View view) {
        Intent move = new Intent(this, ContactsView.class);
        move.putExtra("Class", "TransferToBank44");
        move.putExtra(EXTRA_CLASSTYPE, "bank");
        startActivity(move);
    }

    public void bankTransfer(View view) {
        String phone = phoneNumber;

        Log.e("TAG phone number check", "button pressed to transfer money" + phone);
        String phonenumber = changePhoneNo(phone, view);
        Log.e("TAG phone number last", phonenumber);
        if (!phonenumber.equals("Fasle")) {
            String amount = text.getText().toString();
            String message = editText.getText().toString();
            String holderName = editText1.getText().toString();
            String accNumber = editText2.getText().toString();
            if (amount.isEmpty() || message.isEmpty() || holderName.isEmpty() || accNumber.isEmpty()) {
                Toast.makeText(this, "please provide all the details", Toast.LENGTH_SHORT).show();
                return;
            }

            String[] bankAndBranch = selectedBank();
            String bank = bankAndBranch[0];
            String branch = bankAndBranch[1];

            Log.d(TAG, "bankTransfer: " + bank + "////" + branch);

            valuesConferm(holderName, amount, message, accNumber, branch, bank, phone);
        }
    }

    private String[] selectedBank() {
        String bank = spin.getSelectedItem().toString();
        String bank_branch = "False";
        String[] arr = new String[2];
        switch (bank) {
            case "Equity Bank":
            case "Co-operative Bank":
            case "CfC Stanbic Bank":
                arr[0] = bank;
                arr[1] = bank_branch = spinner.getSelectedItem().toString();
                break;
            case "Barclays Bank":
            case "Chase Bank":
            case "Citibank Bank":
                spinner.setAdapter(adapter);
                arr[0] = bank;
                arr[1] = spinner.getSelectedItem().toString();
                break;
        }
        return arr;
    }

    public void valuesConferm(String holderName, String amount, String message, String accNumber, String branchName, String bankSelect, String phone) {

        Intent move = new Intent(this, ConfirmTransferToBank46.class);
        move.putExtra("Class", "TransferToBank44");
        move.putExtra(EXTRA_AMOUNT, amount);
        move.putExtra(EXTRA_MESSAGE, message);
        move.putExtra(EXTRA_ACCOUNTNUMBER, accNumber);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_BANKSELECTED, bankSelect);
        move.putExtra(EXTRA_BRANCHNAME, branchName);
        move.putExtra(EXTRA_HOLDERNAME, holderName);
        move.putExtra(EXTRA_PHONENUMBER, phone);
        startActivity(move);
        finish();
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