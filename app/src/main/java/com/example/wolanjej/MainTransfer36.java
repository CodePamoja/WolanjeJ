package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainTransfer36 extends AppCompatActivity {

    private CardView walletCard, bankCard, phoneCard;
    private String sessionID;
    private String AGENTNO;
    private String sendAmount;
    private String sendfee;
    private String sendNumber;
    private String sendIDReference;
    private String statusResults;
    private String sendName;


    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transfer36);
        setToolBar();

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");

        Log.e("class Type className", className);
        if(className.equals("Home")) {
            this.sessionID = intentExtra.getStringExtra(Home.EXTRA_SESSION);
            this.AGENTNO = intentExtra.getStringExtra(Home.EXTRA_AGENTNO);
        }else if (className.equals("EnterPin")){
            this.sessionID = intentExtra.getStringExtra(EnterPin.EXTRA_SESSION);
            this.AGENTNO = intentExtra.getStringExtra(EnterPin.EXTRA_AGENTNO);
//            this.sendAmount = getIntent().getStringExtra("sendAmount");
//            this.sendfee = getIntent().getStringExtra("sendfee");
//            this.sendIDReference = getIntent().getStringExtra("sendIDReference");
//            this.sendNumber = getIntent().getStringExtra("sendNumber");
//            this.statusResults = getIntent().getStringExtra("statusResults");
//            this.sendName = getIntent().getStringExtra("sendName");
//            findViewById(R.id.mainTrasfere36).post(new Runnable() {
//                public void run() {
//                    callPopup();
//                }
//            });

        }else if (className.equals("TransferToPhone50")){
            this.sessionID = intentExtra.getStringExtra(TransferToPhone50.EXTRA_SESSION);
            this.AGENTNO = intentExtra.getStringExtra(TransferToPhone50.EXTRA_AGENTNO);
        }else if (className.equals("TransferToWalletSingle37")){
            this.sessionID = intentExtra.getStringExtra(TransferToWalletSingle37.EXTRA_SESSION);
            this.AGENTNO = intentExtra.getStringExtra(TransferToWalletSingle37.EXTRA_AGENTNO);
        }else if (className.equals("TransferToWalletMultiple40")){
            this.sessionID = intentExtra.getStringExtra(TransferToWalletMultiple40.EXTRA_SESSION);
            this.AGENTNO = intentExtra.getStringExtra(TransferToWalletMultiple40.EXTRA_AGENTNO);
        }else if (className.equals("TransferToBank44")){
            this.sessionID = intentExtra.getStringExtra(TransferToBank44.EXTRA_SESSION);
            this.AGENTNO = intentExtra.getStringExtra(TransferToBank44.EXTRA_AGENTNO);
        }

        walletCard = (CardView)findViewById(R.id.transfer_to_wallet);
        walletCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("sessionID to wallet",sessionID);
                toWallet();
            }
        });

        bankCard = (CardView)findViewById(R.id.transfer_to_bank);
        bankCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("sessionID to bank",sessionID);
                toBank();
            }
        });

        phoneCard = (CardView)findViewById(R.id.transfer_to_phone);
        phoneCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("sessionID to mpesa",sessionID);
                toPhone();
            }
        });
    }

    public void toWallet() {
        Intent move = new Intent(this, TransferToWalletSingle37.class);
        move.putExtra("Class","MainTransfer36");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        startActivity(move);
    }

    public void toBank() {
        Intent move = new Intent(this, TransferToBank44.class);
        move.putExtra("Class","MainTransfer36");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        startActivity(move);
    }

    public void toPhone() {
        Log.e("sessionID to mpesa",sessionID);
        Intent move = new Intent(this,TransferToPhone50.class);
        move.putExtra("Class","MainTransfer36");
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        move.putExtra(EXTRA_SESSION, sessionID);
        startActivity(move);
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this,Home.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movetoLogo.putExtra("Class","MainTransfer36");
                        movetoLogo.putExtra(EXTRA_SESSION, sessionID);
                        movetoLogo.putExtra(EXTRA_AGENTNO, AGENTNO);
                        startActivity(movetoLogo);
                    }
                }
        );

    }

