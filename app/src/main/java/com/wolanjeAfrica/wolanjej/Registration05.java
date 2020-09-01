package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

public class Registration05 extends AppCompatActivity {

    public ProgressDialog prgBar;
    private TextInputLayout text,mail;
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
         tb = findViewById(R.id.toolbar);

         mail = findViewById(R.id.mail);
        text = findViewById(R.id.phoneNumber);
        setToolBar();

        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.bShadeGray));


        Button btn = findViewById(R.id.btn_continue);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      // new UserSendPhone(text.getEditText().getText().toString()).execute();
                        if(TextUtils.isEmpty(text.getEditText().getText().toString()) || TextUtils.isEmpty(mail.getEditText().getText().toString())){

                            Toast.makeText(Registration05.this, "Fill all", Toast.LENGTH_SHORT).show();

                        }else {
                            SharedPreferences registration_details = getSharedPreferences("Registration_details", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = registration_details.edit();
                            editor.putString("Phone_Number", text.getEditText().getText().toString());
                            editor.putString("Email_adress", mail.getEditText().getText().toString());
                            editor.apply();

                            startActivity(new Intent(getApplicationContext(),Registration051.class));
                        }
                    }
                }
        );
    }

    private void setToolBar() {
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this,LogIn.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );

    }

    public void movetoLogin(View view) {

        Intent movetoLogo = new Intent(this,LogIn.class);
        startActivity(movetoLogo);
    }

    public String getPhone(){
        return phoneNo;
    }

    public void createAccount(View view) {
        Intent movetobusiness = new Intent(this,Reg_02.class);
        startActivity(movetobusiness);
    }
}