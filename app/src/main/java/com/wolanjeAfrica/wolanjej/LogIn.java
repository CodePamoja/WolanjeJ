package com.wolanjeAfrica.wolanjej;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class LogIn extends AppCompatActivity {

    private ImageView imageView, imageView1;
    public ProgressDialog prgBar;
    private Button button;
    public JSONObject sessionID = null;
    private TextInputLayout textPhone, textPin;
    private androidx.biometric.BiometricPrompt.PromptInfo promptInfo;
    private androidx.biometric.BiometricPrompt biometricPrompt;
    private ConnectivityManager connectivityManager;
    private Executor executor;
    private String TAG = "Login";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_ID = "com.example.wolanjej.ID";
    public static final String EXTRA_USERNAME = "com.example.wolanjej.USERNAME";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";

    public Base64Encoder baseResult = new Base64Encoder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_log_in);

        final LinearLayout linearLayout = findViewById(R.id.LoginNetAlert);
        imageView1 = findViewById(R.id.closeNetAlert);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
            }
        });
        textPhone = findViewById(R.id.phoneNoLogIN);
        textPin = findViewById(R.id.pinLogIN);

        // login button action
        button = findViewById(R.id.btn_LogIn);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isNetworkAvailable()) {
                            String phone = textPhone.getEditText().getText().toString();
                            String pin = textPin.getEditText().getText().toString();
                            if (phone.isEmpty() || pin.isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Please Fill ", LENGTH_SHORT).show();
                                return;
                            }
                            new UserLogin(phone, pin).execute();
                        } else {
                            linearLayout.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "No internet Available", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

        setToolBar();
//        imageView = findViewById(R.id.image_holder);
//        imageView.setImageResource(R.drawable.ic_groupwall);
        if (Build.VERSION.SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        setActionBarColor();
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, Registration05.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
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

    private void moveToreg5(View view) {
        Intent intent = new Intent(getApplicationContext(), Registration05.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public class UserLogin extends AsyncTask<Void, Void, Response> {

        private String phone;
        private String pin;

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
            prgBar.show();
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response result = null;

            if (phone != null && pin != null) {
                String phonePin = "254" + phone + ":" + pin; //adding a phone number and a pin together separating them using Full collon
                String results = baseResult.encodedValue(phonePin); // sending the phone number and pin for base 64 encoder for and getting the string value

                String url = "/api/";
                OkhttpConnection okConn = new OkhttpConnection(); // calling the okhttp connection class here
                result = okConn.getLogin(url, results); // sending the url string and base 64 results to the okhttp connection and it's method is getLogin

            }
            return result;
        }

        @Override
        protected void onPostExecute(Response result) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            prgBar.dismiss();

                        }
                    });
                    Log.e("All result", "Result");
                    if (result != null && result.code() == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Your have been Loggedin successfuly", Toast.LENGTH_LONG).show();
                            }
                        });


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
                            System.out.println(TAG + sessionID.getJSONObject("session").getString("agentno"));
                            editor.apply();
                            Intent move = new Intent(LogIn.this, LinkAccount11.class);
                            move.putExtra("Class", "LogIn");
                            startActivity(move);
                            finish();
                        } catch (JSONException | IOException e) {
                            //e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LogIn.this, "There is a problem with your internet connection.Please try again if not logged in.", LENGTH_SHORT).show();

                                }
                            });

                        }
                    } else if (result != null && result.code() != 201) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LogIn.this, "Sorry something went wrong", Toast.LENGTH_LONG).show();
                            }
                        });

                    }


                }
            }).start();

            Log.e("All result", "Result");

        }
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

}
