package com.example.wolanjej;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class LogIn extends AppCompatActivity {

    private ImageView imageView;
    private Button button;
    public  JSONObject sessionID = null;
    private EditText textPhone;
    private EditText textPin;

    private ImageButton imageButton;


    private  androidx.biometric.BiometricPrompt.PromptInfo promptInfo;
    private  androidx.biometric.BiometricPrompt biometricPrompt;
    private Executor executor;

    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";

    public Base64Encoder baseResult = new Base64Encoder();
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
        imageButton = findViewById(R.id.button2);
        final LogIn activity = this;
        final BiometricPrompt finalBp = bp;
        imageButton.setOnClickListener(new View.OnClickListener() {

//adasd
        executor = ContextCompat.getMainExecutor(this);
        final BiometricManager biometricManager = BiometricManager.from(this);

        biometricPrompt = new androidx.biometric.BiometricPrompt(LogIn.this,executor,new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                //super.onAuthenticationError(errorCode, errString);
                Toast.makeText(LogIn.this,"Error"+errString, LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                //super.onAuthenticationSucceeded(result);
                Toast.makeText(LogIn.this,"Succes"+result, LENGTH_SHORT).show();

                startActivity(new Intent(getParent(),Home.class));
            }

            @Override
            public void onAuthenticationFailed() {
                //super.onAuthenticationFailed();
                Toast.makeText(LogIn.this,"Failed", LENGTH_SHORT).show();
            }
        });

        promptInfo = new androidx.biometric.BiometricPrompt.PromptInfo.Builder().setTitle("Biometric Registration")
                .setSubtitle("Register using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();



        //button = findViewById(R.id.button2);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (biometricManager.canAuthenticate()) {
                    case BiometricManager.BIOMETRIC_SUCCESS:
                        biometricPrompt.authenticate(promptInfo);
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                        Toast.makeText(LogIn.this,"No hardware", LENGTH_SHORT).show();
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                        Toast.makeText(LogIn.this,"Hardware unavailable", LENGTH_SHORT).show();
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                        Toast.makeText(LogIn.this,"No Bio Enrolled", LENGTH_SHORT).show();
                        break;
                }

            }
        });





        // login button action
        button = (Button)findViewById(R.id.btn_LogIn);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendHomeScreen();
                    }
                }
        );

        setToolBar();
        imageView = (ImageView)findViewById(R.id.image_holder);
        imageView.setImageResource(R.drawable.ic_group_7);
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

    public void sendHomeScreen() {
        textPhone = findViewById(R.id.phoneNoLogIN);
        textPin = findViewById(R.id.pinLogIN);

        String phone = textPhone.getText().toString();
        String pin = textPin.getText().toString();
        if(phone!=null && pin!=null){
            String phonePin = "254"+phone+":"+pin; //adding a phone number and a pin together separating them using Full collon
            Log.d("TAG", phonePin);
            String results = baseResult.encodedValue(phonePin); // sending the phone number and pin for base 64 encoder for and getting the string value
            Log.d("TAG", results);
            String url = "/api/";
            OkhttpConnection okConn = new OkhttpConnection(); // calling the okhttp connection class here
            Response result = okConn.getLogin(url, results); // sending the url string and base 64 results to the okhttp connection and it's method is getLogin
            Log.d("TAG", String.valueOf(result));

            if ( result.code() == 200) {
                Toast.makeText(getApplicationContext(), "Your have been Loggedin successfuly", Toast.LENGTH_LONG).show();
                try {
                    String test = result.body().string();
                    Log.d("TAG test", test);
                    sessionID = new JSONObject(test);
                    System.out.println("Response body json values are : " + sessionID);
                    Log.d("TAG test Session", sessionID.getString("session_token"));
                    Intent move = new Intent(this, Home.class);
                    move.putExtra(EXTRA_SESSION, sessionID.getString("session_token"));
                    startActivity(move);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            }else if( result.code() != 201) {
                Log.d("TAG", String.valueOf(result));
                Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
            }
        }

//        Intent move2 = new Intent(this, LinkAccount11.class);
//        startActivity(move2);
    }
}