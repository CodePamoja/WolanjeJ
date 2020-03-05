package com.example.wolanjej;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpConnection {
    String baseUrl= "https://backoffice.wolenjeafrica.com/wolenje/gapi";
    OkHttpClient client = new OkHttpClient();

    public  String postRequest(String jsonbody){
        String result = null;
        try {
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonbody);

            Request request = new Request.Builder()
                    .url(baseUrl)
                    .post(body)
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();
            result = response.body().string();     // Get response
            int responseCode = 0;

            if ((responseCode = response.code()) == 200) {
                System.out.println("Response body json values are : " + result);
                Log.d("TAG",result);

            }else if((responseCode = response.code()) != 200) {
                Log.d("TAG",result);
            }


        } catch (IOException ex) {
            System.out.println("IO Error : " + ex);
        }

        return result;
    }
}
