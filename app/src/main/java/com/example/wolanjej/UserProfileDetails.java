package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.example.wolanjej.RetrofitUtils.RetrofitClient;
import com.example.wolanjej.models.ModelUserDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserProfileDetails extends AppCompatActivity {
    private String sessionID = null;
    private SharedPreferences pref;
    private Intent intent;
    private Button button;
    private JSONObject jUserDetails;
    private ModelUserDetails modelUserDetail;
    private JsonPlaceHolders jsonPlaceHolder;
    private EditText editText1, editText2, editText3, editText4,
            editText5, editText6, editText7, editText8, editText9, editText10, editText11, editText12, editText13,
            editText14, editText15, editText16, editText17, editText18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_details);
        modelUserDetail = new ModelUserDetails();
        editText1 = findViewById(R.id.fNamep);
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");

    }

    public void sendDetails(View view) {
        userDetails();
    }

    public void userDetails() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + sessionID + "");

        String FIRST_NAME = editText1.getText().toString().toUpperCase().trim();
        modelUserDetail.setFIRST_NAME(FIRST_NAME);

        editText2 = findViewById(R.id.lNamep);
        String LAST_NAME = editText2.getText().toString().toUpperCase().trim();
        modelUserDetail.setLAST_NAME(LAST_NAME);

        editText3 = findViewById(R.id.oNamep);
        String OTHER_NAME = editText3.getText().toString().toUpperCase().trim();
        modelUserDetail.setOTHER_NAME(OTHER_NAME);

        editText4 = findViewById(R.id.address1p);
        String ADDRESS1 = editText4.getText().toString().toUpperCase().trim();
        modelUserDetail.setADDRESS1(ADDRESS1);

        editText5 = findViewById(R.id.address2p);
        String ADDRESS2 = editText5.getText().toString().toUpperCase().trim();
        modelUserDetail.setADDRESS2(ADDRESS2);

        editText6 = findViewById(R.id.townp);
        String TOWN = editText6.getText().toString().toUpperCase().trim();
        modelUserDetail.setTOWN(TOWN);

        editText7 = findViewById(R.id.statep);
        String STATE = editText7.getText().toString().toUpperCase().trim();
        modelUserDetail.setSTATE(STATE);

        editText8 = findViewById(R.id.cityp);
        String CITY = editText8.getText().toString().toUpperCase().trim();
        modelUserDetail.setCITY(CITY);

        editText9 = findViewById(R.id.districtp);
        String DISTRICT = editText9.getText().toString().toUpperCase().trim();
        modelUserDetail.setDISTRICT(DISTRICT);

        editText10 = findViewById(R.id.countyp);
        String COUNTY = editText10.getText().toString().toUpperCase().trim();
        modelUserDetail.setCOUNTY(COUNTY);

        editText11 = findViewById(R.id.provincep);
        String PROVINCE = editText11.getText().toString().toUpperCase().trim();
        modelUserDetail.setPROVINCE(PROVINCE);

        editText12 = findViewById(R.id.countryp);
        String COUNTRY = editText12.getText().toString().toUpperCase().trim();
        modelUserDetail.setCOUNTRY(COUNTRY);

        editText13 = findViewById(R.id.dobp);
        String DATE_OF_BIRTH = editText13.getText().toString().toUpperCase().trim();
        modelUserDetail.setDATE_OF_BIRTH(DATE_OF_BIRTH);

        editText14 = findViewById(R.id.genderp);
        String GENDER = editText14.getText().toString().toUpperCase().trim();
        modelUserDetail.setGENDER(GENDER);

        editText15 = findViewById(R.id.passportp);
        String PASSPORT = editText15.getText().toString().toUpperCase().trim();
        modelUserDetail.setPASSPORT(PASSPORT);

        editText16 = findViewById(R.id.nationalidp);
        String NATIONAL_ID = editText16.getText().toString().toUpperCase().trim();
        modelUserDetail.setNATIONAL_ID(NATIONAL_ID);

        editText17 = findViewById(R.id.facebookp);
        String FACEBOOK = editText17.getText().toString().toUpperCase().trim();
        modelUserDetail.setFACEBOOK(FACEBOOK);

        editText18 = findViewById(R.id.twitterp);
        String TWITTER = editText18.getText().toString().toUpperCase().trim();
        modelUserDetail.setTWITTER(TWITTER);


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
                Toast.makeText(getApplicationContext(), "success" +response.code(), Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFailure(Call<ModelUserDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error"+t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
