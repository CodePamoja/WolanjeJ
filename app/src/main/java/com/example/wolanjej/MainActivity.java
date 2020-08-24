package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    boolean connected = false;
    private SharedPreferences pref;
    private String sessionID;
    private String AGENTNO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //setToolBar();
        if (Build.VERSION.SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        //SharedPreferences values for login eg token, user registered number
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", null);
        this.AGENTNO = pref.getString("agentno", "");
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (sessionID != null) {
//            Intent intent = new Intent(getApplicationContext(), Home.class);
//            startActivity(intent);
//        }
//        finish();
//    }

    // Do not change here because it checks if your are connected to the internet first
    public void movetoRegistration(final View view) {

        if (connected) {
            Intent move1 = new Intent(this, LogIn.class);
            startActivity(move1);
            finish();
        } else {

            new MaterialAlertDialogBuilder(getApplicationContext())

                    .setTitle("Network Error")
                    .setMessage("Turn data on to continue")
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    })
                    .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            movetoRegistration(view);
                        }
                    })

                    .show();
        }


    }
}