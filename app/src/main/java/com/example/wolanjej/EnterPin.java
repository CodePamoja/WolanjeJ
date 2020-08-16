package com.example.wolanjej;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wolanjej.dialogs.AirtimeSuccess;
import com.example.wolanjej.dialogs.AirtimeUnsuccessful;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

public class EnterPin extends AppCompatActivity {
    private Button button;
    private String sessionID;
    private String phoneNumber;
    private String phoneName;
    private String amount;
    private String accNumber;
    private String sendIDReference;
    private String phoneProvider;
    private String message;
    private String sendAmount;
    private String myBalance;
    private String resultBalance;
    private String bankDetails;
    private EditText editText1, editText2;
    private SharedPreferences pref;
    private final String TAG = "EnterPin";
    private ProgressBar progressBar;


    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);

        //SharedPreferences values for login eg token
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        progressBar = (ProgressBar) findViewById(R.id.progressBarEnterPin);

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        switch (className) {
            case "BookBus06":

                break;
            case "TransferToWalletSingle37":

                this.phoneNumber = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENUMBER);
                this.amount = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_AMOUNT);
                this.phoneName = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENAME);

                break;
            case "TransferToPhone50":
                pref = getApplication().getSharedPreferences("ConfirmTransferToPhone52", MODE_PRIVATE);
                this.phoneNumber = pref.getString("phone", "");
                this.phoneName = pref.getString("phoneName", "");
                this.amount = pref.getString("amount", "");
                this.phoneProvider = pref.getString("phoneCompany", "");

                break;
            case "TransferToBank44":

                pref = getApplication().getSharedPreferences("ConfirmTransferToBank46", MODE_PRIVATE);
                this.accNumber = pref.getString("accNumber", "");
                this.phoneNumber = pref.getString("phone", "");
                this.phoneName = pref.getString("phoneName", "");
//            this.holderName = pref.getString("holderName", "");
                this.amount = pref.getString("amount", "");
                this.message = pref.getString("message", "");
                String sendBranch = pref.getString("branchName", "");
                String sendBank = pref.getString("bankSelected", "");
                this.bankDetails = sendBank + "-" + sendBranch;

