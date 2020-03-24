package com.example.wolanjej;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

public class Sendtover implements Runnable {
   String phone ;
   Thread t;

    public Sendtover(String phone) {
        this.phone = phone;
        new Thread(this, phone);
        t.start();
    }
    public String sendToVerification() {
        String response = "Nothing new";
        
        JSONObject jPhone = new JSONObject();
        try {
            jPhone.put("phone", "254"+phone);
            Log.e("jPhone",jPhone.toString());
        } catch (JSONException e) {
            Log.e("Error",e.toString());
            e.printStackTrace();
        }
        String url = "/gapi/sendOTP";
        OkhttpConnection okConn = new OkhttpConnection();
        Response result = okConn.postRequest(url,jPhone.toString());

        if ( result.code() == 201) {
            response = "OK  \n"+result;
           // Toast.makeText(getApplicationContext(), "Phone number sent successfuly", Toast.LENGTH_LONG).show();
            new Registration05().verifyOTP(phone);
            return response;
        }else if(result.code() != 201) {
            response = "Failed  \n"+result;
            return response;
        }
        return response;

    }



    @Override
    public void run() {


    }
}
