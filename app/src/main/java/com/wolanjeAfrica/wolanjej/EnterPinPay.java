package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class EnterPinPay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin_pay);
    }

    public void payEnter(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.payment_successfull, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}