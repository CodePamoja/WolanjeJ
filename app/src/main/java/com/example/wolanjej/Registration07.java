package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

public class Registration07 extends AppCompatActivity {
    private ImageView imageView;
    private String sessionID = null;
    private TextInputLayout textPin1,textPin2;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration07);

        setToolBar();
        setActionBarColor();
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        this.phoneNumber = intent.getStringExtra(Registration05.EXTRA_PHONE);
        this.sessionID = intent.getStringExtra(Registration05.EXTRA_SESSION);
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
 //       final Intent movetoLogo = new Intent(this, Registration06.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
   //                     startActivity(movetoLogo);
                    }
                }
        );

    }
    private void setActionBarColor(){
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorWhite));
    }



    public void sendPin(View view) {
           sendPinServer();
    }

    public void sendPinServer() {
        textPin1 = findViewById(R.id.pin1);
        textPin2 = findViewById(R.id.pin2);

        String pin1 = String.valueOf(textPin1.getEditText().getText());
        String pin2 = String.valueOf(textPin2.getEditText().getText());
        if (pin1.equals(pin2) || pin1.length() > 3 || pin2.length() > 3){
            JSONObject jPin = new JSONObject();
            try {
                jPin.put("phone", "254"+phoneNumber);
                jPin.put("pin", pin1);

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
                //Intent move = new Intent(this, UserProfileDetails.class);
                Intent move = new Intent(this, LogIn.class);
                startActivity(move);

            }else if((responseCode = result.code()) != 201) {
                Log.d("TAG", String.valueOf(result));
                Toast.makeText(getApplicationContext(), "Access Denied to Resource", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "Requirements not meet", Toast.LENGTH_SHORT).show();
        }

    }
}