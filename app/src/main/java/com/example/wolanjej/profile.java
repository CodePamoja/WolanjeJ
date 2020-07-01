package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wolanjej.RetrofitUtils.ApiJsonObjects;
import com.example.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.example.wolanjej.RetrofitUtils.RetrofitClient;
import com.example.wolanjej.models.ModelUserDetails;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class profile extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences pref;
    private String sessionID;
    private  String AGENTNO;
    private  TextView tvtext;
    private MaterialCardView materialCardView, materialCardView1;
    private List<ModelUserDetails> modelUserDetails = new ArrayList<>();
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvtext = findViewById(R.id.txtProfName);

        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");

        materialCardView1 =findViewById(R.id.card2Prof);
        materialCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings48.class);
                startActivity(intent);
            }
        });

        materialCardView = findViewById(R.id.CardRepurchaseReport);
        materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowDialog();
            }
        });

        setToolBar();
        RetrieveUserInfo();
    }
    private void setToolBar() {
        Toolbar tb = findViewById(R.id.toolbar_account);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,Home.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }

    private void RetrieveUserInfo(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + sessionID + "");

        Retrofit retrofit = RetrofitClient.getInstance();
        JsonPlaceHolders jsonPlaceHolder = retrofit.create(JsonPlaceHolders.class);
        Call<ApiJsonObjects> call = jsonPlaceHolder.getProfile(headers);

        call.enqueue(new Callback<ApiJsonObjects>() {
            @Override
            public void onResponse(Call<ApiJsonObjects> call, Response<ApiJsonObjects> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(profile.this, "code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                modelUserDetails = response.body().getUserDetails();
                for (ModelUserDetails modelUserDetails1 : modelUserDetails){
                    String USER_NAME =  modelUserDetails1.getFIRST_NAME() + modelUserDetails1.getLAST_NAME();
                    tvtext.setText(USER_NAME);
                }

            }

            @Override
            public void onFailure(Call<ApiJsonObjects> call, Throwable t) {

                Toast.makeText(profile.this, "code" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void ShowDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.repurchase_report_dialog, null);


        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        ImageView dismiss = view.findViewById(R.id.img_report_dlg);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        TextView textView1 = view.findViewById(R.id.txt_received_report_dlg);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TransactionView.class);
                startActivity(intent);
            }
        });

        alertDialog.show();
    }

    @Override
    public void onClick(View v) {

    }
}
