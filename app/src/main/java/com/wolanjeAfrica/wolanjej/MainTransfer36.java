package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class MainTransfer36 extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_PARENTCLASSNAME = "com.example.wolanjej.PARENTCLASSNAME";
    private static final String TAG = "MainTransfer";
    private CardView walletCard, bankCard, phoneCard;
    private String userId;
    private SharedPreferences pref;
    private static String ClassName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transfer36);


        ClassName = getIntent().getStringExtra("Class"); //get previous class


        walletCard = (CardView) findViewById(R.id.transfer_to_wallet);
        walletCard.setOnClickListener(this);
        bankCard = (CardView) findViewById(R.id.transfer_to_bank);
        bankCard.setOnClickListener(this);

        phoneCard = (CardView) findViewById(R.id.transfer_to_phone);
        phoneCard.setOnClickListener(this);
        setToolBar();
    }


    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        tb.setNavigationOnClickListener(
                v -> {
                    if (ClassName.equals("HomeTwo")) {
                        Intent movetohm2 = new Intent(getApplicationContext(), HomeTwo.class);
                        movetohm2.putExtra("Class", "MainTransfer36");
                        movetohm2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(movetohm2);
                    } else {
                        Intent movetoLogo = new Intent(getApplicationContext(), Home.class);
                        movetoLogo.putExtra("Class", "MainTransfer36");
                        movetoLogo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(movetoLogo);
                    }
                }
        );
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }


    @Override
    public void onClick(View v) {
        Intent move;
        switch (v.getId()) {
            case R.id.transfer_to_wallet:
                move = new Intent(this, TransferToWalletSingle37.class);
                move.putExtra("Class", "MainTransfer36");
                move.putExtra(EXTRA_PARENTCLASSNAME, ClassName);
                move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
                break;
            case R.id.transfer_to_bank:
                move = new Intent(this, TransferToBank44.class);
                move.putExtra("Class", "MainTransfer36");
                move.putExtra(EXTRA_PARENTCLASSNAME, ClassName);
                move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
                break;
            case R.id.transfer_to_phone:
                move = new Intent(this, TransferToPhone50.class);
                move.putExtra("Class", "MainTransfer36");
                move.putExtra(EXTRA_PARENTCLASSNAME, ClassName);
                move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
                break;
            default:
                break;
        }
    }
}