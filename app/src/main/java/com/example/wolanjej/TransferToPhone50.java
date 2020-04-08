package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TransferToPhone50 extends AppCompatActivity {
    private Button button;
    private String phoneNumber = "phone1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_phone50);
        setToolBar();

        button = (Button)findViewById(R.id.continue_phone_transfer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetoConfirm();
            }
        });

        SelectUserAdapter intent = new SelectUserAdapter();
        this.phoneNumber = intent.getEXTRANumber();
        if(phoneNumber!="phone1") {
            Intent move = getIntent();
            String CheckphoneNumber = move.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
//            Log.e("phone number", phoneNumber);
//            Toast.makeText(getApplicationContext(), CheckphoneNumber, Toast.LENGTH_LONG).show();
            EditText tvtext =  findViewById(R.id.transPhoneAmout);
            tvtext.setText(CheckphoneNumber);
        }
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this,MainTransfer36.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );
    }

    public void movetoConfirm() {
        Intent move = new Intent(this, ConfirmTransferToPhone52.class);
        startActivity(move);
    }

    public void moveToContact(View view) {
        String cont = "get all user contacts contact button preseed";
        Log.e("Move to ", cont);
        Intent move = new Intent(this, ContactsView.class);
        startActivity(move);
    }
}