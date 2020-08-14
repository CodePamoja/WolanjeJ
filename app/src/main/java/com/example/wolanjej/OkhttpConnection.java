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
    String baseUrl = "https://wolenjeafrica.com/wolenje";
    OkHttpClient client = new OkHttpClient();

    public Response postRequest(String url, String jsonBody) {
        Response result = null;
        try {
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
            String allUrl = baseUrl + url;
            Log.e("TAG", allUrl);
            Request request = new Request.Builder()
                    .url(allUrl)
                    .post(body)
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();
            Log.e("TAG", String.valueOf(response.code()));
            result = response;

        } catch (IOException ex) {
            System.out.println("IO Error : " + ex);
            Log.d("TAG", String.valueOf(ex));
        }

        return result;
    }

    public Response postValue(String url, String jsonBody, String sessionID) {
        Response result = null;
        try {
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
            String allUrl = baseUrl + url;
            Request request = new Request.Builder()
                    .header("Authorization", "Bearer " + sessionID + "")
                    .url(allUrl)
                    .post(body)
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();
            result = response;

        } catch (IOException ex) {
            System.out.println("IO Error : " + ex);
            Log.d("TAG", String.valueOf(ex));
        }

        return result;
    }


    public Response payBill(String url, String jsonBody, String sessionID) {
        Response result = null;
        try {
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
            String allUrl = baseUrl + url;
            Request request = new Request.Builder()
                    .header("Authorization", "Bearer " + sessionID + "")
                    .url(allUrl)
                    .post(body)
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();
            result = response;

        } catch (IOException ex) {
            System.out.println("IO Error : " + ex);
            Log.d("TAG", String.valueOf(ex));
        }

        return result;
    }

    public Response getLogin(String url, String base64Results) {
        Response result = null;
        try {
            String allUrl = baseUrl + url;
            Request request = new Request.Builder()
                    .header("Authorization", "Basic " + base64Results + "")
                    .get()
                    .url(allUrl)
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();
//            String test = response.body().string();
//            Log.d("TAG", test);
            result = response;

        } catch (IOException ex) {
            System.out.println("IO Error : " + ex);
            Log.d("TAG", String.valueOf(ex));
        }

        return result;
    }

    public Response getBalance(String url, String sessionID) {
        Response result = null;
        try {
            String allUrl = baseUrl + url;
            Request request = new Request.Builder()
                    .header("Authorization", "Bearer " + sessionID + "")
                    .get()
                    .url(allUrl)
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();
//            String test = response.body().string();
//            Log.d("TAG", test);
            result = response;

        } catch (IOException ex) {
            System.out.println("IO Error : " + ex);
            Log.d("TAG", String.valueOf(ex));
        }

        return result;
    }

    public Response setProfileDetails(String url, String jsonBody, String sessionID) {
        Response result = null;
        try {
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
            String allUrl = baseUrl + url;
            Request request = new Request.Builder()
                    .header("Authorization", "Bearer" + sessionID)
                    .post(body)
                    .url(allUrl)
                    .build();
            Call call = client.newCall(request);
            Response response = call.execute();
            result = response;

        } catch (IOException ex) {
            System.out.println("IO Error : " + ex);
            Log.d("TAG", String.valueOf(ex));
        }

        return result;
    }
}