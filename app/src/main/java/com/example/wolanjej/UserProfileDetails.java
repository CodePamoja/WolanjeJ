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

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import okhttp3.Response;

public class UserProfileDetails extends AppCompatActivity {
    private String sessionID = null;
    private SharedPreferences pref;
    private Intent intent;
    private Button button;
    private JSONObject jUserDetails;
    private EditText editText1, editText2, editText3, editText4,
            editText5, editText6, editText7,editText8, editText9
            ,editText10,editText11, editText12, editText13,
            editText14, editText15, editText16, editText17, editText18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_details);

        editText1 = findViewById(R.id.fNamep);
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");

    }

    public void sendDetails(View view) {
        userDetails();
    }
    public void userDetails(){
        String FIRST_NAME = editText1.getText().toString();

        editText2 = findViewById(R.id.lNamep);
        String LAST_NAME = editText2.getText().toString().toUpperCase();

        editText3 = findViewById(R.id.oNamep);
        String OTHER_NAME = editText3.getText().toString().toUpperCase();

        editText4 = findViewById(R.id.address1p);
        String ADDRESS1 = editText4.getText().toString().toUpperCase();

        editText5 = findViewById(R.id.address2p);
        String ADDRESS2 = editText5.getText().toString().toUpperCase();

        editText6 = findViewById(R.id.townp);
        String TOWN = editText6.getText().toString().toUpperCase();

        editText7 = findViewById(R.id.statep);
        String STATE = editText7.getText().toString().toUpperCase();

        editText8 = findViewById(R.id.cityp);
        String CITY = editText8.getText().toString().toUpperCase();

        editText9 = findViewById(R.id.districtp);
        String DISTRICT = editText9.getText().toString().toUpperCase();

        editText10 = findViewById(R.id.countyp);
        String COUNTY = editText10.getText().toString().toUpperCase();

        editText11 = findViewById(R.id.provincep);
        String PROVINCE = editText11.getText().toString().toUpperCase();

        editText12 = findViewById(R.id.countryp);
        String COUNTRY = editText12.getText().toString().toUpperCase();

        editText13 = findViewById(R.id.dobp);
        String DATE_OF_BIRTH = editText13.getText().toString().toUpperCase();

        editText14 = findViewById(R.id.genderp);
        String GENDER = editText14.getText().toString().toUpperCase();

        editText15 = findViewById(R.id.passportp);
        String PASSPORT = editText15.getText().toString().toUpperCase();

        editText16 = findViewById(R.id.nationalidp);
        String NATIONAL_ID = editText16.getText().toString().toUpperCase();

        editText17 = findViewById(R.id.facebookp);
        String FACEBOOK = editText17.getText().toString().toUpperCase();

        editText18 = findViewById(R.id.twitterp);
        String TWITTER = editText18.getText().toString().toUpperCase();

        if (FIRST_NAME.isEmpty()){
            editText1.setError("name Required");
            editText1.requestFocus();
            return;
        }

        jUserDetails = new JSONObject();

        try {
            jUserDetails.put("firstname", FIRST_NAME);
            jUserDetails.put("lastname", LAST_NAME);
            jUserDetails.put("othernames", OTHER_NAME);
            jUserDetails.put("address1", ADDRESS1);
            jUserDetails.put("address2", ADDRESS2);
            jUserDetails.put("town", TOWN);
            jUserDetails.put("state", STATE);
            jUserDetails.put("city", CITY);
            jUserDetails.put("district", DISTRICT);
            jUserDetails.put("county", COUNTY);
            jUserDetails.put("province", PROVINCE);
            jUserDetails.put("country", COUNTRY);
            jUserDetails.put("dob", DATE_OF_BIRTH);
            jUserDetails.put("gender", GENDER);
            jUserDetails.put("passport_no", PASSPORT);
            jUserDetails.put("national_id_no", NATIONAL_ID);
            jUserDetails.put("facebook", FACEBOOK);
            jUserDetails.put("twitter", TWITTER);

        }catch (JSONException e){
            e.printStackTrace();

        }
        postUserProf postUserProf = new postUserProf(this);
        postUserProf.execute();
    }
    public static class postUserProf extends  AsyncTask<Void, Void, Response>{
        private WeakReference <UserProfileDetails> weakReference;

        postUserProf(UserProfileDetails userProfileDetails){
            weakReference = new WeakReference<>(userProfileDetails);
        }

        @Override
        protected Response doInBackground(Void... voids) {
            UserProfileDetails userProfileDetails = weakReference.get();
            String url = "/profiles";
            OkhttpConnection okConn = new OkhttpConnection();
            Response result = null;
            result = okConn.setProfileDetails(url, userProfileDetails.jUserDetails.toString(), userProfileDetails.sessionID);
            return result;
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            UserProfileDetails userProfileDetails = weakReference.get();
            if (response.code() == 200) {
                System.out.println("Response body json values are : " + response);
                Log.d("TAG", String.valueOf(response));
                Toast.makeText(userProfileDetails.getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                userProfileDetails.intent = new Intent(userProfileDetails.getApplicationContext(), profile.class);
                userProfileDetails.startActivity(userProfileDetails.intent);
            }
            else if (response.code() == 202){

                System.out.println("Response body json values are : " + response);
                Log.d("TAG", String.valueOf(response));
                Toast.makeText(userProfileDetails.getApplicationContext(), "Accepted for processing", Toast.LENGTH_LONG).show();
            }
            else {
                System.out.println("Response body json values are : " + response);
                Log.d("TAG", String.valueOf(response));
                Toast.makeText(userProfileDetails.getApplicationContext(), "error", Toast.LENGTH_LONG).show();
            }
        }
    }
}
