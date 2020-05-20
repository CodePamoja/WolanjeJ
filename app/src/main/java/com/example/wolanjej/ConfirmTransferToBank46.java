package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private String phoneName;
    private String holderName;
    private String branchName;
    private String amount;
    private String bankSelected;
    public String message;
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
        pref=getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.userName= pref.getString("user_name", "");

        //SharedPreferences values for TransferToPhone52 activity class eg token
        pref=getApplication().getSharedPreferences("ConfirmTransferToBank46", MODE_PRIVATE);
        this.accNumber = pref.getString("accNumber", "");
        this.phoneNumber = pref.getString("phone", "");
        this.phoneName = pref.getString("phoneName", "");
        this.holderName = pref.getString("holderName", "");
        this.amount = pref.getString("amount", "");
        this.message = pref.getString("message", "");
        this.branchName = pref.getString("branchName", "");
        this.bankSelected = pref.getString("bankSelected", "");

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
                        movetoLogo.putExtra("Class","ConfirmTransferToBank46");
                        startActivity(movetoLogo);
                    }
                }
        );
    }

    public void movetoPin() {
        Intent move = new Intent(this, EnterPin.class);
        move.putExtra("Class","TransferToBank44");
        startActivity(move);
    }

    public String dateTime(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a | dd/MMM/yyyy ");
        String dateTime = simpleDateFormat.format(calendar.getTime());

        return dateTime;
    }
}