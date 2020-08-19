package com.example.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmTransferToBank46 extends AppCompatActivity {
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_MESSAGE = "com.example.wolanjej.MESSAGE";
    public static final String EXTRA_ACCOUNTNUMBER = "com.example.wolanjej.ACCOUNTNUMBER";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_BANKSELECTED = "com.example.wolanjej.BANKSELECTED";
    public static final String EXTRA_BRANCHNAME = "com.example.wolanjej.BRANCHNAME";
    public static final String EXTRA_HOLDERNAME = "com.example.wolanjej.HOLDERSNAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    private Button button;
    private EditText text;
    private String accNumber;
    private String phoneNumber;
    private String phoneName;
    private String holderName;
    private String branchName;
    private String amount;
    private String bankSelected;
    private String message;
    private String AGENTNO;
    private String userName;
    private TextView tvtext;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_transfer_to_bank46);
        setToolBar();

        //SharedPreferences values for login activity class eg token
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.userName = pref.getString("user_name", "");


        Intent intentExtra = getIntent();


        this.accNumber = intentExtra.getStringExtra(TransferToBank44.EXTRA_ACCOUNTNUMBER);
        this.phoneNumber = intentExtra.getStringExtra(TransferToBank44.EXTRA_PHONENUMBER);
        this.phoneName = intentExtra.getStringExtra(TransferToBank44.EXTRA_PHONENAME);
        this.holderName = intentExtra.getStringExtra(TransferToBank44.EXTRA_HOLDERNAME);
        this.amount = intentExtra.getStringExtra(TransferToBank44.EXTRA_AMOUNT);
        this.message = intentExtra.getStringExtra(TransferToBank44.EXTRA_MESSAGE);
        this.branchName = intentExtra.getStringExtra(TransferToBank44.EXTRA_BRANCHNAME);
        this.bankSelected = intentExtra.getStringExtra(TransferToBank44.EXTRA_BANKSELECTED);

        tvtext = findViewById(R.id.bankToName);
        tvtext.setText(holderName);

        tvtext = findViewById(R.id.userToName);
        tvtext.setText(holderName);

        tvtext = findViewById(R.id.dateBank);
        tvtext.setText(dateTime());

        tvtext = findViewById(R.id.bankAccNumber);
        tvtext.setText(accNumber);

        tvtext = findViewById(R.id.bankMessage);
        tvtext.setText(message);

        tvtext = findViewById(R.id.AmountToBank);
        tvtext.setText("KES " + amount);

        tvtext = findViewById(R.id.bankTransFee);
        tvtext.setText("0.00");

        tvtext = findViewById(R.id.totBankToAmount);
        tvtext.setText("KES " + amount);

        button = (Button) findViewById(R.id.confirm_bank_transfer);
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
                        movetoLogo.putExtra("Class", "ConfirmTransferToBank46");
                        startActivity(movetoLogo);
                    }
                }
        );
    }

    public void movetoPin() {
        Intent move = new Intent(this, EnterPin.class);
        move.putExtra("Class", "TransferToBank44");
        move.putExtra(EXTRA_AMOUNT, amount);
        move.putExtra(EXTRA_MESSAGE, message);
        move.putExtra(EXTRA_ACCOUNTNUMBER, accNumber);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_BANKSELECTED, bankSelected);
        move.putExtra(EXTRA_BRANCHNAME, branchName);
        move.putExtra(EXTRA_HOLDERNAME, holderName);
        move.putExtra(EXTRA_PHONENUMBER, phoneNumber);
        startActivity(move);
        finish();
    }

    public String dateTime() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a | dd/MMM/yyyy ");
        String dateTime = simpleDateFormat.format(calendar.getTime());

        return dateTime;
    }
}