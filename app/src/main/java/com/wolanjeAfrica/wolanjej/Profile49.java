package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.wolanjeAfrica.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.RetrofitClient;
import com.wolanjeAfrica.wolanjej.models.ModelUserDetails;
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
    private ModelUserDetails modelUserDetail;
    private TextInputLayout editText, editText1, editText2, editText3, editText4, editText5, editText6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile49);
        modelUserDetail = new ModelUserDetails();

        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");

        editText = findViewById(R.id.name_profile2);
        editText1 = findViewById(R.id.username_prof2);
        editText2 = findViewById(R.id.email_prof2);
        editText3 = findViewById(R.id.phoneNumberProf2);
        editText4 = findViewById(R.id.id_profile2);
        editText5 = findViewById(R.id.country_prof2);
        editText6 = findViewById(R.id.date_profile2);
        setToolBar(tb);
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
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }
    public void userDetails(View view) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + sessionID + "");


        String FirstName = editText.getEditText().getText().toString();
        modelUserDetail.setFIRST_NAME(FirstName);
        String LastName = editText1.getEditText().getText().toString();
        modelUserDetail.setLAST_NAME(LastName);
        String Email = editText2.getEditText().toString();
        String phoneNumber = editText3.getEditText().toString();
        String IdNo = editText4.getEditText().getText().toString();
        modelUserDetail.setNATIONAL_ID(IdNo);
        String Country = editText5.getEditText().toString();
        modelUserDetail.setCOUNTRY(Country);
        String Date = editText6.getEditText().toString();
        modelUserDetail.setDATE_OF_BIRTH(Date);


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
                Intent intent = new Intent(getApplicationContext(), profile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }


            @Override
            public void onFailure(Call<ModelUserDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}