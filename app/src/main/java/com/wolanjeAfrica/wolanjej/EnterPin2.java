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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

public class EnterPin2 extends AppCompatActivity {
    public static final String EXTRA_SEND_FEE = "com.example.wolanjej.SEND_FEE";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_REFERENCE_NUMBER = "com.example.wolanjej.REFERNCE_NUMBER";
    private static final String TAG = "TVSubscription";
    private Button button;
    private String ProductName;
    private String amount;
    private String userId;
    private String AccountNumber;
    private TextView textView1, textView2, textView3, textView4;
    private ProgressBar progressBar;
    private SharedPreferences pref;
    private String sessionID;
    private String phoneNumber;
    private String sentAmount;
    private Realm realm;
    private String sendfee;
    private EditText text1, text2, text3, text4;
    private String sendIDReference;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_subscriptions);
        Realm.init(this);

        //pin editText
        text1 = findViewById(R.id.pinDigit1);
        text2 = findViewById(R.id.pinDigit2);
        text3 = findViewById(R.id.pinDigit3);
        text4 = findViewById(R.id.pinDigit4);
        //

        textView1 = (TextView) findViewById(R.id.txt_TvSubscription_sp);
        textView2 = (TextView) findViewById(R.id.txt_Account_noTvSubscription);
        textView3 = (TextView) findViewById(R.id.txt_amount_EWallet_2_1);
        textView4 = (TextView) findViewById(R.id.txtTvSubscription);
        progressBar = (ProgressBar) findViewById(R.id.progressTvSubscription);
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.phoneNumber = pref.getString("agentno", "");
        this.userId = pref.getString("userDbId", null);

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);

        switch (className) {
            case"LinkAccount11":
                break;
            case "HomePayTvSubscription":
                this.ProductName = intentExtra.getStringExtra(Home.EXTRA_PRODUCT_NAME);
                this.amount = intentExtra.getStringExtra(Home.EXTRA_AMOUNT);
                this.AccountNumber = intentExtra.getStringExtra(Home.EXTRA_ACCOUNTNUMBER);
                textView1.setText(ProductName);
                textView2.setText(AccountNumber);
                textView3.setText(amount);
                textView4.setText("Confirmation: Tv Subscription");
                getProceedIntent();
                break;
            case "HomePayWalletTopUp":
                this.ProductName = intentExtra.getStringExtra(Home.EXTRA_PRODUCT_NAME);
                this.amount = intentExtra.getStringExtra(Home.EXTRA_AMOUNT);
                this.AccountNumber = intentExtra.getStringExtra(Home.EXTRA_ACCOUNTNUMBER);
                textView1.setText(ProductName);
                textView2.setText(AccountNumber);
                textView3.setText(amount);
                textView4.setText("Confirmation: Top up Subscription");
                getProceedIntent();
                break;
            case "HomeWithdraw":
                this.ProductName = intentExtra.getStringExtra(Home.EXTRA_PRODUCT_NAME);
                this.amount = intentExtra.getStringExtra(Home.EXTRA_AMOUNT);
                this.AccountNumber = intentExtra.getStringExtra(Home.EXTRA_ACCOUNTNUMBER);
                textView1.setText(ProductName);
                textView2.setText(AccountNumber);
                textView3.setText(amount);
                textView4.setText("Withdraw Confirmation");
                getProceedIntent();
                break;

        }
        initPinEntry();
        setActionBarColor();
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
                        case "HomePayTvSubscription":
                        case "HomePayWalletTopUp":
                        case "HomeWithdraw":
                            if (ValidateUserPin(fullPin)) {
                                PerformTransaction(ProductName,AccountNumber,phoneNumber,amount);
                            } else {
                                Toast.makeText(EnterPin2.this, "Invalid pin", Toast.LENGTH_SHORT).show();
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
        transactionApi.userTransactions(EnterPin2.this, sessionID, product, refNo,phoneNumber,amount).observe(this, servicesModel -> {

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
        intent.putExtra(EXTRA_AMOUNT, sentAmount);
        intent.putExtra(EXTRA_REFERENCE_NUMBER, sendIDReference);
        startActivity(intent);

    }

    public void ConfirmPaymentUnsuccessful() {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("Class", "payInternet2pinUnsuccessful");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(EXTRA_SEND_FEE, sendfee);
        intent.putExtra(EXTRA_AMOUNT, sentAmount);
        intent.putExtra(EXTRA_REFERENCE_NUMBER, sendIDReference);
        startActivity(intent);
    }

}