package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmTransferToPhone52 extends AppCompatActivity {
    private Button button;
    private String phoneNumber;
    private String sessionID;
    private String phoneName;
    public String amount;
    private String phoneProvider;
    private TextView tvtext;
    private String AGENTNO;
    private String userName;
    private SharedPreferences pref;

    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_PROVIDER = "com.example.wolanjej.PROVIDER";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_transfer_to_phone52);
        setToolBar();
        setActionBarColor();

        //SharedPreferences values for login activity class eg token
        pref=getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.userName= pref.getString("user_name", "");

        //SharedPreferences values for TransferToPhone52 activity class eg token
        pref=getApplication().getSharedPreferences("ConfirmTransferToPhone52", MODE_PRIVATE);
        this.phoneNumber =  pref.getString("phone", "");
        this.phoneName =  pref.getString("phoneName", "");
        this.amount =  pref.getString("amount", "");
        this.phoneProvider =  pref.getString("phoneCompany", "");

        tvtext =  findViewById(R.id.PName);
        tvtext.setText(phoneName);

        tvtext =  findViewById(R.id.senderName);
        tvtext.setText(userName);

        tvtext =  findViewById(R.id.transDateTime);
        tvtext.setText(dateTime());

        tvtext =  findViewById(R.id.senderMessage);
        tvtext.setText("Thank You Test");

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

    private void setActionBarColor() {
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
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
                        movetoLogo.putExtra("Class","ConfirmTransferToPhone52");
                        startActivity(movetoLogo);
                    }
                }
        );
    }

    public void movetoPin() {
        Intent move = new Intent(this, EnterPin.class);
        move.putExtra("Class","TransferToPhone50");
        startActivity(move);
    }

    public String dateTime(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a | dd/MMM/yyyy ");
        String dateTime = simpleDateFormat.format(calendar.getTime());

        return dateTime;
    }
}