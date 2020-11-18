package com.wolanjeAfrica.wolanjej;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.gson.JsonObject;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.RetrofitClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Registration06 extends AppCompatActivity {

    ProgressDialog prgBar;
    private JSONObject sessionID = null;
    private TextView textView;
    private ProgressBar progressr07;
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
        progressr07 = (ProgressBar) findViewById(R.id.progressr07);
        final EditText text1 = findViewById(R.id.edittext);
        final EditText text2 = findViewById(R.id.inp2);
        final EditText text3 = findViewById(R.id.inp3);
        final EditText text4 = findViewById(R.id.inp4);
        textView = (TextView) findViewById(R.id.txtview_countdown);
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
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
        ExpirationTimer();
    }

    private void ExpirationTimer() {
        new CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(millisUntilFinished / 1000 + "seconds");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                textView.setText("done!");
            }
        }.start();
    }


    public void sendtoSetYourPin(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("Registration_details", MODE_PRIVATE);
        String value = sharedPreferences.getString("Phone_Number", "");
        VerifyOTP(value);
    }

    public void VerifyOTP(String phoneNumber) {
        progressr07.setVisibility(View.VISIBLE);
        JsonObject jValue = new JsonObject();
        jValue.addProperty("phone", "254" + phoneNumber);
        jValue.addProperty("otp", "12345678");

        Retrofit retrofit = RetrofitClient.getInstance();
        JsonPlaceHolders jsonPlaceHolders = retrofit.create(JsonPlaceHolders.class);
        Call<JsonObject> call = jsonPlaceHolders.VerifyOTP(jValue);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Registration06.this, response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String session = response.body().get("session").getAsJsonObject().get("session_token").getAsString();
                progressr07.setVisibility(View.GONE);
                Intent move = new Intent(Registration06.this, Registration07.class);
                move.putExtra(EXTRA_SESSION, session);
                move.putExtra(EXTRA_PHONE, phoneNumber);
                startActivity(move);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(Registration06.this, "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}