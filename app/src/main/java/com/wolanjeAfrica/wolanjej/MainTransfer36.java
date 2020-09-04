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

import com.wolanjeAfrica.wolanjej.RealmDataBase.DbMigrations;
import com.wolanjeAfrica.wolanjej.RealmDataBase.User;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainTransfer36 extends AppCompatActivity {

    private CardView walletCard, bankCard, phoneCard;
    private String userId;
    private SharedPreferences pref;
    private String UserRole;
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transfer36);

        setToolBar();
        setActionBarColor();
        Realm.init(this);

        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.userId = pref.getString("userDbId", "");

        Realm realm = Realm.getInstance(DbMigrations.getDefaultInstance());
        UserRole = realm.where(User.class)
                .equalTo("id", Integer.valueOf(userId) )
                .findFirst().getRole();


        walletCard = (CardView) findViewById(R.id.transfer_to_wallet);
        walletCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toWallet();
            }
        });

        bankCard = (CardView) findViewById(R.id.transfer_to_bank);
        bankCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBank();
            }
        });

        phoneCard = (CardView) findViewById(R.id.transfer_to_phone);
        phoneCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPhone();
            }
        });
    }


    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (UserRole.equals("1")){
                            Intent movetohm2 = new Intent(getApplicationContext(), HomeTwo.class);
                            movetohm2.putExtra("Class", "MainTransfer36");
                            startActivity(movetohm2);
                        }else{
                            Intent movetoLogo = new Intent(getApplicationContext(), Home.class);
                            movetoLogo.putExtra("Class", "MainTransfer36");
                            startActivity(movetoLogo);
                        }
                    }
                }
        );

    }

    private void setActionBarColor() {
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }


    public void toWallet() {
        Intent move = new Intent(this, TransferToWalletSingle37.class);
        move.putExtra("Class", "MainTransfer36");
        startActivity(move);
    }

    public void toBank() {
        Intent move = new Intent(this, TransferToBank44.class);
        move.putExtra("Class", "MainTransfer36");
        startActivity(move);
    }

    public void toPhone() {
//        Log.e("sessionID to mpesa",sessionID);
        Intent move = new Intent(this, TransferToPhone50.class);
        move.putExtra("Class", "MainTransfer36");
        startActivity(move);
    }

}