//    private void callPopup(){
//        if(statusResults.equals("successful")){
//            showPopup(sendAmount, sendfee, sendNumber, sendIDReference, sendName);
//        }else if(statusResults.equals("unsuccessful")){
//            showPopupFail();
//        }
//    }

//    public void showPopup(String sendAmount,  String sendfee, String sendNumber, String sendIDReference, String sendName) {
//        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.toast_popup, (ViewGroup) findViewById(R.id.toast_popup_layout), false);
//
//        Intent intentExtra = getIntent();
//        String className = getIntent().getStringExtra("Class");
//
//        // create the popup window
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//        if (className.equals("TransferToWalletSingle37")){
//            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText(sendAmount);
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText(sendName);
//            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Done!");
//        }else if (className.equals("TransferToPhone50")){
//            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText(sendAmount);
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText(sendName);
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText("+"+sendNumber);
//            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Done!");
//            Log.e("sendName",sendName);
//            Log.e("sendAmount",sendAmount);
//            Log.e("sendNumber",sendNumber);
//        }else if(className.equals("TransferToBank44")){
//            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText(sendAmount);
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText(sendName);
////            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText(bankDetails);
////            ((TextView)popupWindow.getContentView().findViewById(R.id.recpBankName)).setText(accNumber);
//            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Done!");
//        }else if (className.equals("TopupOtherNumber")){
//            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText(sendAmount);
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText(sendName);
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText("+"+sendNumber);
//            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Airtime Topped Up!");
//        }else if (className.equals("Top_up")){
//            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText(sendAmount);
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText("My Number");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText("+"+sendNumber);
//            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Airtime Topped Up!");
//        }
//
//        ((Button)popupView.findViewById(R.id.dismiss_success)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//
//        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
//
//
//    }
//
//    public void showPopupFail() {
//        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        final View popupView = inflater.inflate(R.layout.toast_popup, (ViewGroup) findViewById(R.id.toast_popup_layout), false);
//
//        Intent intentExtra = getIntent();
//        String className = getIntent().getStringExtra("Class");
//
//        // create the popup window
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//        if (className.equals("TransferToWalletSingle37")){
//            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText("0.00");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText("*******");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Unsuccessful");
//            ((Button)popupView.findViewById(R.id.dismiss_success)).setText("TRY AGAIN");
////            ((ImageView)popupView.findViewById(R.id.imageTrasfer)).set;
//        }else if (className.equals("TransferToPhone50")){
//            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText("0.00");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText("*******");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText("*******");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Unsuccessful");
//            ((Button)popupView.findViewById(R.id.dismiss_success)).setText("TRY AGAIN");
////            ((ImageView)popupView.findViewById(R.id.imageTrasfer)).setImageResource(Integer.parseInt("@mipmap/button_rounded_2"));
//        }else if(className.equals("TransferToBank44")){
//            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText("0.00");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText("*******");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText("*******");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpBankName)).setText("*******");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Unsuccessful");
//            ((Button)popupView.findViewById(R.id.dismiss_success)).setText("TRY AGAIN");
////            ((ImageView)popupView.findViewById(R.id.imageTrasfer)).setImageResource(Integer.parseInt("@mipmap/button_rounded_2"));
//        }else if (className.equals("TopupOtherNumber")){
//            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText("0.00");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText("*******");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText("*******");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Unsuccessful");
//            ((Button)popupView.findViewById(R.id.dismiss_success)).setText("TRY AGAIN");
//        }else if (className.equals("Top_up")){
//            ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText("0.00");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText("*******");
//            ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Unsuccessful");
//            ((Button)popupView.findViewById(R.id.dismiss_success)).setText("TRY AGAIN");
////            ((ImageView)popupView.findViewById(R.id.imageTrasfer)).set;
//        }
//
//        ((Button)popupView.findViewById(R.id.dismiss_success)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//
//            }
//        });
//
//        findViewById(R.id.mainTrasfere36).post(new Runnable() {
//            public void run() {
//                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
//            }
//        });
//    }
}