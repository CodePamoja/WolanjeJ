package com.example.wolanjej;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class WolenjeConnection {
    public Base64Encoder baseResult = new Base64Encoder();
    public ProgressDialog prgBar;
    private String msg;
    private Map<String, String> userFields;

    // variable to hold context
    private Context context;

//save the context recievied via constructor in a local variable

    public WolenjeConnection(Context context, String msg, Map<String, String> userFields){
        this.context=context;
        this.msg = msg;
        this.userFields = userFields;
    }


}
