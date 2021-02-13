package com.wolanjeAfrica.wolanjej;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.wolanjeAfrica.wolanjej.RealmDataBase.DbMigrations;
import com.wolanjeAfrica.wolanjej.RealmDataBase.User;
import com.wolanjeAfrica.wolanjej.Utils.WolenjeUtil;
import com.wolanjeAfrica.wolanjej.ViewModels.TransactionApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.realm.Realm;
import okhttp3.Response;

public class EnterPin3 extends AppCompatActivity {
    private static final String TAG = "PayInternet";
    public static final String EXTRA_SEND_FEE = "com.example.wolanjej.SEND_FEE";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_REFERENCE_NUMBER = "com.example.wolanjej.REFERNCE_NUMBER";
    private Button button;
    private String sessionID;
    private String userId;
    private SharedPreferences pref;
    private String AccountNumber;
    private String Amount;
    private TextView textViewAccountNumber;
    private TextView textViewAmount;
    private String sendAmount;
    private TextView txtWallet3_Pay;
    private EditText text1, text2, text3, text4;
    private String phoneNumber;
    private String sendIDReference;
    private String sendfee;
    private Realm realm;
    private ProgressBar progressBar;
    private String product_name;
    private String sentAmount;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_internet2);
        Realm.init(this);

        //pin editText
        text1 = findViewById(R.id.pinDigit1);
        text2 = findViewById(R.id.pinDigit2);
        text3 = findViewById(R.id.pinDigit3);
        text4 = findViewById(R.id.pinDigit4);
        //

        button = (Button) findViewById(R.id.btn_enter_pay);
        textViewAccountNumber = (TextView) findViewById(R.id.txt_saf_eWallet3_1);
        textViewAmount = (TextView) findViewById(R.id.txt_numberEWallet3_1);
        txtWallet3_Pay = (TextView) findViewById(R.id.txtWallet3_Pay);
        progressBar = (ProgressBar) findViewById(R.id.progressInternet2);
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.phoneNumber = pref.getString("agentno", "");
        this.userId = pref.getString("userDbId", null);

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        String activePaymentMethod = new WolenjeUtil().ActivePaymentMethod(userId);
        switch (className) {
            case "LinkAccount11":
                break;
            case "HomePayNet":
                if (activePaymentMethod == null){
                    Intent intent = new Intent(EnterPin3.this, LinkAccount11.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("Class", "Enterpin3");
                    startActivity(intent);
                    finish();
                }
                this.AccountNumber = intentExtra.getStringExtra(Home.EXTRA_ACCOUNTNUMBER);
                this.Amount = intentExtra.getStringExtra(Home.EXTRA_AMOUNT);
                this.product_name = intentExtra.getStringExtra(Home.EXTRA_PRODUCT_NAME);
                textViewAccountNumber.setText(AccountNumber);
                textViewAmount.setText(Amount);
                getProceedIntent();
                break;
            case "HomePayElectricity":

                if (activePaymentMethod == null){
                    Intent intent = new Intent(EnterPin3.this, LinkAccount11.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("Class", "Enterpin3");
                    startActivity(intent);
                    finish();
                }
                this.AccountNumber = intentExtra.getStringExtra(Home.EXTRA_ACCOUNTNUMBER);
                this.product_name = intentExtra.getStringExtra(Home.EXTRA_PRODUCT_NAME);
                this.Amount = intentExtra.getStringExtra(Home.EXTRA_AMOUNT);
                textViewAccountNumber.setText(AccountNumber);
                textViewAmount.setText(Amount);
                txtWallet3_Pay.setText("Confirmation: Buy Token");
                getProceedIntent();
                break;
        }


        initPinEntry();
    }

    private void initPinEntry() {

        final String[] numbers = new String[4];

        text1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                numbers[0] = s.toString();
                // text1.setFocusable(false);
                text1.setClickable(false);
                text2.requestFocus();

            }
        });
        text2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {


                numbers[1] = s.toString();
                //text2.setFocusable(false);
                text2.setClickable(false);
                text3.requestFocus();
            }
        });
        text3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                numbers[2] = s.toString();
                //text3.setFocusable(false);
                text3.setClickable(false);
                text4.requestFocus();
            }
        });
        text4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                numbers[3] = s.toString();
                text4.setClickable(false);


            }
        });

    }

    private void getProceedIntent() {

        text4.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                EditText tvtext = findViewById(R.id.pinDigit1);
                String pin1 = tvtext.getText().toString();

                tvtext = findViewById(R.id.pinDigit2);
                String pin2 = tvtext.getText().toString();

                tvtext = findViewById(R.id.pinDigit3);
                String pin3 = tvtext.getText().toString();

                tvtext = findViewById(R.id.pinDigit4);
                String pin4 = tvtext.getText().toString();

                String fullPin = pin1 + pin2 + pin3 + pin4;
                Intent intentExtra = getIntent();
                String className = getIntent().getStringExtra("Class");
                if (className != null)
                    switch (className) {
                        case "HomePayNet":
                        case "HomePayElectricity":
                            if (ValidateUserPin(fullPin)) {
                                PerformTransaction(product_name,AccountNumber,phoneNumber,Amount);
                            }else {
                                Toast.makeText(EnterPin3.this, "Invalid Pin", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        default:
                            System.out.println("Not an airtel, safaricom or telkom");
                            break;
                    }
                return false;
            }
        });

    }
    public Boolean ValidateUserPin(String pin) {
        LogIn logIn = new LogIn();
        String hasshedPassword = logIn.generateHashedPassword(pin);  //public method in Login class

        realm = Realm.getInstance(DbMigrations.getDefaultInstance());
        User user = realm.where(User.class)
                .equalTo("id", Integer.valueOf(userId))
                .findFirst();

        if (user != null) {
            String password = user.getPassword();
            return password.equals(hasshedPassword);
        }
        return false;
    }

    private void PerformTransaction(String product, String refNo, String phoneNumber, String amount){
        progressBar.setVisibility(View.VISIBLE);
        TransactionApi transactionApi = new ViewModelProvider(this).get(TransactionApi.class);
        transactionApi.userTransactions(EnterPin3.this, sessionID, product, refNo,phoneNumber,amount).observe(this, servicesModel -> {

            progressBar.setVisibility(View.GONE);
            sentAmount=servicesModel.getAmountTransaction();
            sendfee= servicesModel.getTransactionFee();
            String sendNumber =servicesModel.getRef();
            sendIDReference = servicesModel.getId();
            String statusResulsts = servicesModel.getLast_status();
            switch (statusResulsts) {
                case "TRX_ASYNC":
                    ConfirmPaymentUnsuccessful();
                    break;
                case "TRX_OK":
                    ConfirmPaymentUnsuccessful();
                    ConfirmPaymentSuccess();
                    break;
                case "TRX_INSUFFICIENT_BALANCE":
                    ConfirmPaymentUnsuccessful();
                    break;
                case "TRX_VERIFY":
                    ConfirmPaymentUnsuccessful();
                    break;
                default:
                    ConfirmPaymentUnsuccessful();
                    break;
            }
        });
    }



    public void ConfirmPaymentSuccess() {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("Class", "PayInternet2PinSuccess");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(EXTRA_SEND_FEE, sendfee);
        intent.putExtra(EXTRA_AMOUNT, sendAmount);
        intent.putExtra(EXTRA_REFERENCE_NUMBER, sendIDReference);
        startActivity(intent);

    }

    public void ConfirmPaymentUnsuccessful() {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("Class", "payInternet2pinUnsuccessful");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(EXTRA_SEND_FEE, sendfee);
        intent.putExtra(EXTRA_AMOUNT, sendAmount);
        intent.putExtra(EXTRA_REFERENCE_NUMBER, sendIDReference);
        startActivity(intent);
    }



}