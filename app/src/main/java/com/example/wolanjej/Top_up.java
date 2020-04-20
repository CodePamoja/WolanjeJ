package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Top_up extends AppCompatActivity implements View.OnClickListener{
    private Button button;
    private String sessionID;
    private EditText text;

    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        // Get the Intent that started this activity and extract the string
        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        if(className.equals("Home")) {
            this.sessionID = intentExtra.getStringExtra(Home.EXTRA_SESSION);
        }
//        else if (className.equals("MainTransfer36")){
//            this.sessionID = intentExtra.getStringExtra(MainTransfer36.EXTRA_SESSION);
//        }

        button = findViewById(R.id.btn_continue_top_up);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.income_details101: intent = new Intent(this, IncomeDetails.class);startActivity(intent);
            break;
            case R.id.btn_continue_top_up:intent = new Intent(this,TopupOtherNumber.class);startActivity(intent);
            default:break;
    }
}
}