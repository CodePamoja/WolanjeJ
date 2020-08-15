package com.example.wolanjej;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

public class TvSubscriptions extends AppCompatActivity {
    private Button button;
    private String ProductName;
    private String amount;
    private String AccountNumber;
    private TextView textView1, textView2, textView3, textView4;
    private ProgressBar progressBar;
    private SharedPreferences pref;
    private String sessionID;
    private String phoneNumber;
    private String sentAmount;
    private String sendfee;
    private String sendIDReference;
    private static final String TAG = "TVSubscription";
    public static final String EXTRA_SEND_FEE = "com.example.wolanjej.SEND_FEE";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_REFERENCE_NUMBER = "com.example.wolanjej.REFERNCE_NUMBER";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_subscriptions);
        textView1 = (TextView) findViewById(R.id.txt_TvSubscription_sp);
        textView2 = (TextView) findViewById(R.id.txt_Account_noTvSubscription);
        textView3 = (TextView) findViewById(R.id.txt_amount_EWallet_2_1);
        textView4 = (TextView) findViewById(R.id.txtTvSubscription);
        progressBar = (ProgressBar) findViewById(R.id.progressTvSubscription);
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.phoneNumber = pref.getString("agentno", "");

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);

        switch (className) {
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


        }
        initPinEntry();
    }

    private void initPinEntry() {
        final EditText text1 = findViewById(R.id.pinDigit1);
        final EditText text2 = findViewById(R.id.pinDigit2);
        final EditText text3 = findViewById(R.id.pinDigit3);
        final EditText text4 = findViewById(R.id.pinDigit4);
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
        button = (Button) findViewById(R.id.btn_enter_pay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            new Transfer(fullPin, ProductName, amount, AccountNumber).execute();
                            break;
                        default:
                            System.out.println("Not an airtel, safaricom or telkom");
                            break;
                    }
            }
        });
    }

    public void ConfirmPaymentSuccess() {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("Class", "PayInternet2PinSuccess");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(EXTRA_SEND_FEE, sendfee);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_REFERENCE_NUMBER, sendIDReference);
        startActivity(intent);

    }

    public void ConfirmPaymentUnsuccessful() {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("Class", "payInternet2pinUnsuccessful");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(EXTRA_SEND_FEE, sendfee);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_REFERENCE_NUMBER, sendIDReference);
        startActivity(intent);
    }

    public class Transfer extends AsyncTask<Void, Void, Response> {
        String pin;
        String productName;
        String amount;
        String ref;

        public Transfer(String pin, String productName, String amount, String ref) {
            this.pin = pin;
            this.productName = productName;
            this.amount = amount;
            this.ref = ref;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Response doInBackground(Void... voids) {
            progressBar.setVisibility(View.VISIBLE);
            JSONArray jdataset = new JSONArray();
            JSONObject jdata = new JSONObject();


            try {
                jdata.put("product_name", productName);
                jdata.put("amount", amount);
                jdata.put("phone", phoneNumber);
                jdata.put("ref", ref);
                jdata.put("pin", pin);
                jdataset.put(jdata);
            } catch (Exception e) {
                e.getStackTrace();
            }

            JSONObject jMpesa = new JSONObject();
            try {
                jMpesa.put("ac_uname", "test");
                jMpesa.put("services", jdataset);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("TAG", String.valueOf(jMpesa));

            String url = "/api/transactions";
            OkhttpConnection okConn = new OkhttpConnection();
            Response result = okConn.postValue(url, jMpesa.toString(), sessionID);
            System.out.println("Response body json values are : " + result);
            return result;
        }

        @Override
        protected void onPostExecute(Response response) {
            progressBar.setVisibility(View.GONE);
            super.onPostExecute(response);
            if (response.code() == 201) {
                try {
                    String value = response.body().string();
                    JSONObject jBody = new JSONObject(value); // adding
                    System.out.println(TAG + "Response body json values are service : " + value);
                    Log.e("TAG", String.valueOf(value));
                    sentAmount = jBody.getJSONArray("services").getJSONObject(0).getString("amount");
                    sendfee = jBody.getJSONArray("services").getJSONObject(0).getString("fee");
                    String sendNumber = jBody.getJSONArray("services").getJSONObject(0).getString("ref");
                    sendIDReference = jBody.getJSONArray("services").getJSONObject(0).getString("id");
                    String statusResulsts = jBody.getJSONArray("services").getJSONObject(0).getString("status");
                    Log.e("TAG", sentAmount);

                    if (phoneNumber.equals(sendNumber) && statusResulsts.equals("TRX_ASYNC")) {
                        ConfirmPaymentSuccess();
                    } else if (statusResulsts.equals("TRX_OK")) {
                        ConfirmPaymentSuccess();
                    } else if (statusResulsts.equals("TRX_INSUFFICIENT_BALANCE")) {
                        Toast.makeText(getApplicationContext(), "You have insufficient balance on your wallet", Toast.LENGTH_LONG).show();
                        ConfirmPaymentUnsuccessful();
                    } else if (statusResulsts.equals("TRX_VERIFY")) {
                        Toast.makeText(getApplicationContext(), "the transaction is being verified", Toast.LENGTH_SHORT).show();
                        ConfirmPaymentSuccess();
                    } else if (statusResulsts.equals("TRX_RESPONSE_ERROR")) {
                        Toast.makeText(getApplicationContext(), "SorrynExperiences An error", Toast.LENGTH_SHORT).show();
                        ConfirmPaymentUnsuccessful();
                    } else {
                        Toast.makeText(getApplicationContext(), "You have insufficient balance", Toast.LENGTH_LONG).show();
                        ConfirmPaymentUnsuccessful();
                    }

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            } else if (response.code() != 201) {
                {
                    String statusResults = "unsuccessful";
                    try {
                        String result = response.body().string();
                        Log.e("TAG", String.valueOf(result));
//                Toast.makeText(EnterPin.this, "Please Try Again"+verifyResult, Toast.LENGTH_SHORT).show();
//                        showPopupFail();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}