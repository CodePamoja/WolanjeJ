package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

public class UserProfileDetails extends AppCompatActivity {
    private String sessionID = null;
    private SharedPreferences pref;
    private Button button;
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
        String LAST_NAME = editText2.getText().toString();

        editText3 = findViewById(R.id.oNamep);
        String OTHER_NAME = editText3.getText().toString();

        editText4 = findViewById(R.id.address1p);
        String ADDRESS1 = editText4.getText().toString();

        editText5 = findViewById(R.id.address2p);
        String ADDRESS2 = editText5.getText().toString();

        editText6 = findViewById(R.id.townp);
        String TOWN = editText6.getText().toString();

        editText7 = findViewById(R.id.statep);
        String STATE = editText7.getText().toString();

        editText8 = findViewById(R.id.cityp);
        String CITY = editText8.getText().toString();

        editText9 = findViewById(R.id.districtp);
        String DISTRICT = editText9.getText().toString();

        editText10 = findViewById(R.id.countyp);
        String COUNTY = editText10.getText().toString();

        editText11 = findViewById(R.id.provincep);
        String PROVINCE = editText11.getText().toString();

        editText12 = findViewById(R.id.countryp);
        String COUNTRY = editText12.getText().toString();

        editText13 = findViewById(R.id.dobp);
        String DATE_OF_BIRTH = editText13.getText().toString();

        editText14 = findViewById(R.id.genderp);
        String GENDER = editText14.getText().toString();

        editText15 = findViewById(R.id.passportp);
        String PASSPORT = editText15.getText().toString();

        editText16 = findViewById(R.id.nationalidp);
        String NATIONAL_ID = editText16.getText().toString();

        editText17 = findViewById(R.id.facebookp);
        String FACEBOOK = editText17.getText().toString();

        editText18 = findViewById(R.id.twitterp);
        String TWITTER = editText18.getText().toString();

        JSONObject jUserDetails = new JSONObject();

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
        String url = "/profiles";
        OkhttpConnection okConn = new OkhttpConnection();
        Response result = null;
        result = okConn.setProfileDetails(url, jUserDetails.toString(), sessionID);

        int responseCode = 0;
        if ((responseCode = result.code()) == 200){
            System.out.println("Response body json values are : " + result);
            Log.d("TAG", String.valueOf(result));
            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
        }
        else if ((responseCode = result.code()) == 202){

            System.out.println("Response body json values are : " + result);
            Log.d("TAG", String.valueOf(result));
            Toast.makeText(getApplicationContext(), "Accepted for processing", Toast.LENGTH_LONG).show();
        }
        else {
            System.out.println("Response body json values are : " + result);
            Log.d("TAG", String.valueOf(result));
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
        }
    }
}
