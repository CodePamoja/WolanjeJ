package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

public class Registration07 extends AppCompatActivity {
    private ImageView imageView;
    private String sessionID = null;
    private EditText textPin1;
    private EditText textPin2;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration07);

        setToolBar();
        imageView = (ImageView)findViewById(R.id.image_holder);
        imageView.setImageResource(R.mipmap.group_6);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        this.phoneNumber = intent.getStringExtra(Registration05.EXTRA_PHONE);
        this.sessionID = intent.getStringExtra(Registration05.EXTRA_SESSION);

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, Registration06.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );

    }


//    public void sendtoFingerPrint(View view) {
//        Intent move = new Intent(this, registration08.class);
//        startActivity(move);
//
//    }

    public void sendPin(View view) {
        sendPinServer();
    }

    public void sendPinServer() {
        textPin1 = findViewById(R.id.pin1);
        textPin2 = findViewById(R.id.pin2);

        String pin1 = textPin1.getText().toString();
        String pin2 = textPin2.getText().toString();
            if (pin1.equals(pin2)){
                JSONObject jPin = new JSONObject();
                try {
                    jPin.put("phone", "254"+phoneNumber);
                    jPin.put("pin", pin1);
                    Log.e("jPhone",jPin.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String url = "/api/";
                OkhttpConnection okConn = new OkhttpConnection();
                Response result = null;
                result = okConn.postValue(url, jPin.toString(), sessionID);

                int responseCode = 0;
                if ((responseCode = result.code()) == 202) {
                    System.out.println("Response body json values are : " + result);
                    Log.d("TAG", String.valueOf(result));
                    Toast.makeText(getApplicationContext(), "Your password has been changed successfuly", Toast.LENGTH_LONG).show();
                    Intent move = new Intent(this, Registration08.class);
                    startActivity(move);

                }else if((responseCode = result.code()) != 201) {
                    Log.d("TAG", String.valueOf(result));
                    Toast.makeText(getApplicationContext(), "Access Denied to Resource", Toast.LENGTH_LONG).show();
                }
            }

    }
}