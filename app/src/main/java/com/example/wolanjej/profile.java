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

import com.google.android.material.card.MaterialCardView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;


public class profile extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences pref;
    private static String sessionID;
    private static String AGENTNO;
    private static TextView tvtext;
    private MaterialCardView materialCardView, materialCardView1;
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

        new UserProfile().execute();
        setToolBar();
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

    public static class UserProfile extends AsyncTask<Void, Void, Response> {

        @Override
        protected Response doInBackground(Void... voids) {

            String url = "/api/profiles";
            OkhttpConnection okConn = new OkhttpConnection(); // calling the okhttp connection class here
            Response result = okConn.getBalance(url, sessionID); // sending the url string and base 64 results to the okhttp connection and it's method is getLogin
            Log.d("TAG", String.valueOf(result));
            return result;
        }

        @Override
        protected void onPostExecute(Response result) {
            String verifyResult = null;
            if (result.code() == 200) {
                try {
                    String test = result.body().string();
                    Log.d("TAG test", test);
                    JSONObject JProfile = new JSONObject(test);
                    System.out.println("Response body json values  for profiles are : " + JProfile);

//                    String resultProfile = JBills.getJSONArray("profiles").getJSONObject(0).getString("name");
//                    tvtext.setText( resultProfile);

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            } else if (result.code() != 201) {
                try {
                    verifyResult = result.body().string();
                    JSONObject jBody = new JSONObject(verifyResult); // adding
                    System.out.println("Response body json values are : " + verifyResult);
                    Log.e("TAG", String.valueOf(verifyResult));
//                    String sendResutls = jBody.getJSONObject("errors").getJSONObject("otp").getJSONArray("otp").getJSONArray(0).getString(2);
//                    Log.e("TAG", String.valueOf(sendResutls));
//                    Toast.makeText(getApplicationContext(), "Phone Number, "+sendResutls, Toast.LENGTH_LONG).show();
                    Log.e("TAG result value", String.valueOf(result));
                    Log.e("TAG result body", verifyResult);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }
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
