package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

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
    private String phoneProvider;
    private String message;
    private String bankDetails;
    private EditText editText1, editText2;
    private SharedPreferences pref;

    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);

        //SharedPreferences values for login eg token
        pref=getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        if(className.equals("TransferToWalletSingle37")) {

            this.phoneNumber = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENUMBER);
            this.amount = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_AMOUNT);
            this.phoneName = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENAME);

        }else if (className.equals("TransferToPhone50")){
            pref=getApplication().getSharedPreferences("ConfirmTransferToPhone52", MODE_PRIVATE);
            this.phoneNumber =  pref.getString("phone", "");
            this.phoneName =  pref.getString("phoneName", "");
            this.amount =  pref.getString("amount", "");
            this.phoneProvider =  pref.getString("phoneCompany", "");

        }else if (className.equals("TransferToBank44")){

            this.phoneNumber = intentExtra.getStringExtra(TransferToBank44.EXTRA_PHONENUMBER);
            this.sessionID = intentExtra.getStringExtra(TransferToBank44.EXTRA_SESSION);
            this.amount = intentExtra.getStringExtra(TransferToBank44.EXTRA_AMOUNT);
            this.phoneName = intentExtra.getStringExtra(TransferToBank44.EXTRA_PHONENAME);
            this.accNumber = intentExtra.getStringExtra(TransferToBank44.EXTRA_ACCOUNTNUMBER);
            String sendBank = intentExtra.getStringExtra(TransferToBank44.EXTRA_BANKSELECTED);
            String sendBranch = intentExtra.getStringExtra(TransferToBank44.EXTRA_BRANCHNAME);
            this.bankDetails = sendBank+"-"+sendBranch;

        }else if (className.equals("TopUpOtherNumber")){

            this.phoneNumber = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_PHONENUMBER);
            this.amount = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_AMOUNT);
            this.phoneName = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_PHONENAME);
            this.phoneProvider = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_PROVIDER);

        }else if (className.equals("Top_up")){

            this.phoneNumber = intentExtra.getStringExtra(Top_up.EXTRA_PHONENUMBER);
            this.amount = intentExtra.getStringExtra(Top_up.EXTRA_AMOUNT);
            this.phoneProvider = intentExtra.getStringExtra(Top_up.EXTRA_PROVIDER);

        }

        button = (Button)findViewById(R.id.confirm_pin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tvtext =  findViewById(R.id.pinValue1);
                String pin1 = tvtext.getText().toString();

                tvtext =  findViewById(R.id.pinValue2);
                String pin2 = tvtext.getText().toString();

                tvtext =  findViewById(R.id.pinValue3);
                String pin3 = tvtext.getText().toString();

                tvtext =  findViewById(R.id.pinValue4);
                String pin4 = tvtext.getText().toString();

                String fullPin = pin1+pin2+pin3+pin4;
                if(fullPin!=null){
                    Intent intentExtra = getIntent();
                    String className = getIntent().getStringExtra("Class");
                    if (className.equals("TransferToWalletSingle37")){
                        Transfer(fullPin, "WALLET_XFER", phoneNumber);
                    }else if (className.equals("TransferToPhone50")){
                        switch(phoneProvider){
                            //Case statements
                            case "safaricom": Transfer(fullPin, "MPESA_B2C", phoneNumber);
                                Toast.makeText(getApplicationContext(), "Sending via MPESA", Toast.LENGTH_LONG).show();
                                break;
                            case "airtel": Transfer(fullPin, "AIRTEL_B2C", phoneNumber);
                                Toast.makeText(getApplicationContext(), "Sending via AIRTEL MONEY", Toast.LENGTH_LONG).show();
                                break;
                            case "telkom": Transfer(fullPin, "TKASH_B2C", phoneNumber);
                                Toast.makeText(getApplicationContext(), "Sending via TKASH", Toast.LENGTH_LONG).show();
                                break;
                            //Default case statement
                            default:System.out.println("Not an airtel, safaricom or telkom");
                        }
                    }else if (className.equals("TransferToBank44")){
                        Transfer(fullPin, "BANK_XFER", accNumber);
                    }else if (className.equals("TopUpOtherNumber")){
                        switch(phoneProvider){
                            //Case statements
                            case "safaricom": Transfer(fullPin, "SAF_ATP", phoneNumber);
                                Toast.makeText(getApplicationContext(), "Top up via MPESA", Toast.LENGTH_LONG).show();
                                break;
                            case "airtel": Transfer(fullPin, "AIRTEL_ATP", phoneNumber);
                                Toast.makeText(getApplicationContext(), "Top up via AIRTEL MONEY", Toast.LENGTH_LONG).show();
                                break;
                            case "telkom": Transfer(fullPin, "TKASH_ATP", phoneNumber);
                                Toast.makeText(getApplicationContext(), "Top up via TKASH", Toast.LENGTH_LONG).show();
                                break;
                            //Default case statement
                            default:System.out.println("Not an airtel, safaricom or telkom");
                        }
                    }else if (className.equals("Top_up")){
                        switch(phoneProvider){
                            //Case statements
                            case "safaricom": Transfer(fullPin, "SAF_ATP", phoneNumber);
                                Toast.makeText(getApplicationContext(), "Top up via MPESA", Toast.LENGTH_LONG).show();
                                break;
                            case "airtel": Transfer(fullPin, "AIRTEL_ATP", phoneNumber);
                                Toast.makeText(getApplicationContext(), "Top up via AIRTEL MONEY", Toast.LENGTH_LONG).show();
                                break;
                            case "telkom": Transfer(fullPin, "TKASH_ATP", phoneNumber);
                                Toast.makeText(getApplicationContext(), "Top up via TKASH", Toast.LENGTH_LONG).show();
                                break;
                            //Default case statement
                            default:System.out.println("Not an airtel, safaricom or telkom");
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Please Enter your Pin", Toast.LENGTH_LONG).show();
                }

            }
        });

        final EditText text1 = findViewById(R.id.pinValue1);
        final EditText text2 =findViewById(R.id.pinValue2);
        final EditText text3 = findViewById(R.id.pinValue3);
        final EditText text4 = findViewById(R.id.pinValue4);
        final String [] numbers = new String[4];

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


                numbers[1] =s.toString();
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

                numbers[2]=s.toString();
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

                numbers[3]=s.toString();
                //text4.setFocusable(false);
                text4.setClickable(false);
