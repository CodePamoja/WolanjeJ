package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransferToBank44 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] select_bank = {"Select Bank","Equity Bank","Barclays Bank", "CfC Stanbic Bank","Co-operative Bank","Chase Bank", "Citibank"};
    String[] select_branch;

    private Button button;
    private Spinner spin;
    private EditText text;
    private String phoneNumber;
    private String sessionID;
    private String branchName;
    private String accNumber;
    private String amount;
    private String phoneCompany;
    private String phoneName;
    private String bankSelected;

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

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        if(className.equals("MainTransfer36")) {
            this.sessionID = intentExtra.getStringExtra(MainTransfer36.EXTRA_SESSION);
        }else if (className.equals("SelectUserAdapter")){
            this.phoneNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
            String userName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);
            this.sessionID = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_SESSION);
            this.phoneName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);

            EditText tvtext =  findViewById(R.id.holdName);
            tvtext.setText(userName);
        }else if (className.equals("ConfirmTransferToBank46")){
            this.phoneNumber ="+"+ intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_PHONENUMBER);
            String userName = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_PHONENAME);
            this.sessionID = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_SESSION);
            this.phoneName = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_PHONENAME);
            this.accNumber = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_ACCOUNTNUMBER);
            this.branchName = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_BRANCHNAME);
            this.bankSelected = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_BANKSELECTED);
            String sendAmount = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_AMOUNT);
            String sendMessage = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_MESSAGE);
            String sendAccNumber = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_ACCOUNTNUMBER);
            String sendBank = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_BANKSELECTED);
            String sendBranch = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_BRANCHNAME);

            EditText tvtext =  findViewById(R.id.holdName);
            tvtext.setText(userName);

            tvtext = findViewById(R.id.bankAmount);
            tvtext.setText(sendAmount);

            tvtext = findViewById(R.id.bankMessage);
            tvtext.setText(sendMessage);

            tvtext = findViewById(R.id.accNumber);
            tvtext.setText(sendAccNumber);

            tvtext = findViewById(R.id.branchName);
            tvtext.setText(sendBranch);
        }

        spin = (Spinner) this.findViewById(R.id.selectBank);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,select_bank);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getApplicationContext(),select_bank[position] , Toast.LENGTH_LONG).show();
        bankSelected = select_bank[position];
//        if ("Equity Bank".equals(select_bank[position])){
//           select_branch = new String[]{"Select Branch", "Awendo Branch", "Bomet Branch", "Bondo Branch", "Bungoma Branch", "Corporate Branch", "Eldoret Branch"};
//        }else if ("Barclays Bank".equals(select_bank[position])){
//            select_branch = new String[]{"Select Branch", "Awendo Branch", "Bomet Branch", "Bondo Branch", "Bungoma Branch", "Corporate Branch", "Eldoret Branch"};
//        }else if ("CfC Stanbic Bank".equals(select_bank[position])){
//            select_branch = new String[]{"Select Branch", "Awendo Branch", "Bomet Branch", "Bondo Branch", "Bungoma Branch", "Corporate Branch", "Eldoret Branch"};
//        }else if ("Co-operative Bank".equals(select_bank[position])){
//            select_branch = new String[]{"Select Branch", "Awendo Branch", "Bomet Branch", "Bondo Branch", "Bungoma Branch", "Corporate Branch", "Eldoret Branch"};
//        }else if ("Chase Bank".equals(select_bank[position])){
//            select_branch = new String[]{"Select Branch", "Awendo Branch", "Bomet Branch", "Bondo Branch", "Bungoma Branch", "Corporate Branch", "Eldoret Branch"};
//        }else if ("Citibank Bank".equals(select_bank[position])){
//            select_branch = new String[]{"Select Branch", "Awendo Branch", "Bomet Branch", "Bondo Branch", "Bungoma Branch", "Corporate Branch", "Eldoret Branch"};
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
                        movetoLogo.putExtra("Class","TransferToBank44");
                        movetoLogo.putExtra(EXTRA_SESSION, sessionID);
                        startActivity(movetoLogo);
                    }
                }
        );
    }

    public void movetoConfirm() {
        Intent move = new Intent(this, ConfirmTransferToBank46.class);
        startActivity(move);
    }

    public void moveToContact(View view) {
        Log.e("session before contact", sessionID);
        Intent move = new Intent(this, ContactsView.class);
        move.putExtra("Class","TransferToBank44");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_CLASSTYPE, "bank");
        startActivity(move);
    }

    public void bankTransfer(View view) {
        String phone = phoneNumber;

        Log.e("TAG phone number check", "button pressed to transfer money" + phone);
        String phonenumber = changePhoneNo(phone, view);
        Log.e("session after contact", sessionID);
        Log.e("TAG phone number last", phonenumber);
        if(phonenumber!="Fasle"){
            Log.e("TAG phone number check", "button pressed to transfer money" + bankSelected);
            if(!"Select Bank".equals(bankSelected)){

                String bankSelect = bankSelected;

                text = findViewById(R.id.bankAmount);
                String amount = text.getText().toString();

                text = findViewById(R.id.bankMessage);
                String message = text.getText().toString();

                text = findViewById(R.id.holdName);
                String holderName = text.getText().toString();

                text = findViewById(R.id.accNumber);
                String accNumber = text.getText().toString();

                text = findViewById(R.id.branchName);
                String branchName = text.getText().toString();

                if (amount!=null && message!=null && holderName!=null && accNumber!=null && branchName!=null ){
                    valuesConferm(holderName, amount, message, accNumber, branchName, bankSelect, phone);
                }
            }
        }
    }

    public void valuesConferm(String holderName, String amount, String message, String accNumber, String branchName,String bankSelect, String phone){
        Log.e("session before contact", sessionID);
        Intent move = new Intent(this, ConfirmTransferToBank46.class);
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_HOLDERNAME, holderName);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_MESSAGE, message);
        move.putExtra(EXTRA_PHONENUMBER, phone);
        move.putExtra(EXTRA_ACCOUNTNUMBER, accNumber);
        move.putExtra(EXTRA_AMOUNT, amount);
        move.putExtra(EXTRA_BRANCHNAME, branchName);
        move.putExtra(EXTRA_BANKSELECTED, bankSelect);
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