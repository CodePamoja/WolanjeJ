package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

public class Registration051 extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Toolbar tb ;
    ProgressDialog prgBar;
    private Spinner spinner;
    private  ArrayAdapter adapter;
    TextInputLayout kenyanid,firstname,lastname,mygender;
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

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorWhite));
        spinner = (Spinner) findViewById(R.id.spinner_gender);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }
    private void setToolBar(Toolbar tb ) {

        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        final Intent movetoLogo = new Intent(this,Registration05.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void movetoLogin(View view) {

        Intent movetoLogo = new Intent(this,LogIn.class);
        startActivity(movetoLogo);
    }

    public void askForOtp(View view) {
        kenyanid = findViewById(R.id.kenyanid);
        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);

        if(TextUtils.isEmpty(kenyanid.getEditText().getText().toString()) ||TextUtils.isEmpty(firstname.getEditText().getText().toString()) ||TextUtils.isEmpty(lastname.getEditText().getText().toString()) ||TextUtils.isEmpty(spinner.getSelectedItem().toString())  ){
            Toast.makeText(getApplicationContext(), "Please provide the details", Toast.LENGTH_SHORT).show();
        }else{
            SharedPreferences fromreg2 = getSharedPreferences("Registration_details",MODE_PRIVATE);
            SharedPreferences.Editor editor = fromreg2.edit();
            editor.putString("Kenyan_id", kenyanid.getEditText().getText().toString());
            editor.putString("First_Name", firstname.getEditText().getText().toString());
            editor.putString("Last_Name",lastname.getEditText().getText().toString());
            editor.putString("Gender",spinner.getSelectedItem().toString());
            editor.commit();

             new UserSendPhone(fromreg2.getString("Phone_Number","")).execute();


        }
    }
    public class UserSendPhone extends AsyncTask<Void, Void, Response> {


        String phoneNo;

        public UserSendPhone(String phonenumber) {

            phoneNo = phonenumber;
        }

        @Override
        protected void onPreExecute() {
            prgBar = new ProgressDialog(Registration051.this);
            prgBar.setMessage("Please Wait... Sending Phone Number");
            prgBar.setIndeterminate(false);
            prgBar.setCancelable(false);
            prgBar .show();
        }

        @Override
        protected Response doInBackground(Void... voids) {

            JSONObject jPhone = new JSONObject();
            try {
                jPhone.put("phone", "254"+phoneNo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String url = "/gapi/sendOTP";
            OkhttpConnection okConn = new OkhttpConnection();
            Response result = okConn.postRequest(url,jPhone.toString());
            return result;
        }

        @Override
        protected void onPostExecute(Response result) {
            if ( result.code() == 201) {
                prgBar.dismiss();
                Toast.makeText(getApplicationContext(), "Phone number sent successfully", Toast.LENGTH_LONG).show();
                //new UserverifyOTP().execute();

                Intent movetoregnext = new Intent(Registration051.this,Registration06.class);

                startActivity(movetoregnext);
            }else if(result.code() != 201) {
                prgBar.dismiss();
                String value = null;
                try {
                    value = result.body().string();
                    JSONObject jBody = new JSONObject(value); // adding
                    String sendResutls = jBody.getJSONObject("errors").getJSONObject("otp").getJSONArray("action").getJSONArray(0).getString(2);
                    Toast.makeText(getApplicationContext(), "Phone Number, "+sendResutls, Toast.LENGTH_LONG).show();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}