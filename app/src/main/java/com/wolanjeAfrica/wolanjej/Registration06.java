package com.wolanjeAfrica.wolanjej;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

public class Registration06 extends AppCompatActivity {

    ProgressDialog prgBar;
    private JSONObject sessionID = null;
    public static final String EXTRA_SESSION = "com.wolanjeAfrica.wolanjej.SESSION";
    public static final String EXTRA_PHONE = "com.wolanjeAfrica.wolanjej.PHONE";
    private static final String TAG = "registrtion06";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_registration06);
        setToolBar();
        final EditText text1 = findViewById(R.id.edittext);
        final EditText text2 = findViewById(R.id.inp2);
        final EditText text3 = findViewById(R.id.inp3);
        final EditText text4 = findViewById(R.id.inp4);
        final String[] numbers = new String[4];


        text1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                numbers[0] = s.toString();
                text1.setFocusable(false);
                text1.setClickable(false);
                text2.requestFocus();

            }
        });
        text2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                numbers[1] = s.toString();
                text2.setFocusable(false);
                text2.setClickable(false);
                text3.requestFocus();
            }
        });
        text3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                numbers[2] = s.toString();
                text3.setFocusable(false);
                text3.setClickable(false);
                text4.requestFocus();
            }
        });
        text4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                numbers[3] = s.toString();
                text4.setFocusable(false);
                text4.setClickable(false);
                Toast.makeText(Registration06.this, "" + numbers[0] + "" + numbers[1] + "" + numbers[2] + "" + numbers[3], Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void setToolBar() {
        Toolbar tb = findViewById(R.id.toolbar);
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
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }


    public void sendtoSetYourPin(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("Registration_details", MODE_PRIVATE);
        String value = sharedPreferences.getString("Phone_Number", "");

        Toast.makeText(this, "The number is" + value, Toast.LENGTH_SHORT).show();
        new UserverifyOTP(value).execute();
    }

    public class UserverifyOTP extends AsyncTask<Void, Void, Response> {

        String phoneNo;

        public UserverifyOTP(String phonenumber) {

            phoneNo = phonenumber;

            Toast.makeText(Registration06.this, "Inside" + phoneNo, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPreExecute() {
            prgBar = new ProgressDialog(Registration06.this);
            prgBar.setMessage("Please Wait... Verifying OTP");
            prgBar.setIndeterminate(false);
            prgBar.setCancelable(false);
            prgBar.show();
        }

        @Override
        protected Response doInBackground(Void... voids) {
            JSONObject jValue = new JSONObject();
            try {
                jValue.put("phone", "254" + phoneNo);
                jValue.put("otp", "12345678");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String url = "/gapi/verifyOTP";
            OkhttpConnection okConn = new OkhttpConnection();
            Response result = okConn.postRequest(url, jValue.toString());
            return result;
        }

        @Override
        protected void onPostExecute(Response result) {
            String verifyResult = null;
            if (result.code() == 201) {
                prgBar.dismiss();
                Toast.makeText(getApplicationContext(), "OTP verified successfully", Toast.LENGTH_LONG).show();
                try {
                    verifyResult = result.body().string();
                    sessionID = new JSONObject(verifyResult); // adding

//                //bypass the verification code and page for now since we are adding otp for testing
                    Intent move = new Intent(Registration06.this, Registration07.class);
                    move.putExtra(EXTRA_SESSION, sessionID.getJSONObject("session").getString("session_token"));
                    move.putExtra(EXTRA_PHONE, phoneNo);
                    startActivity(move);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            } else if (result.code() != 201) {
                prgBar.dismiss();
                Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_LONG).show();
                try {
                    verifyResult = result.body().string();
                    JSONObject jBody = new JSONObject(verifyResult); // adding
                    String sendResutls = jBody.getJSONObject("errors").getJSONObject("otp").getJSONArray("otp").getJSONArray(0).getString(2);
                    Toast.makeText(getApplicationContext(), "Phone Number, " + sendResutls, Toast.LENGTH_LONG).show();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}