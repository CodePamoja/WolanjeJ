package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.example.wolanjej.RetrofitUtils.RetrofitClient;
import com.example.wolanjej.models.ModelUserDetails;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserProfileDetails extends AppCompatActivity {
    private String sessionID ;
    private SharedPreferences pref;
    private Intent intent;
    private Button button;
    private JSONObject jUserDetails;
    private ModelUserDetails modelUserDetail;
    private  ArrayAdapter adapter;
    private JsonPlaceHolders jsonPlaceHolder;
    private Spinner spinner;
    private TextInputLayout editText1, editText2, editText_email;
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_details);
        modelUserDetail = new ModelUserDetails();
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");


        spinner = (Spinner) findViewById(R.id.spinner_gender);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        setToolBar(tb);
    }
    private void setToolBar(Toolbar tb) {
         tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, Registration08.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );

    }



    public void userDetails(View view) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + sessionID + "");

        editText_email = findViewById(R.id.UserP_mail);
        String EMAIL = editText_email.getEditText().getText().toString().trim();
//        TODO: add email address

        editText1 = findViewById(R.id.UserNameP);
        String USER_NAME = editText1.getEditText().getText().toString().toUpperCase().trim();
        modelUserDetail.setFIRST_NAME(USER_NAME);

        editText2 = findViewById(R.id.nationalidp);
        String NATIONAL_ID = editText2.getEditText().getText().toString().toUpperCase().trim();
        modelUserDetail.setNATIONAL_ID(NATIONAL_ID);

        String  GENDER = spinner.getSelectedItem().toString();
        modelUserDetail.setGENDER(GENDER);

        if (USER_NAME.isEmpty()){
            editText1.requestFocus();
            return;
        }
        if (NATIONAL_ID.isEmpty()){
            editText2.requestFocus();
            return;
        }
        if (GENDER.isEmpty()){
            spinner.requestFocus();
            return;
        }
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
