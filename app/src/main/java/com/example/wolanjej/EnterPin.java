package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private String phoneProvider;
    private String message;
    private EditText editText1, editText2;

    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        if(className.equals("TransferToWalletSingle37")) {

            this.phoneNumber = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENUMBER);
            this.sessionID = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_SESSION);
            this.amount = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_AMOUNT);
            this.phoneName = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENAME);

        }else if (className.equals("TransferToPhone50")){

            this.phoneNumber = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_PHONENUMBER);
            this.sessionID = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_SESSION);
            this.amount = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_AMOUNT);
            this.phoneName = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_PHONENAME);
            this.phoneProvider = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_PROVIDER);

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
                        Transfer(fullPin, "WALLET_XFER");
                    }else if (className.equals("TransferToPhone50")){
                        switch(phoneProvider){
                            //Case statements
                            case "safaricom": Transfer(fullPin, "MPESA_B2C");
                                Toast.makeText(getApplicationContext(), "Sending via MPESA", Toast.LENGTH_LONG).show();
                                break;
                            case "airtel": Transfer(fullPin, "AIRTEL_B2C");
                                Toast.makeText(getApplicationContext(), "Sending via AIRTEL MONEY", Toast.LENGTH_LONG).show();
                                break;
                            case "telkom": Transfer(fullPin, "TKASH_B2C");
                                Toast.makeText(getApplicationContext(), "Sending via TKASH", Toast.LENGTH_LONG).show();
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
    }

    public void movetoSuccess() {
        Intent move = new Intent(this, MainTransfer36.class);
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra("Class","EnterPin");
        startActivity(move);
    }

    public void Transfer(String pin, String productName){
        String verifyResult;

        JSONArray jdataset = new JSONArray();
        JSONObject jdata = new JSONObject();
        try {
            jdata.put("product_name", productName);
            jdata.put("amount", amount);
            jdata.put("phone", phoneNumber);
            jdata.put("ref", phoneNumber);
            jdata.put("pin", pin);
            jdataset.put(jdata);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jMpesa = new JSONObject();
        try {
            jMpesa.put("ac_uname", "test");
            jMpesa.put("sched", jdataset);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("TAG", String.valueOf(jMpesa));
        Log.e("TAG session on phon50", sessionID);

        String url = "/api/transactions";
        OkhttpConnection okConn = new OkhttpConnection();
        Response result = okConn.postValue(url, jMpesa.toString(),sessionID);
        if (result.code() == 201) {
            try {

                String value = result.body().string();
                verifyResult = value;
                JSONObject jBody = new JSONObject(value); // adding
                System.out.println("Response body json values are : " + verifyResult);
                Log.e("TAG", String.valueOf(verifyResult));
                String sendAmount = jBody.getJSONArray("services").getJSONObject(0).getString("amount");
                Log.e("TAG", sendAmount);
////                //bypass the verification code and page for now since we are adding otp for testing
//                Intent move = new Intent(this, Registration07.class);
//                move.putExtra(EXTRA_SESSION, sessionID);
//                move.putExtra(EXTRA_JSONBODY, verifyResult);
//                startActivity(move);
                showPopup(sendAmount);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        }else if(result.code() != 201) {
            try {
                verifyResult = result.body().string();
                Log.e("TAG", String.valueOf(result));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showPopup(String sendAmount) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.transfer_success_popup, (ViewGroup) findViewById(R.id.popup_element), false);

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        if (className.equals("TransferToWalletSingle37")){
            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText(sendAmount);
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText(phoneName);
        }else if (className.equals("TransferToPhone50")){
            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText(sendAmount);
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText(phoneName);
            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText(phoneNumber);
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