package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LogIn extends AppCompatActivity {

    private ImageView imageView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_log_in);
        final Executor executor = Executors.newSingleThreadExecutor();
        BiometricPrompt bp = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            bp = new BiometricPrompt.Builder(this)
                    .setTitle("This is  finger print")
                    .setSubtitle("subtitle")
                    .setDescription("Description")
                    .setNegativeButton("cancel", executor, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).build();
        }
        button = (Button)findViewById(R.id.button2);
        final LogIn activity = this;
        final BiometricPrompt finalBp = bp;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    finalBp.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {
                        @Override
                        public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                            // super.onAuthenticationSucceeded(result);
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LogIn.this,"Athentication",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }
        });

        setToolBar();
        imageView = (ImageView)findViewById(R.id.image_holder);
        imageView.setImageResource(R.mipmap.group_6);
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, Registration08.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }


    public void sendtoFingerPrint(View view) {
        Intent move = new Intent(this, Registration08.class);
        startActivity(move);

    }

    public void movetoreg(MenuItem item) {
        Intent move = new Intent(this, Registration05.class);
        startActivity(move);
    }

    public void sendtohome(View view) {
        Intent move2 = new Intent(this, LinkAccount11.class);
        startActivity(move2);
    }
}