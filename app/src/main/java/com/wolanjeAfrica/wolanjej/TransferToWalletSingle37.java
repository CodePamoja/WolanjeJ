package com.wolanjeAfrica.wolanjej;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.wolanjeAfrica.wolanjej.Utils.CheckPhoneNumber;
import com.wolanjeAfrica.wolanjej.Utils.WolenjeUtil;
import com.wolanjeAfrica.wolanjej.ViewModels.UserBalanceViewModel;
import com.wolanjeAfrica.wolanjej.models.BalanceModel;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.SelectUserAdapter;

import java.util.List;

public class TransferToWalletSingle37 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] selectUser = {"Select User","Single Transfers", "Multiple Transfers"};
    public static final String EXTRA_MESSAGE = "com.example.wolanjej.MESSAGE";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_PROVIDER = "com.example.wolanjej.PROVIDER";
    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_PARENTCLASSNAME = "com.example.wolanjej.PARENTCLASSNAME";
    private static String className;
    private Button button;
    private Spinner spin;
    private EditText text, editText1, editText2, editText3;
    private String phoneNumber;
    private String sessionID;
    private String phoneName;
    private String AGENTNO;
    private String phoneCompany;
    private String amount;
    private ArrayAdapter adapter;
    private String MY_BALANCE;
    private TextView textView;
    private SharedPreferences pref;
    private  String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_wallet_single37);
        setToolBar();
        editText1 = (EditText) findViewById(R.id.walluserName);
        editText2 = (EditText) findViewById(R.id.walltAmount);
        editText3 = (EditText) findViewById(R.id.walltMessage);
        textView = (TextView) findViewById(R.id.balancetxtTwallet);

        pref = getApplication().getSharedPreferences("LogIn", Context.MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");
        this.userId = pref.getString("userDbId", null);

        spin = (Spinner) this.findViewById(R.id.select_user);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        adapter = ArrayAdapter.createFromResource(this,
                R.array.user_select, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(adapter);
        fetchClassIntent();

        UserBalanceViewModel userBalanceViewModel = new ViewModelProvider(this).get(UserBalanceViewModel.class);
        userBalanceViewModel.getUserBalance(TransferToWalletSingle37.this, sessionID).observe(this, new Observer<List<BalanceModel>>() {
            @Override
            public void onChanged(List<BalanceModel> balanceModels) {
                for (BalanceModel b : balanceModels) {
                    MY_BALANCE = b.getBalance();
                    textView.setText("KES" + MY_BALANCE);
                }
            }
        });

    }


    private void fetchClassIntent() {

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        switch (className) {
            case"LinkAccount11":
            case "EnterPin":
                break;
            case "MainTransfer36":
                TransferToWalletSingle37.className = intentExtra.getStringExtra(MainTransfer36.EXTRA_PARENTCLASSNAME);
                String activePaymentMethod = new WolenjeUtil().ActivePaymentMethod(userId);
                if (activePaymentMethod == null){
                    Intent intent = new Intent(TransferToWalletSingle37.this, LinkAccount11.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("Class", "TransferToWalletSingle37");
                    startActivity(intent);
                    finish();
                }
                break;
            case "TransferToWalletMultiple40":
                this.sessionID = intentExtra.getStringExtra(TransferToWalletMultiple40.EXTRA_SESSION);
                this.AGENTNO = intentExtra.getStringExtra(TransferToWalletMultiple40.EXTRA_AGENTNO);
                break;
            case "SelectUserAdapter": {
                this.phoneNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
                this.phoneName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);
                editText1.setText(phoneNumber);
                break;
            }
            case "ConfirmSingleTransfer40": {
                this.phoneNumber = "+" + intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENUMBER);
                String userName = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENAME);
                this.sessionID = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_SESSION);
                this.AGENTNO = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_AGENTNO);
                this.phoneName = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENAME);
                String sendAmount = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_AMOUNT);
                String sendMessage = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_MESSAGE);
                editText1.setText(userName);
                editText2.setText(sendAmount);
                editText3.setText(sendMessage);
                break;
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if ("Multiple Transfers".equals(selectUser[position])) {
            Intent move = new Intent(this, TransferToWalletMultiple40.class);
            move.putExtra("Class", "TransferToWalletSingle37");
            move.putExtra(EXTRA_SESSION, sessionID);
            move.putExtra(EXTRA_AGENTNO, AGENTNO);
            startActivity(move);
            finish();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, MainTransfer36.class);
        tb.setNavigationOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Log.e("set toolbar sess", sessionID);
                        movetoLogo.putExtra("Class", "TransferToWalletSingle37");
                        movetoLogo.putExtra(EXTRA_SESSION, sessionID);
                        movetoLogo.putExtra(EXTRA_AGENTNO, AGENTNO);
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

    public void moveToContact(View view) {
        Log.e("session before contact", sessionID);
        Intent move = new Intent(this, ContactsView.class);
        move.putExtra("Class", "TransferToWalletSingle37");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        move.putExtra(EXTRA_CLASSTYPE, "wallet");
        startActivity(move);
        finish();
    }

    public void movetoSingle() {
        Intent move = new Intent(this, TransferToWalletSingle37.class);
        startActivity(move);
    }

    public void movetoMultiple() {
        Intent move = new Intent(this, TransferToWalletMultiple40.class);
        startActivity(move);
    }

    public void transferMoney(View view) {

        String phone = editText1.getText().toString();
        String amount = editText2.getText().toString();
        String message = editText3.getText().toString();
        if (phone.isEmpty()) {
            Toast.makeText(this, "please provide recipient", Toast.LENGTH_SHORT).show();
            return;
        }
        if (amount.isEmpty()) {
            Toast.makeText(this, "please provide amount", Toast.LENGTH_SHORT).show();
            return;
        }

        String phonenumber = CheckPhoneNumber.getInstance().changePhoneNo(TransferToWalletSingle37.this, phone, view);
        Log.e("session after contact", sessionID);
        Log.e("TAG phone number last", phonenumber);
        if (!phonenumber.equals("Fasle")) {
            valuesConferm(phonenumber, amount, message);
        }
    }

    public void valuesConferm(String phone, String amount, String message) {
        Log.e("session before contact", sessionID);
        Intent move = new Intent(this, ConfirmSingleTransfer40.class);
        move.putExtra("Class","TransferToWalletSingle37");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_MESSAGE, message);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        move.putExtra(EXTRA_PHONENUMBER, phone);
        move.putExtra(EXTRA_AMOUNT, amount);
        move.putExtra(EXTRA_PARENTCLASSNAME,className);
        startActivity(move);
    }


}