//            this.phoneNumber = intentExtra.getStringExtra(TransferToBank44.EXTRA_PHONENUMBER);
//            this.sessionID = intentExtra.getStringExtra(TransferToBank44.EXTRA_SESSION);
//            this.amount = intentExtra.getStringExtra(TransferToBank44.EXTRA_AMOUNT);
//            this.phoneName = intentExtra.getStringExtra(TransferToBank44.EXTRA_PHONENAME);
//            this.accNumber = intentExtra.getStringExtra(TransferToBank44.EXTRA_ACCOUNTNUMBER);
//            String sendBank = intentExtra.getStringExtra(TransferToBank44.EXTRA_BANKSELECTED);
//            String sendBranch = intentExtra.getStringExtra(TransferToBank44.EXTRA_BRANCHNAME);
//            this.bankDetails = sendBank+"-"+sendBranch;

                break;
            case "TopUpOtherNumber":

                this.phoneNumber = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_PHONENUMBER);
                this.amount = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_AMOUNT);
                this.phoneName = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_PHONENAME);
                this.phoneProvider = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_PROVIDER);

                break;
            case "Top_up":

                this.phoneNumber = intentExtra.getStringExtra(Top_up.EXTRA_PHONENUMBER);
                this.amount = intentExtra.getStringExtra(Top_up.EXTRA_AMOUNT);
                this.phoneProvider = intentExtra.getStringExtra(Top_up.EXTRA_PROVIDER);

                break;
        }

        button = (Button) findViewById(R.id.confirm_pin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tvtext = findViewById(R.id.pinValue1);
                String pin1 = tvtext.getText().toString();

                tvtext = findViewById(R.id.pinValue2);
                String pin2 = tvtext.getText().toString();

                tvtext = findViewById(R.id.pinValue3);
                String pin3 = tvtext.getText().toString();

                tvtext = findViewById(R.id.pinValue4);
                String pin4 = tvtext.getText().toString();

                String fullPin = pin1 + pin2 + pin3 + pin4;
                if (fullPin != null) {
                    Intent intentExtra = getIntent();
                    String className = getIntent().getStringExtra("Class");
                    switch (className) {
                        case "BookBus06":
                            Intent intent = new Intent(getApplicationContext(), BookBus09.class);
                            startActivity(intent);
                            break;
                        case "TransferToWalletSingle37":
                            new Transfer(fullPin, "WALLET_XFER", phoneNumber).execute();
                            break;
                        case "TransferToPhone50":
                            switch (phoneProvider) {
                                //Case statements
                                case "safaricom":
                                    new Transfer(fullPin, "MPESA_B2C", phoneNumber).execute();
                                    Toast.makeText(getApplicationContext(), "Sending via MPESA", Toast.LENGTH_LONG).show();
                                    break;
                                case "airtel":
                                    new Transfer(fullPin, "AIRTEL_B2C", phoneNumber).execute();
                                    Toast.makeText(getApplicationContext(), "Sending via AIRTEL MONEY", Toast.LENGTH_LONG).show();
                                    break;
                                case "telkom":
                                    new Transfer(fullPin, "TKASH_B2C", phoneNumber).execute();
                                    Toast.makeText(getApplicationContext(), "Sending via TKASH", Toast.LENGTH_LONG).show();
                                    break;
                                //Default case statement
                                default:
                                    System.out.println("Not an airtel, safaricom or telkom");
                            }
                            break;
                        case "TransferToBank44":
                            new Transfer(fullPin, "BANK_XFER", accNumber).execute();
                            break;
                        case "TopUpOtherNumber":
                            switch (phoneProvider) {
                                //Case statements
                                case "safaricom":
                                    new Transfer(fullPin, "SAF_ATP", phoneNumber).execute();
                                    Toast.makeText(getApplicationContext(), "Top up via MPESA", Toast.LENGTH_LONG).show();
                                    break;
                                case "airtel":
                                    new Transfer(fullPin, "AIRTEL_ATP", phoneNumber).execute();
                                    Toast.makeText(getApplicationContext(), "Top up via AIRTEL MONEY", Toast.LENGTH_LONG).show();
                                    break;
                                case "telkom":
                                    new Transfer(fullPin, "TKASH_ATP", phoneNumber).execute();
                                    Toast.makeText(getApplicationContext(), "Top up via TKASH", Toast.LENGTH_LONG).show();
                                    break;
                                //Default case statement
                                default:
                                    System.out.println("Not an airtel, safaricom or telkom");
                            }
                            break;
                        case "Top_up":
                            switch (phoneProvider) {
                                //Case statements
                                case "safaricom":
                                    new Transfer(fullPin, "SAF_ATP", phoneNumber).execute();
                                    Toast.makeText(getApplicationContext(), "Top up via MPESA", Toast.LENGTH_LONG).show();
                                    break;
                                case "airtel":
                                    new Transfer(fullPin, "AIRTEL_ATP", phoneNumber).execute();
                                    Toast.makeText(getApplicationContext(), "Top up via AIRTEL MONEY", Toast.LENGTH_LONG).show();
                                    break;
                                case "telkom":
                                    new Transfer(fullPin, "TKASH_ATP", phoneNumber).execute();
                                    Toast.makeText(getApplicationContext(), "Top up via TKASH", Toast.LENGTH_LONG).show();
                                    break;
                                //Default case statement
                                default:
                                    System.out.println("Not an airtel, safaricom or telkom");
                            }
                            break;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter your Pin", Toast.LENGTH_LONG).show();
                }

            }
        });

        final EditText text1 = findViewById(R.id.pinValue1);
        final EditText text2 = findViewById(R.id.pinValue2);
        final EditText text3 = findViewById(R.id.pinValue3);
        final EditText text4 = findViewById(R.id.pinValue4);
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
                //text4.setFocusable(false);
                text4.setClickable(false);
