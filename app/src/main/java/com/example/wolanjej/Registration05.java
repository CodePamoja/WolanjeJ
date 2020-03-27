package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
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
    private EditText text;
    private JSONObject sessionID = null;
    private String phone = null;
    public Sendtover conn;
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
        imageView.setImageResource(R.mipmap.group_5);

        Button btn = (Button)findViewById(R.id.btn_continue);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        text = findViewById(R.id.phoneNumber);
//                        conn = new Sendtover(text.toString());
//                        conn.sendToVerification();
                        sendToVerification();
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

    public void sendToVerification() {
        text = findViewById(R.id.phoneNumber);
        phone = text.getText().toString();
        Log.e("Test 1", phone);
        System.out.println(phone);

        JSONObject jPhone = new JSONObject();
        try {
            jPhone.put("phone", "254"+phone);
            Log.e("jPhone",jPhone.toString());
        } catch (JSONException e) {
            Log.e("Error",e.toString());
            e.printStackTrace();
        }
        String url = "/gapi/sendOTP";
        OkhttpConnection okConn = new OkhttpConnection();
        Response result = okConn.postRequest(url,jPhone.toString());

        if ( result.code() == 201) {
            System.out.println("Response body json values are : " + result);
            Log.e("TAG", String.valueOf(result));
            Toast.makeText(getApplicationContext(), "Phone number sent successfuly", Toast.LENGTH_LONG).show();
            verifyOTP(phone);

        }else if(result.code() != 201) {
            Log.e("TAG", String.valueOf(result.body()));
        }

        //bypass the verification code and page for now since we are adding otp for testing
//        Intent move = new Intent(this, Registration06.class);
//        startActivity(move);
    }

    public void verifyOTP(String phone){
        String verifyResult = null;
        JSONObject jValue = new JSONObject();
        try {
            jValue.put("phone", "254"+phone);
            jValue.put("otp", "12345678");
            Log.e("JValues",jValue.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "/gapi/verifyOTP";
        OkhttpConnection okConn = new OkhttpConnection();
        Response result = okConn.postRequest(url, jValue.toString());
        if (result.code() == 201) {
            try {
                verifyResult = result.body().string();
                sessionID = new JSONObject(verifyResult); // adding
                System.out.println("Response body json values are : " + verifyResult);
                Log.e("TAG", String.valueOf(verifyResult));

//                //bypass the verification code and page for now since we are adding otp for testing
                Intent move = new Intent(this, Registration07.class);
                move.putExtra(EXTRA_SESSION, sessionID.getString("session_token"));
                move.putExtra(EXTRA_PHONE, phone);
                startActivity(move);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }else if(result.code() != 201) {
            try {
                verifyResult = result.body().string();
                Log.e("TAG", String.valueOf(result));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public JSONObject getSessionID(){
        return sessionID;
    }

    public String getPhone(){
        return phone;
    }
}