//                Toast.makeText(EnterPin.this, ""+numbers[0]+""+numbers[1]+""+numbers[2]+""+numbers[3], Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void movetoSuccess() {
        String className = getIntent().getStringExtra("Class");
        if (className.equals("TopUpOtherNumber")){
            Intent move = new Intent(this, Home.class);
            startActivity(move);
        }else if (className.equals("Top_up")){
            Intent move = new Intent(this, Home.class);
            startActivity(move);
        }else{
            Intent move = new Intent(this, MainTransfer36.class);
            startActivity(move);
        }

    }

    public void Transfer(String pin, String productName, String refValue){
        String verifyResult;

        JSONArray jdataset = new JSONArray();
        JSONObject jdata = new JSONObject();
        try {
            jdata.put("product_name", productName);
            jdata.put("amount", amount);
            jdata.put("phone", phoneNumber);
            jdata.put("ref", refValue);
            jdata.put("pin", pin);
            jdataset.put(jdata);
        } catch (JSONException e) {
            e.printStackTrace();
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
        if (result.code() == 201) {
            try {
                String value = result.body().string();
                verifyResult = value;
                JSONObject jBody = new JSONObject(value); // adding
                System.out.println("Response body json values are : " + verifyResult);
                Log.e("TAG", String.valueOf(verifyResult));
                String sendAmount = jBody.getJSONArray("services").getJSONObject(0).getString("amount");
                String sendfee = jBody.getJSONArray("services").getJSONObject(0).getString("fee");
                String sendNumber = jBody.getJSONArray("services").getJSONObject(0).getString("ref");
                String sendIDReference = jBody.getJSONArray("services").getJSONObject(0).getString("id");
                String  statusResulsts =  jBody.getJSONArray("services").getJSONObject(0).getString("status");
                Log.e("TAG", sendAmount);

                if (phoneNumber.equals(sendNumber) && statusResulsts.equals("TRX_ASYNC")) {
                    showPopup(sendAmount, sendfee, sendIDReference);
                }else if (statusResulsts.equals("TRX_OK")){
                    showPopup(sendAmount, sendfee, sendIDReference);
                }else if (statusResulsts.equals("TRX_INSUFFICIENT_BALANCE")){
                    Toast.makeText(getApplicationContext(), "You have insufficient balance on your wallet", Toast.LENGTH_LONG).show();
                    showPopupFail();
                }else{
                    Toast.makeText(getApplicationContext(), "You have insufficient balance", Toast.LENGTH_LONG).show();
                    showPopupFail();
                }

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        }else if(result.code() != 201) {
            String statusResults = "unsuccessful";
            try {
                verifyResult = result.body().string();
                Log.e("TAG", String.valueOf(result));
//                Toast.makeText(EnterPin.this, "Please Try Again"+verifyResult, Toast.LENGTH_SHORT).show();
                showPopupFail();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void showPopup(String sendAmount, String sendfee, String sendIDReference) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.toast_popup, (ViewGroup) findViewById(R.id.toast_popup_layout), false);

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        if (className.equals("TransferToWalletSingle37")){
            ((TextView)popupWindow.getContentView().findViewById(R.id.refncNumber)).setText(sendIDReference);
            ((TextView)popupWindow.getContentView().findViewById(R.id.refFee)).setText(sendfee);
            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText(sendAmount);
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText(phoneName);
            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Done!");
            ((ImageView) popupWindow.getContentView().findViewById(R.id.imageTrasfer)).setImageResource(R.mipmap.button_rounded);
        }else if (className.equals("TransferToPhone50")){
            ((TextView)popupWindow.getContentView().findViewById(R.id.refFee)).setText(sendfee);
            ((TextView)popupWindow.getContentView().findViewById(R.id.transBalance)).setText("0");
            ((TextView)popupWindow.getContentView().findViewById(R.id.refncNumber)).setText(sendIDReference);
            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText(sendAmount);
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText(phoneName);
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText("+"+phoneNumber);
            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Done!");
            ((ImageView) popupWindow.getContentView().findViewById(R.id.imageTrasfer)).setImageResource(R.mipmap.button_rounded);
        }else if(className.equals("TransferToBank44")){
            ((TextView)popupWindow.getContentView().findViewById(R.id.refFee)).setText(sendfee);
            ((TextView)popupWindow.getContentView().findViewById(R.id.transBalance)).setText("0");
            ((TextView)popupWindow.getContentView().findViewById(R.id.refncNumber)).setText(sendIDReference);
            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText(sendAmount);
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText(phoneName);
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText(bankDetails);
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpBankName)).setText(accNumber);
            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Done!");
            ((ImageView) popupWindow.getContentView().findViewById(R.id.imageTrasfer)).setImageResource(R.mipmap.button_rounded);
        }else if (className.equals("TopUpOtherNumber")){
            ((TextView)popupWindow.getContentView().findViewById(R.id.refFee)).setText(sendfee);
            ((TextView)popupWindow.getContentView().findViewById(R.id.transBalance)).setText("0");
            ((TextView)popupWindow.getContentView().findViewById(R.id.refncNumber)).setText(sendIDReference);
            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText(sendAmount);
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText(phoneName);
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText("+"+phoneNumber);
            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Airtime Topped Up!");
            ((ImageView) popupWindow.getContentView().findViewById(R.id.imageTrasfer)).setImageResource(R.mipmap.button_rounded);
        }else if (className.equals("Top_up")){
            ((TextView)popupWindow.getContentView().findViewById(R.id.refFee)).setText(sendfee);
            ((TextView)popupWindow.getContentView().findViewById(R.id.transBalance)).setText("0");
            ((TextView)popupWindow.getContentView().findViewById(R.id.refncNumber)).setText(sendIDReference);
            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText(sendAmount);
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText("My Number");
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText("+"+phoneNumber);
            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Airtime Topped Up!");
            ((ImageView) popupWindow.getContentView().findViewById(R.id.imageTrasfer)).setImageResource(R.mipmap.button_rounded);
        }

        ((Button)popupView.findViewById(R.id.dismiss_success)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                movetoSuccess();
            }
        });

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }

    public void showPopupFail() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.toast_popup, (ViewGroup) findViewById(R.id.toast_popup_layout), false);

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        if (className.equals("TransferToWalletSingle37")){
            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText("0.00");
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText("*******");
            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Unsuccessful");
            ((Button)popupView.findViewById(R.id.dismiss_success)).setText("TRY AGAIN");
           ((ImageView) popupWindow.getContentView().findViewById(R.id.imageTrasfer)).setImageResource(R.mipmap.button_rounded_2);
        }else if (className.equals("TransferToPhone50")){
            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText("0.00");
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText("*******");
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText("*******");
            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Unsuccessful");
            ((Button)popupView.findViewById(R.id.dismiss_success)).setText("TRY AGAIN");
            ((ImageView) popupWindow.getContentView().findViewById(R.id.imageTrasfer)).setImageResource(R.mipmap.button_rounded_2);
        }else if(className.equals("TransferToBank44")){
            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText("0.00");
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText("*******");
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText("*******");
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpBankName)).setText("*******");
            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Unsuccessful");
            ((Button)popupView.findViewById(R.id.dismiss_success)).setText("TRY AGAIN");
            ((ImageView) popupWindow.getContentView().findViewById(R.id.imageTrasfer)).setImageResource(R.mipmap.button_rounded_2);
        }else if (className.equals("TopupOtherNumber")){
            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText("0.00");
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText("*******");
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText("*******");
            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Unsuccessful");
            ((Button)popupView.findViewById(R.id.dismiss_success)).setText("TRY AGAIN");
            ((ImageView) popupWindow.getContentView().findViewById(R.id.imageTrasfer)).setImageResource(R.mipmap.button_rounded_2);
        }else if (className.equals("Top_up")){
            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText("0.00");
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText("*******");
            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Unsuccessful");
            ((Button)popupView.findViewById(R.id.dismiss_success)).setText("TRY AGAIN");
            ((ImageView) popupWindow.getContentView().findViewById(R.id.imageTrasfer)).setImageResource(R.mipmap.button_rounded_2);
        }

        ((Button)popupView.findViewById(R.id.dismiss_success)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                movetoSuccess();
            }
        });

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }
}