//                Toast.makeText(EnterPin.this, ""+numbers[0]+""+numbers[1]+""+numbers[2]+""+numbers[3], Toast.LENGTH_SHORT).show();

            }
        });

    }

    public class Transfer extends AsyncTask<Void, Void, Response> {
        String pin;
        String productName;
        String refValue;

        public Transfer(String pin, String productName, String refValue) {
            this.pin = pin;
            this.productName = productName;
            this.refValue = refValue;
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
                jdata.put("ref", refValue);
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
            super.onPostExecute(response);
            progressBar.setVisibility(View.GONE);
            if (response.code() == 201) {
                try {
                    String value = response.body().string();
                    JSONObject jBody = new JSONObject(value); // adding
                    System.out.println(TAG + "Response body json values are service : " + value);
                    Log.e("TAG", String.valueOf(value));
                    sendAmount = jBody.getJSONArray("services").getJSONObject(0).getString("amount");
                    String sendfee = jBody.getJSONArray("services").getJSONObject(0).getString("fee");
                    String sendNumber = jBody.getJSONArray("services").getJSONObject(0).getString("ref");
                    sendIDReference = jBody.getJSONArray("services").getJSONObject(0).getString("id");
                    String statusResulsts = jBody.getJSONArray("services").getJSONObject(0).getString("status");
                    Log.e("TAG", sendAmount);

                    if (phoneNumber.equals(sendNumber) && statusResulsts.equals("TRX_ASYNC")) {
                        showPopup();
                    } else if (statusResulsts.equals("TRX_OK")) {
                        showPopup();
                    } else if (statusResulsts.equals("TRX_INSUFFICIENT_BALANCE")) {
                        Toast.makeText(getApplicationContext(), "You have insufficient balance on your wallet", Toast.LENGTH_LONG).show();
                        showPopupFail();
                    } else if (statusResulsts.equals("TRX_VERIFY")) {
                        Toast.makeText(getApplicationContext(), "the transaction is being verified", Toast.LENGTH_SHORT).show();
                        ShowDialogWalletFail();

                    } else {
                        Toast.makeText(getApplicationContext(), "You have insufficient balance", Toast.LENGTH_LONG).show();
                        showPopupFail();
                    }

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            } else if (response.code() != 201) {
                {
                    progressBar.setVisibility(View.GONE);
                    String statusResults = "unsuccessful";
                    try {
                        String result = response.body().string();
                        Log.e("TAG", String.valueOf(result));
//                Toast.makeText(EnterPin.this, "Please Try Again"+verifyResult, Toast.LENGTH_SHORT).show();
                        showPopupFail();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public class UserBalance extends AsyncTask<Void, Void, Response> {

        @Override
        protected Response doInBackground(Void... voids) {

            String url = "/api/balance";
            OkhttpConnection okConn = new OkhttpConnection(); // calling the okhttp connection class here
            Response result = okConn.getBalance(url, sessionID); // sending the url string and base 64 results to the okhttp connection and it's method is getLogin
            Log.d("TAG", String.valueOf(result));
            return result;
        }

        @Override
        protected void onPostExecute(Response result) {
            String verifyResult = null;
            if (result.code() == 200) {
                try {
                    String test = result.body().string();
                    Log.d("TAG test", test);
                    JSONObject JBalance = new JSONObject(test);
                    System.out.println("Response body json values are : " + JBalance);

                    resultBalance = JBalance.getJSONArray("balance").getJSONObject(0).getString("balance");


                    myBalance = "KSH " + resultBalance;

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            } else if (result.code() != 201) {
                try {
                    verifyResult = result.body().string();
                    JSONObject jBody = new JSONObject(verifyResult); // adding
                    System.out.println("Response body json values are : " + verifyResult);
                    Log.e("TAG", String.valueOf(verifyResult));
//                    String sendResutls = jBody.getJSONObject("errors").getJSONObject("otp").getJSONArray("otp").getJSONArray(0).getString(2);
//                    Log.e("TAG", String.valueOf(sendResutls));
//                    Toast.makeText(getApplicationContext(), "Phone Number, "+sendResutls, Toast.LENGTH_LONG).show();
                    Log.e("TAG result value", String.valueOf(result));
                    Log.e("TAG result body", verifyResult);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void showPopup() {
        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");

        new UserBalance().execute();

        switch (className) {
            case "TransferToWalletSingle37":
            case "Top_up":
            case "TopUpOtherNumber":
            case "TransferToBank44":
            case "TransferToPhone50": {
                ShowDialogSuccess();
                break;
            }
        }
    }

    public void showPopupFail() {
        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");

        switch (className) {
            case "TransferToWalletSingle37":
            case "TransferToPhone50":
            case "TransferToBank44":
            case "TopupOtherNumber":
            case "Top_up": {
                ShowDialogWalletFail();
                break;
            }
        }

    }

    private void ShowDialogSuccess() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.transfer_success_popup, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btn = view.findViewById(R.id.dismiss_success);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Class", "EnterPin");
                startActivity(intent);
                finish();
                alertDialog.dismiss();
            }
        });
        TextView textView = view.findViewById(R.id.refNumberSuccess);
        textView.setText(sendIDReference);
        TextView textView1 = view.findViewById(R.id.amountSentSuccess);
        textView1.setText(sendAmount);

        alertDialog.show();

    }

    private void ShowDialogWalletFail() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.transfer_unsuccessful_popup, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btn = view.findViewById(R.id.try_again_unsuccessful);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Class", "EnterPin");
                startActivity(intent);
                finish();
                alertDialog.dismiss();
            }
        });
        TextView textView = view.findViewById(R.id.amount_sent_note);
        TextView textView1 = view.findViewById(R.id.balance_note2);
        TextView textView2 = view.findViewById(R.id.reference_numberTra);
        textView2.setText(sendIDReference);
        textView.setText(sendAmount);

        alertDialog.show();
    }
}