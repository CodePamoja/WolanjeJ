package com.example.wolanjej;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
    public ProgressDialog prgBar;
    private Button button;
    public  JSONObject sessionID = null;
    private EditText textPhone;
    private EditText textPin;
    private  androidx.biometric.BiometricPrompt.PromptInfo promptInfo;
    private  androidx.biometric.BiometricPrompt biometricPrompt;
    private Executor executor;
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_ID = "com.example.wolanjej.ID";
    public static final String EXTRA_USERNAME = "com.example.wolanjej.USERNAME";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";

    public Base64Encoder baseResult = new Base64Encoder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_log_in);

        textPhone = findViewById(R.id.phoneNoLogIN);
        textPin = findViewById(R.id.pinLogIN);

        // login button action
        button = findViewById(R.id.btn_LogIn);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new UserLogin(textPhone.getText().toString(),textPin.getText().toString()).execute();
                    }
                }
        );

        setToolBar();
        imageView = findViewById(R.id.image_holder);
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

    public void FogtPssd(View view) {
        Intent move = new Intent(this, Registration05.class);
        startActivity(move);
    }

    public class UserLogin extends AsyncTask<Void, Void, Response> {

        private String phone ;
        private String pin ;

        UserLogin(String phone, String pin) {
            this.phone = phone;
            this.pin = pin;
        }

        @Override
        protected void onPreExecute() {
            prgBar = new ProgressDialog(LogIn.this);
            prgBar.setMessage("Please Wait");
            prgBar.setIndeterminate(false);
            prgBar.setCancelable(false);
            prgBar .show();
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response result = null;

            if(phone!=null && pin!=null){
                String phonePin = "254"+phone+":"+pin; //adding a phone number and a pin together separating them using Full collon
                String results = baseResult.encodedValue(phonePin); // sending the phone number and pin for base 64 encoder for and getting the string value

                String url = "/api/";
                OkhttpConnection okConn = new OkhttpConnection(); // calling the okhttp connection class here
                result = okConn.getLogin(url, results); // sending the url string and base 64 results to the okhttp connection and it's method is getLogin

            }
            return result;
        }

        @Override
        protected void onPostExecute(Response result) {
            prgBar.dismiss();
            Log.e("All result","Result");

            if ( result.code() == 200) {
                Toast.makeText(getApplicationContext(), "Your have been Loggedin successfuly", Toast.LENGTH_LONG).show();


                try {
                    String test = result.body().string();
                    sessionID = new JSONObject(test);
                    //adding values to SharedPreferences
                    // make sure that in the getsharedPreferences the key value should be the same as the intent putextra class value

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("LogIn", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("session_token", sessionID.getJSONObject("session").getString("session_token"));
                    editor.putString("id", sessionID.getJSONObject("session").getString("id"));
                    editor.putString("role", sessionID.getJSONObject("session").getString("role"));
                    editor.putString("user_name", sessionID.getJSONObject("session").getString("user_name"));
                    editor.putString("agentno", sessionID.getJSONObject("session").getString("agentno"));
                    editor.commit();
                    Intent move = new Intent(LogIn.this, Home.class);
                    move.putExtra("Class","LogIn");
                    startActivity(move);
                } catch (JSONException | IOException e) {
                    //e.printStackTrace();
                    Toast.makeText(LogIn.this, "There is a problem with your internet connection.Please try again if not logged in.", LENGTH_SHORT).show();

                }
            }else if( result.code() != 201) {
                Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
            }
        }
    }

}