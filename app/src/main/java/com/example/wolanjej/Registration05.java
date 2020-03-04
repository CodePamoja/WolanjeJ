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

import org.json.JSONException;
import org.json.JSONObject;

public class Registration05 extends AppCompatActivity {

    private ImageView imageView;
    private EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_registration05);

        setToolBar();
        imageView = (ImageView)findViewById(R.id.image_holder);
        imageView.setImageResource(R.mipmap.group_5);

        Button btn = findViewById(R.id.btn_continue);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked");
                sendToVerification();
            }
        });
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
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
        String value = text.getText().toString();
        System.out.println(value);

        JSONObject jValue = new JSONObject();
        try {
            jValue.put("phone", value);
            Log.e("JValues",jValue.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

       OkhttpConnection okConn = new OkhttpConnection();
        String result = okConn.postRequest(jValue.toString());
        System.out.println(result);

        /*Intent move = new Intent(this, Registration06.class);
        startActivity(move);*/
    }
}