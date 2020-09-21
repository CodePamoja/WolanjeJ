package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.wolanjeAfrica.wolanjej.fragments.BulkPaymentFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmSingleTransfer40 extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.wolanjej.MESSAGE";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_PROVIDER = "com.example.wolanjej.PROVIDER";
    public static final String EXTRA_PRODUCT_NAME = "com.example.wolanjej.PRODUCT_NAME";
    public static final String EXTRA_PARENTCLASSNAME = "com.example.wolanjej.PARENTCLASSNAME";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    private Button button;
    private String phoneNumber;
    private String sessionID;
    private String phoneName;
    public String amount;
    public String message;
    private String AGENTNO;
    private TextView tvtext;
    private String previousClassName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_single_transfer40);
        setToolBar();
        Intent move = getIntent();
        previousClassName = getIntent().getStringExtra("Class");
        switch (previousClassName) {
            case "TransferToWalletSingle37":
                this.phoneNumber = move.getStringExtra(TransferToWalletSingle37.EXTRA_PHONENUMBER);
                this.sessionID = move.getStringExtra(TransferToWalletSingle37.EXTRA_SESSION);
                this.phoneName = move.getStringExtra(TransferToWalletSingle37.EXTRA_PHONENAME);
                this.amount = move.getStringExtra(TransferToWalletSingle37.EXTRA_AMOUNT);
                this.AGENTNO = move.getStringExtra(TransferToWalletSingle37.EXTRA_AGENTNO);
                this.message = move.getStringExtra(TransferToWalletSingle37.EXTRA_MESSAGE);
                this.previousClassName = "Home";
                break;
            case "BulkPaymentFrag":
                this.phoneNumber = move.getStringExtra(BulkPaymentFragment.EXTRA_PHONENUMBER);
                this.sessionID = move.getStringExtra(BulkPaymentFragment.EXTRA_SESSION);
                this.phoneName = move.getStringExtra(BulkPaymentFragment.EXTRA_PHONENAME);
                this.amount = move.getStringExtra(BulkPaymentFragment.EXTRA_AMOUNT);
                this.AGENTNO = move.getStringExtra(BulkPaymentFragment.EXTRA_AGENTNO);
                this.message = move.getStringExtra(BulkPaymentFragment.EXTRA_MESSAGE);
                break;
            default:
                break;
        }


        tvtext = findViewById(R.id.waltToName);
        tvtext.setText(phoneName);

        tvtext = findViewById(R.id.waltName);
        tvtext.setText(phoneName);

        tvtext = findViewById(R.id.waltDateTime);
        tvtext.setText(dateTime());

        tvtext = findViewById(R.id.waltMessage);
        tvtext.setText(message);

        tvtext = findViewById(R.id.waltAmout);
        tvtext.setText("KES " + amount);

        tvtext = findViewById(R.id.waltTransFee);
        tvtext.setText("0.00");

        tvtext = findViewById(R.id.waltTotal);
        tvtext.setText("KES " + amount);

        button = (Button) findViewById(R.id.confirm_single_transfer);
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
                        movetoLogo.putExtra("Class", "ConfirmSingleTransfer40");
                        movetoLogo.putExtra(EXTRA_SESSION, sessionID);
                        movetoLogo.putExtra(EXTRA_MESSAGE, message);
                        movetoLogo.putExtra(EXTRA_PHONENAME, phoneName);
                        movetoLogo.putExtra(EXTRA_AGENTNO, AGENTNO);
                        movetoLogo.putExtra(EXTRA_PHONENUMBER, phoneNumber);
                        movetoLogo.putExtra(EXTRA_AMOUNT, amount);
                        startActivity(movetoLogo);
                    }
                }
        );

        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }

    public void movetoPin() {
        Intent move = new Intent(this, EnterPin.class);
        move.putExtra("Class", "TransferToWalletSingle37");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        move.putExtra(EXTRA_PHONENUMBER, phoneNumber);
        move.putExtra(EXTRA_AMOUNT, amount);
        move.putExtra(EXTRA_PARENTCLASSNAME, previousClassName);
        startActivity(move);
    }

    public String dateTime() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a | dd/MMM/yyyy ");
        String dateTime = simpleDateFormat.format(calendar.getTime());

        return dateTime;
    }
}