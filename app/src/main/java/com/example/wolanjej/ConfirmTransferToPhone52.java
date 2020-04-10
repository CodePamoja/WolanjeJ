package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmTransferToPhone52 extends AppCompatActivity {
    private Button button;
    private String phoneNumber;
    private String sessionID;
    private String phoneName;
    public String amount;
    private TextView tvtext;

    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_transfer_to_phone52);
        setToolBar();

        Intent move = getIntent();
        this.phoneNumber = move.getStringExtra(TransferToPhone50.EXTRA_PHONENUMBER);
        this.sessionID = move.getStringExtra(TransferToPhone50.EXTRA_SESSION);
        this.phoneName = move.getStringExtra(TransferToPhone50.EXTRA_PHONENAME);
        this.amount = move.getStringExtra(TransferToPhone50.EXTRA_AMOUNT);

        tvtext =  findViewById(R.id.PName);
        tvtext.setText(phoneName);

        tvtext =  findViewById(R.id.transDateTime);
        tvtext.setText(dateTime());

        tvtext =  findViewById(R.id.PNumber);
        tvtext.setText("+"+phoneNumber);

        tvtext =  findViewById(R.id.sendAmount);
        tvtext.setText("KES "+amount);

        tvtext =  findViewById(R.id.transFee);
        tvtext.setText("0.00");

        tvtext =  findViewById(R.id.totalAmount);
        tvtext.setText("KES "+amount);

        button = (Button)findViewById(R.id.confirm_phone_transfer);
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
        final Intent movetoLogo = new Intent(this,TransferToPhone50.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movetoLogo.putExtra(EXTRA_SESSION, sessionID);
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
        startActivity(move);
    }

    public String dateTime(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a | dd/MMM/yyyy ");
        String dateTime = simpleDateFormat.format(calendar.getTime());

        return dateTime;
    }
}