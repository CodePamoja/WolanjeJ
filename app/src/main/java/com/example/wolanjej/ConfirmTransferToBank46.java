package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmTransferToBank46 extends AppCompatActivity {
    private Button button;
    private EditText text;
    private String accNumber;
    private String phoneNumber;
    private String sessionID;
    private String phoneName;
    private String holderName;
    private String branchName;
    private String amount;
    private String bankSelected;
    public String message;
    private String AGENTNO;
    private TextView tvtext;

    public static final String EXTRA_MESSAGE = "com.example.wolanjej.MESSAGE";
    public static final String EXTRA_PROVIDER = "com.example.wolanjej.PROVIDER";
    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_HOLDERNAME = "com.example.wolanjej.HOLDERNAME";
    public static final String EXTRA_ACCOUNTNUMBER = "com.example.wolanjej.ACCOUNTNUMBER";
    public static final String EXTRA_BRANCHNAME = "com.example.wolanjej.BRANCHNAME";
    public static final String EXTRA_BANKSELECTED = "com.example.wolanjej.BANKSELECTED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_transfer_to_bank46);
        setToolBar();

        Intent move = getIntent();
        this.accNumber = move.getStringExtra(TransferToBank44.EXTRA_ACCOUNTNUMBER);
        this.phoneNumber = move.getStringExtra(TransferToBank44.EXTRA_PHONENUMBER);
        this.sessionID = move.getStringExtra(TransferToBank44.EXTRA_SESSION);
        this.phoneName = move.getStringExtra(TransferToBank44.EXTRA_PHONENAME);
        this.holderName = move.getStringExtra(TransferToBank44.EXTRA_HOLDERNAME);
        this.AGENTNO = move.getStringExtra(TransferToBank44.EXTRA_AGENTNO);
        this.amount = move.getStringExtra(TransferToBank44.EXTRA_AMOUNT);
        this.message = move.getStringExtra(TransferToBank44.EXTRA_MESSAGE);
        this.branchName = move.getStringExtra(TransferToBank44.EXTRA_BRANCHNAME);
        this.bankSelected = move.getStringExtra(TransferToBank44.EXTRA_BANKSELECTED);

        tvtext =  findViewById(R.id.bankToName);
        tvtext.setText(holderName);

        tvtext =  findViewById(R.id.userToName);
        tvtext.setText(holderName);

        tvtext =  findViewById(R.id.dateBank);
        tvtext.setText(dateTime());

        tvtext =  findViewById(R.id.bankAccNumber);
        tvtext.setText(accNumber);

        tvtext =  findViewById(R.id.bankMessage);
        tvtext.setText(message);

        tvtext =  findViewById(R.id.AmountToBank);
        tvtext.setText("KES "+amount);

        tvtext =  findViewById(R.id.bankTransFee);
        tvtext.setText("0.00");

        tvtext =  findViewById(R.id.totBankToAmount);
        tvtext.setText("KES "+amount);

        button = (Button)findViewById(R.id.confirm_bank_transfer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetoPin();
            }
        });
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, TransferToBank44.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("set toolbar sess", sessionID);
                        movetoLogo.putExtra("Class","ConfirmTransferToBank46");
                        movetoLogo.putExtra(EXTRA_SESSION, sessionID);
                        movetoLogo.putExtra(EXTRA_MESSAGE, message);
                        movetoLogo.putExtra(EXTRA_AGENTNO, AGENTNO);
                        movetoLogo.putExtra(EXTRA_PHONENAME, phoneName);
                        movetoLogo.putExtra(EXTRA_PHONENUMBER, phoneNumber);
                        movetoLogo.putExtra(EXTRA_HOLDERNAME, holderName);
                        movetoLogo.putExtra(EXTRA_ACCOUNTNUMBER, accNumber);
                        movetoLogo.putExtra(EXTRA_BRANCHNAME, branchName);
                        movetoLogo.putExtra(EXTRA_BANKSELECTED, bankSelected);
                        movetoLogo.putExtra(EXTRA_AMOUNT, amount);
                        startActivity(movetoLogo);
                    }
                }
        );
    }

    public void movetoPin() {
        Intent move = new Intent(this, EnterPin.class);
        move.putExtra("Class","TransferToBank44");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_HOLDERNAME, holderName);
        move.putExtra(EXTRA_MESSAGE, message);
        move.putExtra(EXTRA_ACCOUNTNUMBER, accNumber);
        move.putExtra(EXTRA_AMOUNT, amount);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        move.putExtra(EXTRA_BRANCHNAME, branchName);
        move.putExtra(EXTRA_BANKSELECTED, bankSelected);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_PHONENUMBER, phoneNumber);
        startActivity(move);
    }

    public String dateTime(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a | dd/MMM/yyyy ");
        String dateTime = simpleDateFormat.format(calendar.getTime());

        return dateTime;
    }
}