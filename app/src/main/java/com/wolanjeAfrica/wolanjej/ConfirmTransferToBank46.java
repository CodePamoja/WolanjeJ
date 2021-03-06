package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

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
    public static final String EXTRA_PHONECOMPANY = "com.example.wolanjej.PHONECOMPANY";
    public static final String EXTRA_PARENTCLASSNAME = "com.example.wolanjej.PARENTCLASSNAME";
    public static final String EXTRA_PRODUCTNAME = "com.example.wolanjej.PRODUCTNAME";
    private Button button;
    private EditText text;
    private String accNumber;
    private String phoneNumber;
    private String phoneProvider;
    private String phoneName;
    private String holderName;
    private String branchName;
    private String amount;
    private String bankSelected;
    private String message;
    private String AGENTNO;
    private String userName;
    private TextView tvtext;
    private static String className;
    private SharedPreferences pref;
    private String product_name;


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
        this.phoneProvider = intentExtra.getStringExtra(TransferToBank44.EXTRA_PHONECOMPANY);
        this.product_name = intentExtra.getStringExtra(TransferToBank44.EXTRA_PRODUCTNAME);
        className = intentExtra.getStringExtra(TransferToBank44.EXTRA_PARENTCLASSNAME);

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
        button.setOnClickListener(v -> movetoPin());
    }


    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, TransferToBank44.class);
        tb.setNavigationOnClickListener(
                v -> {
                    movetoLogo.putExtra("Class", "ConfirmTransferToBank46");
                    startActivity(movetoLogo);
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
        move.putExtra("Class", "TransferToBank44");
        move.putExtra(EXTRA_AMOUNT, amount);
        move.putExtra(EXTRA_MESSAGE, message);
        move.putExtra(EXTRA_ACCOUNTNUMBER, accNumber);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_BANKSELECTED, bankSelected);
        move.putExtra(EXTRA_BRANCHNAME, branchName);
        move.putExtra(EXTRA_HOLDERNAME, holderName);
        move.putExtra(EXTRA_PHONENUMBER, phoneNumber);
        move.putExtra(EXTRA_PHONECOMPANY,phoneProvider);
        move.putExtra(EXTRA_PARENTCLASSNAME,className);
        move.putExtra(EXTRA_PRODUCTNAME, product_name);
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