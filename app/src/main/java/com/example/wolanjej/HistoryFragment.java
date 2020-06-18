package com.example.wolanjej;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Response;


public class HistoryFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private SharedPreferences pref;
    private List<TranasactionHistory> historyList = new ArrayList<>();
    private String sessionID;
    private String USERID;
    private String USERNAME;
    private String AGENTNO;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_history, container, false);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = getActivity().getApplication().getSharedPreferences("LogIn", Context.MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");


        new UserServices().execute();
    }

    public class UserServices extends AsyncTask<Void, Void, Response> {

        @Override
        protected Response doInBackground(Void... voids) {

            String url = "/api/services";
            OkhttpConnection okConn = new OkhttpConnection(); // calling the okhttp connection class here
            Response result = okConn.getBalance(url, sessionID);// sending the url string and base 64 results to the okhttp connection and it's method is getLogin
            Log.d("TAG", String.valueOf(result));
            return result;
        }

        @Override
        protected void onPostExecute(Response result) {
            String verifyResult = null;
            if (result.code() == 200) {
                try {
                    String test = result.body().string();
                    Log.d("TAG test", test);
                    JSONObject JService = new JSONObject(test);
                    System.out.println("Response body json values  services are : " + JService);
                    JSONArray services = JService.getJSONArray("services");

                    for (int i = 0; i < services.length(); i++) {
                        JSONObject json_data = services.getJSONObject(i);
                        String date = json_data.getString("created_on");
                        String status = json_data.getString("status");

                        String Month = gettingMonth(date);
                        String Day = gettingDay(date);
                        String NewStatus = getTheStatus(status);

                        historyList.add(new TranasactionHistory(Month, Day, json_data.getString("amount"), json_data.getString("fee"), "status: "+NewStatus, json_data.getString("status")));
                    }
                    recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_history);
                    TransactionRecyclerAdapter transactionRecyclerAdapter = new TransactionRecyclerAdapter(getContext(), historyList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(transactionRecyclerAdapter);


                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            } else if (result.code() != 201) {
                try {
                    verifyResult = result.body().string();
                    JSONObject jBody = new JSONObject(verifyResult); // adding
                    System.out.println("Response body json values are : " + verifyResult);
                    Log.e("TAG", String.valueOf(verifyResult));
//                    String sendResutls = jBody.getJSONObject("errors").getJSONObject("otp").getJSONArray("otp").getJSONArray(0).getString(2);
//                    Log.e("TAG", String.valueOf(sendResutls));
//                    Toast.makeText(getApplicationContext(), "Phone Number, "+sendResutls, Toast.LENGTH_LONG).show();
                    Log.e("TAG result value", String.valueOf(result));
                    Log.e("TAG result body", verifyResult);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String gettingMonth(String date) {

        long datelong = Long.parseLong(date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String[] formattedDate = sdf.format(new Date(datelong)).split("/");

        String Month = formattedDate[1];
        int monthInt = Integer.parseInt(Month.trim());
        String monthInName = new DateFormatSymbols().getShortMonths()[monthInt - 1];
        String Date = formattedDate[0];

        return monthInName;
    }

    private String gettingDay(String date) {

        long datelong = Long.parseLong(date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String[] formattedDate = sdf.format(new Date(datelong)).split("/");
        return formattedDate[0];
    }

    private String getTheStatus(String status) {
        String TRX_OK, TRX_ASYNC, TRX_SCHED;
        String newStatus = null;
        try {
            if (status.equals("TRX_OK")) {
                newStatus = "successful";
                return newStatus;
            }
            if (status.equals("TRX_ASYNC")) {
                newStatus = "processing";
                return newStatus;
            }
            if (status.equals("TRX_SCHED")) {
                newStatus = "queued";
                return newStatus;
            }
            if(status.equals("TRX_INSUFFICIENT_BALANCE")){
                newStatus = "Insufficient Funds";
                return newStatus;
            }else{
                newStatus = "failed";
                return newStatus;
            }
        }catch (Exception e){
            Toast.makeText(getActivity(),"something went wrong"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return newStatus;
    }
}