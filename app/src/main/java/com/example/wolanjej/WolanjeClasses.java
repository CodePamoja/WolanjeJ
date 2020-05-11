//package com.example.wolanjej;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import okhttp3.Response;
//
//public class SharedPrefManager {
//    public static final String SHARED_PREF_NAME = "prefs";
//    private static SharedPrefManager mInstance;
//    private Context context;
//
//    public SharedPrefManager(Context context) {
//        this.context = context;
//    }
//
//    public static synchronized SharedPrefManager getInstance(Context context) {
//        if (mInstance == null) {
//            mInstance = new SharedPrefManager(context);
//        }
//        return mInstance;
//    }
//
//    //save token
//    public void saveLoginDetails(Response result) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(Constants.KEY_TOKEN, token);
//        editor.apply();
//    }
//
//
//    public void saveFirebaseToken(String firebase_token) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(Constants.FIREBASE_TOKEN, firebase_token);
//        editor.apply();
//    }
//
//
//    //for the constants, create a constant class file with static fields to store the values
//
//    public String getToken() {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(Constants.KEY_TOKEN, null);
//    }
//
//
//    public String getFirebaseToken() {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(Constants.FIREBASE_TOKEN, null);
//    }
//
//    //check whether a user is logged in
//    public boolean isLoggedIn() {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString("token", null) != null;
//    }
//    public void updateTokenSent(int tokenSent) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt(Constants.KEY_TOKEN_SENT, tokenSent);
//        editor.apply();
//    }
//
//    public int tokenSent() {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getInt(Constants.KEY_TOKEN_SENT, 0);
//    }
//
////you can remove your stored items one by one or just use editor.clear to clear the whole sharedpref
//
//    public void remove() {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove(Constants.FIREBASE_TOKEN);
//        editor.remove(Constants.KEY_TOKEN);
//        editor.apply();
//    }
//
//}