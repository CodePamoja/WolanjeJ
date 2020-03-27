package com.example.wolanjej;

import android.util.Log;

import java.io.IOException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpConnection {
    String baseUrl= "https://backoffice.wolenjeafrica.com/wolenje";
//    OkHttpClient client = new OkHttpClient();
    OkHttpClient clientSSL = null;
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  Response postRequest(String url, String jsonbody){
        Response result = null;
        clientSSL = getUnsafeOkHttpClient();
        try {
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonbody);
            String allUrl = baseUrl + url;
            Log.e("TAG", allUrl );
            Request request = new Request.Builder()
                    .url(allUrl)
                    .post(body)
                    .build();

            Call call = clientSSL.newCall(request);
            Response response = call.execute();
            Log.e("TAG", String.valueOf(response.code()));
            result  = response;

        } catch (IOException ex) {
            System.out.println("IO Error : " + ex);
            Log.d("TAG", String.valueOf(ex));
        }

        return result;
    }

    public  Response postPin(String url, String jsonBody, String sessionID){
        Response result = null;
        clientSSL = getUnsafeOkHttpClient();
        try {
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
            String allUrl = baseUrl + url;
            Request request = new Request.Builder()
                    .header("X-Requested-With", "XMLHttpRequest")
                    .header("X-Authorization", "Bearer"+sessionID+"" )
                    .url(allUrl)
                    .post(body)
                    .build();

            Call call = clientSSL.newCall(request);
            Response response = call.execute();
            String test = response.body().string();
            Log.d("TAG", test);
            result  = response;

        } catch (IOException ex) {
            System.out.println("IO Error : " + ex);
            Log.d("TAG", String.valueOf(ex));
        }

        return result;
    }
}
