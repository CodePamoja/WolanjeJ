package com.example.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class GettingStarted extends AppCompatActivity {
    private TextView textView, textView1, textView2, textView3, textView4, textView5;
    private ImageButton imageButton1, imageButton2, imageButton3, imageButton4, imageButton5, imageButton6, imageButton7, imageButton8, imageButton9, imageButton10, imageButton11, imageButton12;
    private Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);
        textView = (TextView) this.findViewById(R.id.txtDetailsLogin);
        textView1 = (TextView) this.findViewById(R.id.txtDetailsChangeAccountSettings);
        textView2 = (TextView) this.findViewById(R.id.txtDetailsResetting_my_password);
        textView3 = (TextView) this.findViewById(R.id.txtDetailsPaymentOptions);
        textView4 = (TextView) this.findViewById(R.id.txtDetailsReceiving_notifications);
        textView5 = (TextView) this.findViewById(R.id.txtDetailsLoyaltyProgram);
        imageButton1 = findViewById(R.id.imgBtnGettingStarted);
        imageButton2 = findViewById(R.id.imgBtnUpGettingStarted);
        imageButton3 = findViewById(R.id.imgBtnChangeAccountSet);
        imageButton4 = findViewById(R.id.imgBtnUpChangeAccountSet);
        imageButton5 = findViewById(R.id.imgBtnResetting_my_password);
        imageButton6 = findViewById(R.id.imgBtnUpResetting_my_password);
        imageButton7 = findViewById(R.id.imgBtnPaymentOptions);
        imageButton8 = findViewById(R.id.imgBtnUpPaymentOptions);
        imageButton9 = findViewById(R.id.imgBtnReceiving_notifications);
        imageButton10 = findViewById(R.id.imgBtnUpReceiving_notifications);
        imageButton11 = findViewById(R.id.imgBtnLoyaltyProgram);
        imageButton12 = findViewById(R.id.imgBtnUpLoyaltyProgram);
        setToolBar(tb);

    }
    private void setToolBar(Toolbar tb) {
        tb = findViewById(R.id.toolbarGettingStarted);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this, NeedSupport.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }

    public void CloseLoginGettingStarted(View view) {
        imageButton2.setVisibility(View.GONE);
        imageButton1.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
    }

    public void OpenLoginGettingStarted(View view) {
        imageButton2.setVisibility(View.VISIBLE);
        imageButton1.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
    }

    public void OpenLoginChangeAccountSettings(View view) {
        imageButton4.setVisibility(View.VISIBLE);
        imageButton3.setVisibility(View.GONE);
        textView1.setVisibility(View.VISIBLE);

    }

    public void CloseChangeAccountSettings(View view) {
        imageButton4.setVisibility(View.GONE);
        imageButton3.setVisibility(View.VISIBLE);
        textView1.setVisibility(View.GONE);
    }

    public void OpenResttingPassword(View view) {
        imageButton6.setVisibility(View.VISIBLE);
        imageButton5.setVisibility(View.GONE);
        textView2.setVisibility(View.VISIBLE);
    }

    public void CloseResettingPassword(View view) {
        imageButton6.setVisibility(View.GONE);
        imageButton5.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.GONE);
    }

    public void OpenPaymentOptions(View view) {
        imageButton8.setVisibility(View.VISIBLE);
        imageButton7.setVisibility(View.GONE);
        textView3.setVisibility(View.VISIBLE);
    }

    public void ClosePaymentOptions(View view) {
        imageButton7.setVisibility(View.VISIBLE);
        imageButton8.setVisibility(View.GONE);
        textView3.setVisibility(View.GONE);
    }

    public void OpenReceivingNotifications(View view) {
        imageButton10.setVisibility(View.VISIBLE);
        imageButton9.setVisibility(View.GONE);
        textView4.setVisibility(View.VISIBLE);
    }

    public void CloseReceivingNotifications(View view) {
        imageButton9.setVisibility(View.VISIBLE);
        imageButton10.setVisibility(View.GONE);
        textView4.setVisibility(View.GONE);
    }

    public void OpenLoyaltyPrograms(View view) {
        imageButton12.setVisibility(View.VISIBLE);
        imageButton11.setVisibility(View.GONE);
        textView5.setVisibility(View.VISIBLE);
    }

    public void CloseLoyaltyPrograms(View view) {
        imageButton11.setVisibility(View.VISIBLE);
        imageButton12.setVisibility(View.GONE);
        textView5.setVisibility(View.GONE);

    }
}