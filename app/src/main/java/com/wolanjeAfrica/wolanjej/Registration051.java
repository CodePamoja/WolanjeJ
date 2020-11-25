package com.wolanjeAfrica.wolanjej;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class Registration051 extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "Registration051";
    private Toolbar tb;
    private ProgressDialog prgBar;
    private ProgressBar progressBar;
    private Spinner spinner;
    private ArrayAdapter adapter;
    TextInputLayout kenyanid, firstname, lastname, mygender;
    AutoCompleteTextView autoCompleteTextView;
    String[] gender = {
            "Male",
            "Female"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration051);

        setToolBar(tb);


        spinner = (Spinner) findViewById(R.id.spinner_gender);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }

    private void setToolBar(Toolbar tb) {

        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
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
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void movetoLogin(View view) {

        Intent movetoLogo = new Intent(this, LogIn.class);
        startActivity(movetoLogo);
    }

    public void askForOtp(View view) {
        kenyanid = findViewById(R.id.kenyanid);
        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);

        if (TextUtils.isEmpty(kenyanid.getEditText().getText().toString()) || TextUtils.isEmpty(firstname.getEditText().getText().toString()) || TextUtils.isEmpty(lastname.getEditText().getText().toString()) || TextUtils.isEmpty(spinner.getSelectedItem().toString())) {
            Toast.makeText(getApplicationContext(), "Please provide the details", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences fromreg2 = getSharedPreferences("Registration_details", MODE_PRIVATE);
            SharedPreferences.Editor editor = fromreg2.edit();
            editor.putString("Kenyan_id", kenyanid.getEditText().getText().toString());
            editor.putString("First_Name", firstname.getEditText().getText().toString());
            editor.putString("Last_Name", lastname.getEditText().getText().toString());
            editor.putString("Gender", spinner.getSelectedItem().toString());
            editor.apply();

            UserSendPhone(fromreg2.getString("Phone_Number", ""));

        }
    }
    private void UserSendPhone(String phone){
        progressBar.setVisibility(View.VISIBLE);
        JsonObject jValue = new JsonObject();
        jValue.addProperty("phone", "254" + phone);

        Retrofit retrofit = RetrofitClient.getInstance();
        JsonPlaceHolders jsonPlaceHolders = retrofit.create(JsonPlaceHolders.class);
        Call<ResponseBody> call = jsonPlaceHolders.RegisterPhoneNumber(jValue);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                progressBar.setVisibility(View.GONE);
                if (!response.isSuccessful()){
                    Toast.makeText(Registration051.this, "This Number Is already registered", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "Phone number sent successfully", Toast.LENGTH_LONG).show();
                Intent movetoregnext = new Intent(Registration051.this, Registration06.class);
                startActivity(movetoregnext);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Registration051.this, "error"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }



}