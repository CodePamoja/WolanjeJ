package com.example.wolanjej;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmMultipleTransfer42 extends AppCompatActivity {

    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONENAME1 = "com.example.wolanjej.PHONENAME1";
    public static final String EXTRA_PHONENAME2 = "com.example.wolanjej.PHONENAME2";
    public static final String EXTRA_AGENTNAME = "com.example.wolanjej.AGENTNAME";
    public static final String EXTRA_PHONENUMBER1 = "com.example.wolanjej.PHONENUMBER1";
    public static final String EXTRA_PHONENUMBER2 = "com.example.wolanjej.PHONENUMBER2";
    public static final String EXTRA_MESSAGE = "com.example.wolanjej.MESSAGE";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public Button button;
    public TextView textView1, textView2, textView3, textView4, textView5, textView6,
            textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16;
    private String message;
    private String phoneNumber1, phoneNumber2;
    private String amount;
    private String agentName;
    private String phoneName1, phoneName2;
    private String sessionId;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_multiple_transfer42);
        setToolBar();
        Intent intent = getIntent();
        this.message = intent.getStringExtra(TransferToWalletMultiple40.EXTRA_MESSAGE);
        this.phoneNumber1 = intent.getStringExtra(TransferToWalletMultiple40.EXTRA_PHONENUMBER1);
        this.phoneNumber2 = intent.getStringExtra(TransferToWalletMultiple40.EXTRA_PHONENUMBER2);
        this.amount = intent.getStringExtra(TransferToWalletMultiple40.EXTRA_AMOUNT);
        this.phoneName1 = intent.getStringExtra(TransferToWalletMultiple40.EXTRA_PHONENAME1);
        this.phoneName2 = intent.getStringExtra(TransferToWalletMultiple40.EXTRA_PHONENAME2);
        this.sessionId = intent.getStringExtra(TransferToWalletMultiple40.EXTRA_SESSION);
        this.agentName = intent.getStringExtra(TransferToWalletMultiple40.EXTRA_AGENTNO);

        button = (Button) findViewById(R.id.confirm_multiple_transfer);
        textView1 = (TextView) findViewById(R.id.confirmMultipleName1);
        textView1.setText(phoneName1);
        textView2 = (TextView) findViewById(R.id.dateConfirmMultiple1);
        textView2.setText(dateTime());
        textView3 = (TextView) findViewById(R.id.fromConfirmMultiple);
        textView3.setText(agentName);
        textView4 = (TextView) findViewById(R.id.toConfirmMultiple1);
        textView4.setText(phoneName1);
        textView5 = (TextView) findViewById(R.id.amountConfirmMultiple1);
        textView5.setText(amount);
        textView6 = (TextView) findViewById(R.id.messageConfirm1);
        textView6.setText(message);
        textView7 = (TextView) findViewById(R.id.Confirm1transactionFee);
        //TODO: setfee
        textView8 = (TextView) findViewById(R.id.totalAmountConfirm1);
        textView8.setText(amount);
        textView9 = (TextView) findViewById(R.id.confirmName2);
        textView9.setText(phoneName2);
        textView10 = (TextView) findViewById(R.id.dataConfirm2);
        textView10.setText(dateTime());
        textView11 = (TextView) findViewById(R.id.fromUserConfirm01);
        textView11.setText(agentName);
        textView12 = (TextView) findViewById(R.id.NameconfirmTo2);
        textView12.setText(phoneName2);
        textView13 = (TextView) findViewById(R.id.amountConfirm2);
        textView13.setText(amount);
        textView14 = (TextView) findViewById(R.id.messageConfirmMultiple2);
        textView14.setText(message);
        textView15 = (TextView) findViewById(R.id.transactionFeeConfirmMultiple2);
        //TODO: setfee
        textView16 = (TextView) findViewById(R.id.totalAmountConfirm2);
        textView16.setText(amount);
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
        final Intent movetoLogo = new Intent(this, TransferToWalletMultiple40.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );
    }

    public String dateTime() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a | dd/MMM/yyyy ");

        return simpleDateFormat.format(calendar.getTime());
    }

    public void movetoPin() {
        Intent move = new Intent(this, EnterPin.class);
        move.putExtra("Class", "ConfirmMultipleTransfer");
        move.putExtra(EXTRA_SESSION, sessionId);
        move.putExtra(EXTRA_PHONENAME1, phoneName1);
        move.putExtra(EXTRA_PHONENAME2, phoneName2);
        move.putExtra(EXTRA_AGENTNAME, agentName);
        move.putExtra(EXTRA_PHONENUMBER1, phoneNumber1);
        move.putExtra(EXTRA_PHONENUMBER2, phoneNumber2);
        move.putExtra(EXTRA_MESSAGE, message);
        move.putExtra(EXTRA_AMOUNT, amount);
        startActivity(move);
        startActivity(move);
    }
}