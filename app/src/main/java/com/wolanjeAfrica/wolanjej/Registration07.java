package com.wolanjeAfrica.wolanjej;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

public class Registration07 extends AppCompatActivity {
    private ImageView imageView;
    private String sessionID = null;
    private TextInputLayout textPin1, textPin2;
    private String phoneNumber;
    private SharedPreferences pref;
    private String email;
    public ProgressBar progressBar;
    private String FirstName;
    private String LastName;
    private String Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration07);
        setToolBar();
        progressBar = (ProgressBar) findViewById(R.id.progressr07);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        this.phoneNumber = intent.getStringExtra(Registration05.EXTRA_PHONE);
        this.sessionID = intent.getStringExtra(Registration05.EXTRA_SESSION);

        pref = getApplication().getSharedPreferences("Registration_details", MODE_PRIVATE);
        this.email = pref.getString("Email_adress", "");
        this.FirstName = pref.getString("First_Name", "");
        this.LastName = pref.getString("Last_Name", "");
        this.Gender = pref.getString("Gender", "");
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }
        );
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }


    public void sendPin(View view) {
        textPin1 = findViewById(R.id.pin1);
        textPin2 = findViewById(R.id.pin2);
        String pin1 = String.valueOf(textPin1.getEditText().getText());
        String pin2 = String.valueOf(textPin2.getEditText().getText());
        if (pin1.isEmpty() || pin2.isEmpty()) {
            Toast.makeText(this, "please provide a pin", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pin1.equals(pin2)) {
            Toast.makeText(this, "pin don't match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pin1.length() > 3) {
            new sendPinServer(pin1, phoneNumber).execute();
        } else {
            Toast.makeText(this, "pin 4 digits", Toast.LENGTH_SHORT).show();
        }
    }

    private void SuccessSignUp() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.success_user_signup, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        Button btn = view.findViewById(R.id.buttonSignUpSuccess);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(Registration07.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        alertDialog.show();
    }


    public class sendPinServer extends AsyncTask<Void, Void, Response> {
        String phonenumber;
        String pin1;

        public sendPinServer(String pin1, String phone) {
            this.phonenumber = phone;
            this.pin1 = pin1;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Response doInBackground(Void... voids) {
            JSONObject jPin = new JSONObject();
            try {
                jPin.put("phone", "254" + phoneNumber);
                jPin.put("pin", pin1);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String url = "/api/";
            OkhttpConnection okConn = new OkhttpConnection();
            return okConn.postValue(url, jPin.toString(), sessionID);
        }

        @Override
        protected void onPostExecute(Response response) {
            progressBar.setVisibility(View.GONE);
            if (response.code() == 202) {
                SuccessSignUp();
            } else if (response.code() != 201) {
                Log.d("TAG", String.valueOf(response));
                Toast.makeText(getApplicationContext(), "Access Denied to Resource", Toast.LENGTH_LONG).show();
            }

        }
    }

}