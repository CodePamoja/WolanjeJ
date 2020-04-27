package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

import okhttp3.Response;

public class Registration05 extends AppCompatActivity {

    private ImageView imageView;
    public ProgressDialog prgBar;
    private EditText text;
    private JSONObject sessionID = null;
    private String phoneNo = null;
//    public Sendtover conn;
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONE = "com.example.wolanjej.PHONE";
    Toolbar tb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_registration05);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
         tb = (Toolbar) findViewById(R.id.toolbar);

        setToolBar();
        imageView = (ImageView)findViewById(R.id.image_holder);
        imageView.setImageResource(R.drawable.ic_group_7);

        Button btn = (Button)findViewById(R.id.btn_continue);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        text = findViewById(R.id.phoneNumber);
//                        conn = new Sendtover(text.toString());
//                        conn.sendToVerification();
                       new UserSendPhone().execute();
                    }
                }
        );
       /* Button btn_business= findViewById(R.id.btn_business);
        btn_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration05.this,HomeTwo.class));
            }
        });
*/
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
            text = findViewById(R.id.phoneNumber);
            phoneNo = text.getText().toString();
            Log.e("Test 1", phoneNo);
            System.out.println(phoneNo);

            JSONObject jPhone = new JSONObject();
            try {
                jPhone.put("phone", "254"+phoneNo);
                Log.e("jPhone",jPhone.toString());
            } catch (JSONException e) {
                Log.e("Error",e.toString());
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
                System.out.println("Response body json values are : " + result);
                Log.e("TAG", String.valueOf(result));
                Toast.makeText(getApplicationContext(), "Phone number sent successfully", Toast.LENGTH_LONG).show();
                new UserverifyOTP().execute();

            }else if(result.code() != 201) {
                prgBar.dismiss();
                String value = null;
                try {
                    value = result.body().string();
                    JSONObject jBody = new JSONObject(value); // adding
                    System.out.println("Response body json values are : " + value);
                    Log.e("TAG", String.valueOf(value));
                    String sendResutls = jBody.getJSONObject("errors").getJSONObject("otp").getJSONArray("action").getJSONArray(0).getString(2);
                    Log.e("TAG", String.valueOf(sendResutls));
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
                Log.e("JValues",jValue.toString());
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
                    System.out.println("Response body json values are : " + verifyResult);
                    Log.e("TAG", String.valueOf(verifyResult));

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
                    System.out.println("Response body json values are : " + verifyResult);
                    Log.e("TAG", String.valueOf(verifyResult));
                    String sendResutls = jBody.getJSONObject("errors").getJSONObject("otp").getJSONArray("otp").getJSONArray(0).getString(2);
                    Log.e("TAG", String.valueOf(sendResutls));
                    Toast.makeText(getApplicationContext(), "Phone Number, "+sendResutls, Toast.LENGTH_LONG).show();
                    Log.e("TAG result value", String.valueOf(result));
                    Log.e("TAG result body", verifyResult);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public void verifyOTP(String phone){
//        String verifyResult = null;
//        JSONObject jValue = new JSONObject();
//        try {
//            jValue.put("phone", "254"+phone);
//            jValue.put("otp", "12345678");
//            Log.e("JValues",jValue.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String url = "/gapi/verifyOTP";
//        OkhttpConnection okConn = new OkhttpConnection();
//        Response result = okConn.postRequest(url, jValue.toString());
//        if (result.code() == 201) {
//            Toast.makeText(getApplicationContext(), "OTP verified successfully", Toast.LENGTH_LONG).show();
//            try {
//                verifyResult = result.body().string();
//                sessionID = new JSONObject(verifyResult); // adding
//                System.out.println("Response body json values are : " + verifyResult);
//                Log.e("TAG", String.valueOf(verifyResult));
//
////                //bypass the verification code and page for now since we are adding otp for testing
//                Intent move = new Intent(this, Registration07.class);
//                move.putExtra(EXTRA_SESSION, sessionID.getString("session_token"));
//                move.putExtra(EXTRA_PHONE, phone);
//                startActivity(move);
//            } catch (IOException | JSONException e) {
//                e.printStackTrace();
//            }
//        }else if(result.code() != 201) {
//            Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_LONG).show();
//            try {
//                verifyResult = result.body().string();
//                Log.e("TAG", String.valueOf(result));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }


    public String getPhone(){
        return phoneNo;
    }

    public void createAccount(View view) {
        Intent movetobusiness = new Intent(this,Reg_02.class);
        startActivity(movetobusiness);
    }
}