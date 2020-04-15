package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmSingleTransfer40 extends AppCompatActivity {
    private Button button;
    private String phoneNumber;
    private String sessionID;
    private String phoneName;
    public String amount;
    public String message;
    private TextView tvtext;

    public static final String EXTRA_MESSAGE = "com.example.wolanjej.MESSAGE";
    public static final String EXTRA_PROVIDER = "com.example.wolanjej.PROVIDER";
    public static final String EXTRA_PRODUCT_NAME = "com.example.wolanjej.PRODUCT_NAME";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_single_transfer40);
        setToolBar();

        Intent move = getIntent();
        this.phoneNumber = move.getStringExtra(TransferToWalletSingle37.EXTRA_PHONENUMBER);
        this.sessionID = move.getStringExtra(TransferToWalletSingle37.EXTRA_SESSION);
        this.phoneName = move.getStringExtra(TransferToWalletSingle37.EXTRA_PHONENAME);
        this.amount = move.getStringExtra(TransferToWalletSingle37.EXTRA_AMOUNT);
        this.message = move.getStringExtra(TransferToWalletSingle37.EXTRA_MESSAGE);

        tvtext =  findViewById(R.id.waltToName);
        tvtext.setText(phoneName);

        tvtext =  findViewById(R.id.waltName);
        tvtext.setText(phoneName);

        tvtext =  findViewById(R.id.waltDateTime);
        tvtext.setText(dateTime());

        tvtext =  findViewById(R.id.waltMessage);
        tvtext.setText(message);

        tvtext =  findViewById(R.id.waltAmout);
        tvtext.setText("KES "+amount);

        tvtext =  findViewById(R.id.waltTransFee);
        tvtext.setText("0.00");

        tvtext =  findViewById(R.id.waltTotal);
        tvtext.setText("KES "+amount);

        button = (Button)findViewById(R.id.confirm_single_transfer);
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
        final Intent movetoLogo = new Intent(this, TransferToWalletSingle37.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("set toolbar sess", sessionID);
                        movetoLogo.putExtra("Class","ConfirmSingleTransfer40");
                        movetoLogo.putExtra(EXTRA_SESSION, sessionID);
                        movetoLogo.putExtra(EXTRA_MESSAGE, message);
                        movetoLogo.putExtra(EXTRA_PHONENAME, phoneName);
                        movetoLogo.putExtra(EXTRA_PHONENUMBER, phoneNumber);
                        movetoLogo.putExtra(EXTRA_AMOUNT, amount);
                        startActivity(movetoLogo);
                    }
                }
        );
    }

    public void movetoPin() {
        Intent move = new Intent(this, EnterPin.class);
        move.putExtra("Class","TransferToWalletSingle37");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_PHONENUMBER, phoneNumber);
        move.putExtra(EXTRA_AMOUNT, amount);
        startActivity(move);
    }

    public String dateTime(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a | dd/MMM/yyyy ");
        String dateTime = simpleDateFormat.format(calendar.getTime());

        return dateTime;
    }
}