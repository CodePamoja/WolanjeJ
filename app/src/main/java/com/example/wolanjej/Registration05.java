package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

public class Registration05 extends AppCompatActivity {

    private ImageView imageView;
    public ProgressDialog prgBar;
    private EditText text;
    private JSONObject sessionID = null;
    private String phoneNo = null;
    private TextView tlc;
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONE = "com.example.wolanjej.PHONE";
    Toolbar tb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_registration05);

        tlc = findViewById(R.id.tlc);
        tlc.setMovementMethod(LinkMovementMethod.getInstance());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
         tb = findViewById(R.id.toolbar);

        text = findViewById(R.id.phoneNumber);
        setToolBar();
        imageView = findViewById(R.id.image_holder);
        imageView.setImageResource(R.drawable.ic_group_7);

        Button btn = findViewById(R.id.btn_continue);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       new UserSendPhone(text.getText().toString()).execute();

                    }
                }
        );
    }

    private void setToolBar() {
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this,MainActivity.class);
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
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    public void movetoLog(MenuItem item) {
        Intent movetoLogo = new Intent(this,LogIn.class);
        startActivity(movetoLogo);
    }

    public class UserSendPhone extends AsyncTask<Void, Void, Response> {



        public UserSendPhone(String phonenumber) {

            phoneNo = phonenumber;
        }

        @Override
        protected void onPreExecute() {
            prgBar = new ProgressDialog(Registration05.this);
            prgBar.setMessage("Please Wait... Sending Phone Number");
            prgBar.setIndeterminate(false);
            prgBar.setCancelable(false);
            prgBar .show();
        }

        @Override
        protected Response doInBackground(Void... voids) {

            JSONObject jPhone = new JSONObject();
            try {
                jPhone.put("phone", "254"+phoneNo);
             } catch (JSONException e) {
                e.printStackTrace();
            }
            String url = "/gapi/sendOTP";
            OkhttpConnection okConn = new OkhttpConnection();
            Response result = okConn.postRequest(url,jPhone.toString());
            return result;
        }

        @Override
        protected void onPostExecute(Response result) {
            if ( result.code() == 201) {
                prgBar.dismiss();
                Toast.makeText(getApplicationContext(), "Phone number sent successfully", Toast.LENGTH_LONG).show();
                new UserverifyOTP().execute();

            }else if(result.code() != 201) {
                prgBar.dismiss();
                String value = null;
                try {
                    value = result.body().string();
                    JSONObject jBody = new JSONObject(value); // adding
                    String sendResutls = jBody.getJSONObject("errors").getJSONObject("otp").getJSONArray("action").getJSONArray(0).getString(2);
                    Toast.makeText(getApplicationContext(), "Phone Number, "+sendResutls, Toast.LENGTH_LONG).show();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }
    }
    }


    public class UserverifyOTP extends AsyncTask<Void, Void, Response> {

        @Override
        protected void onPreExecute() {
            prgBar = new ProgressDialog(Registration05.this);
            prgBar.setMessage("Please Wait... Verifying OTP");
            prgBar.setIndeterminate(false);
            prgBar.setCancelable(false);
            prgBar .show();
        }

        @Override
        protected Response doInBackground(Void... voids) {
            JSONObject jValue = new JSONObject();
            try {
                jValue.put("phone", "254"+phoneNo);
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
                    Intent move = new Intent(Registration05.this, Registration07.class);
                    move.putExtra(EXTRA_SESSION, sessionID.getJSONObject("session").getString("session_token"));
                    move.putExtra(EXTRA_PHONE, phoneNo);
                    startActivity(move);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }else if(result.code() != 201) {
                prgBar.dismiss();
                Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_LONG).show();
                try {
                    verifyResult = result.body().string();
                    JSONObject jBody = new JSONObject(verifyResult); // adding
                    String sendResutls = jBody.getJSONObject("errors").getJSONObject("otp").getJSONArray("otp").getJSONArray(0).getString(2);
                    Toast.makeText(getApplicationContext(), "Phone Number, "+sendResutls, Toast.LENGTH_LONG).show();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public String getPhone(){
        return phoneNo;
    }

    public void createAccount(View view) {
        Intent movetobusiness = new Intent(this,Reg_02.class);
        startActivity(movetobusiness);
    }
}