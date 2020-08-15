package com.example.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.example.wolanjej.RetrofitUtils.RetrofitClient;
import com.example.wolanjej.models.ModelUserDetails;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Profile49 extends AppCompatActivity {
    private Toolbar tb;
    private SharedPreferences pref;
    private String sessionID;
    private Spinner spinner;
    private ModelUserDetails modelUserDetail;
    private ArrayAdapter adapter;
    private TextInputLayout editText, editText1, editText2, editText3, editText4, editText5, editText6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile49);
        modelUserDetail = new ModelUserDetails();

        spinner = (Spinner) findViewById(R.id.spinner_gender);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        editText = findViewById(R.id.name_profile2);
        editText1 = findViewById(R.id.username_prof2);
        editText2 = findViewById(R.id.email_prof2);
        editText3 = findViewById(R.id.phoneNumberProf2);
        editText4 = findViewById(R.id.id_profile2);
        editText5 = findViewById(R.id.country_prof2);
        editText6 = findViewById(R.id.date_profile2);
        setToolBar(tb);
        getUserInput();
    }

    public void MoveToProfile(View view) {
        Intent intent = new Intent(getApplicationContext(), profile.class);
        startActivity(intent);
    }

    private void setToolBar(Toolbar tb) {
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, profile.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );

    }

    private void getUserInput() {
        String FullName = editText.getEditText().getText().toString();

        String UserName = editText1.getEditText().getText().toString();
        String Email = editText2.getEditText().toString();
        String phoneNumber = editText3.getEditText().toString();
        String IdNo = editText4.getEditText().getText().toString();
        String Country = editText5.getEditText().toString();
        String Date = editText6.getEditText().toString();
    }

    public void userDetails(View view) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + sessionID + "");


        String FullName = editText.getEditText().getText().toString();

        String UserName = editText1.getEditText().getText().toString();
        String Email = editText2.getEditText().toString();
        String phoneNumber = editText3.getEditText().toString();
        String IdNo = editText4.getEditText().getText().toString();
        String Country = editText5.getEditText().toString();
        String Date = editText6.getEditText().toString();


        Retrofit retrofit = RetrofitClient.getInstance();
        JsonPlaceHolders jsonPlaceHolder = retrofit.create(JsonPlaceHolders.class);
        Call<ModelUserDetails> call = jsonPlaceHolder.createUserDetails(modelUserDetail, headers);

        call.enqueue(new Callback<ModelUserDetails>() {
            @Override
            public void onResponse(Call<ModelUserDetails> call, Response<ModelUserDetails> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "code" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "success" + response.code(), Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFailure(Call<ModelUserDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}