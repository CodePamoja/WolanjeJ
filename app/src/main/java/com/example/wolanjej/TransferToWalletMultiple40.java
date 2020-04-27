package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TransferToWalletMultiple40 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] selectUser = {"Multiple Users", "Single User"};

    private Button confirmButton;
    private String sessionID;
    private String AGENTNO;
    private EditText text;
    private Spinner spin;

    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_wallet_multiple40);
        setToolBar();

        // Get the Intent that started this activity and extract the string
        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        if(className.equals("TransferToWalletSingle37")) {
            this.sessionID = intentExtra.getStringExtra(TransferToWalletSingle37.EXTRA_SESSION);
            this.AGENTNO = intentExtra.getStringExtra(TransferToWalletSingle37.EXTRA_AGENTNO);
        }

        spin = (Spinner) this.findViewById(R.id.select_multiple);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,selectUser);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        confirmButton = (Button)findViewById(R.id.continue_multiple_transfer);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetoConfirm();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),selectUser[position] , Toast.LENGTH_LONG).show();
        if ("Single User".equals(selectUser[position])){
            Log.e("session before contact", sessionID);
            Intent move = new Intent(this, TransferToWalletSingle37.class);
            move.putExtra("Class","TransferToWalletMultiple40");
            move.putExtra(EXTRA_SESSION, sessionID);
            move.putExtra(EXTRA_AGENTNO, AGENTNO);
            startActivity(move);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, MainTransfer36.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("set toolbar sess", sessionID);
                        movetoLogo.putExtra("Class","TransferToWalletMultiple40");
                        movetoLogo.putExtra(EXTRA_SESSION, sessionID);
                        movetoLogo.putExtra(EXTRA_AGENTNO, AGENTNO);
                        startActivity(movetoLogo);
                    }
                }
        );
    }

    public void movetoConfirm() {
        Intent move = new Intent(this, ConfirmMultipleTransfer42.class);
        startActivity(move);
    }
}