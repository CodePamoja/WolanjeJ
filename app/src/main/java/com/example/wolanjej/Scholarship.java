package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Scholarship extends AppCompatActivity {

    private AsyncTask<Void, Void, String> contrylist;
    private Toolbar tb;
    Spinner myspinner,spinnercounties;
    List<String> allNames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship);
        setToolBar(tb);

        spinnercounties = findViewById(R.id.spinnercounties);
        myspinner = findViewById(R.id.spinnercountries);


        OkHttpClient client = new OkHttpClient();
        final ArrayList countries = null;
        final Request request = new Request.Builder()
                .url("https://restcountries-v1.p.rapidapi.com/all")
                .get()
                .addHeader("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "f97e377fcfmsh3ae3e5c6fe84f32p173157jsnccf4b0b5cdf4")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Scholarship.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Scholarship.this, "Network fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                String contrydata = response.body().string();
                System.out.println(contrydata);
                try {
                    JSONArray jcountries = new JSONArray(contrydata);


                    for (int i = 0; i < jcountries.length(); i++) {
                        Log.e("data", String.valueOf(jcountries.getJSONObject(i).getString("name")));

                        allNames.add(jcountries.getJSONObject(i).getString("name"));
                    }
                    Scholarship.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            ArrayAdapter myadappter = new ArrayAdapter(Scholarship.this, android.R.layout.simple_spinner_item,allNames);

                            myadappter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                            myspinner.setAdapter(myadappter);

                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //ArrayAdapter<String> counties = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,R.array.user_select);
        //counties.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //spinnercounties.setAdapter(counties);


        Spinner spinner = (Spinner) findViewById(R.id.spinnercounties);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.counties_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnercounties.setAdapter(adapter);
    }
    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,Education_50.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }

    public void movetoscholar2(View view) {
        startActivity(new Intent(this,Scholarship02.class));
    }

}
