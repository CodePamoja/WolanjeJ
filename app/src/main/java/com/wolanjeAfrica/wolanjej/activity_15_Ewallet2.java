package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class activity_15_Ewallet2 extends AppCompatActivity {

    private String sessionID;
    private EditText text;

    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_15__ewallet2);

        // Get the Intent that started this activity and extract the string
        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        if(className.equals("Home")) {
            this.sessionID = intentExtra.getStringExtra(Home.EXTRA_SESSION);
        }
    }

    public void btnTopUpWallet(View view) {
        Intent intent = new Intent(this, activity_15_Ewallet2_1.class);
        intent.putExtra(EXTRA_SESSION, sessionID);
        intent.putExtra("Class","activity_15_Ewallet2");
        startActivity(intent);
    }
}