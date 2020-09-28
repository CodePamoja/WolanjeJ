package com.wolanjeAfrica.wolanjej.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.wolanjeAfrica.wolanjej.OkhttpConnection;
import com.wolanjeAfrica.wolanjej.R;
import com.wolanjeAfrica.wolanjej.models.TranasactionHistory;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.TransactionRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;


public class HistoryFragment extends Fragment {

    private View v;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private SharedPreferences pref;
    private List<TranasactionHistory> historyList = new ArrayList<>();
    private String sessionID;
    private String USERID;
    private String USERNAME;
    private String AGENTNO;
    private String MY_BALANCE;
    private TextView textView;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_history, container, false);
        progressBar = v.findViewById(R.id.progressHistoryFrag);
        textView = v.findViewById(R.id.txtNoHistory);
        progressBar.setVisibility(View.VISIBLE);
        UserServices userServices = new UserServices(this);
        userServices.execute();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = getActivity().getApplication().getSharedPreferences("LogIn", Context.MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");
    }


    public static class UserServices extends AsyncTask<Void, Void, Response> {
        private WeakReference<HistoryFragment> weakReference;

        UserServices(HistoryFragment historyFragment) {
            weakReference = new WeakReference<>(historyFragment);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            HistoryFragment historyFragment = weakReference.get();
            historyFragment.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Response doInBackground(Void... voids) {

            HistoryFragment historyFragment = weakReference.get();
            historyFragment.progressBar.setVisibility(View.VISIBLE);
            String url = "/api/services";
            OkhttpConnection okConn = new OkhttpConnection(); // calling the okhttp connection class here
            Response result = okConn.getBalance(url, historyFragment.sessionID);// sending the url string and base 64 results to the okhttp connection and it's method is getLogin
            Log.d("TAG", String.valueOf(result));
            return result;
        }

        @Override
        protected void onPostExecute(Response result) {
            HistoryFragment historyFragment = weakReference.get();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String verifyResult = null;
                    if (result != null && result.code() == 200) {
                        try {
                            String test = result.body().string();
                            Log.d("TAG test", test);
                            JSONObject JService = new JSONObject(test);
                            System.out.println("Response body json values  services are : " + JService);
                            JSONArray services = JService.getJSONArray("services");


                            for (int i = 0; i < services.length(); i++) {
                                JSONObject json_data = services.getJSONObject(i);
                                String date = json_data.getString("created_on");//,"created_on":"2020-07-17 12:25:50"
                                String status = json_data.getString("status");
                                String product = json_data.getString("product_name");

                                String Month = historyFragment.gettingMonth(date);
                                String Day = historyFragment.gettingDay(date);
                                String NewStatus = historyFragment.getTheStatus(status);
                                String pending = historyFragment.pendingStatus(status);
                                String typeOfProduct = historyFragment.checkForTypeOfProduct(product);

                                historyFragment.historyList.add(new TranasactionHistory(Month, Day, json_data.getString("amount"), json_data.getString("fee"), "status: " + NewStatus, "pending:" + pending, typeOfProduct));
                            }
                            if (historyFragment.getActivity() == null) {
                                historyFragment.getActivity();
                                return;
                            }
                            historyFragment.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if (services.length() == 0) {
                                        historyFragment.progressBar.setVisibility(View.GONE);
                                        historyFragment.textView.setVisibility(View.VISIBLE);
                                        return;
                                    }
                                    historyFragment.progressBar.setVisibility(View.GONE);
                                    historyFragment.recyclerView = (RecyclerView) historyFragment.v.findViewById(R.id.recyclerview_history);
                                    TransactionRecyclerAdapter transactionRecyclerAdapter = new TransactionRecyclerAdapter(historyFragment.getContext(), historyFragment.historyList);
                                    historyFragment.recyclerView.setLayoutManager(new LinearLayoutManager(historyFragment.getActivity()));
                                    historyFragment.recyclerView.setAdapter(transactionRecyclerAdapter);
                                }
                            });


                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }

                    } else if (result != null && result.code() != 201) {
                        try {
                            verifyResult = result.body().string();
                            JSONObject jBody = new JSONObject(verifyResult); // adding
                            System.out.println("Response body json values are : " + verifyResult);
                            Log.e("TAG", String.valueOf(verifyResult));
                            Log.e("TAG result value", String.valueOf(result));
                            Log.e("TAG result body", verifyResult);
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Snackbar.make(historyFragment.v.findViewById(R.id.constarintHistoryFrag), "Something went wrong", Snackbar.LENGTH_LONG).show();
                    }

                }
            }).start();

        }
    }

    private String gettingMonth(String date) {

        char chara1 = date.charAt(5);
        char chara2 = date.charAt(6);
        String sb = String.valueOf(chara1) +
                chara2;
        int monthInt = Integer.parseInt(sb);
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        return dateFormatSymbols.getShortMonths()[monthInt - 1];
    }

    private String gettingDay(String date) {
        char chara1 = date.charAt(8);
        char chara2 = date.charAt(9);
        return String.valueOf(chara1) +
                chara2;
    }

    private String getTheStatus(String status) {
        String newStatus;

        switch (status) {
            case "TRX_OK":
                newStatus = "successful";
                return newStatus;
            case "TRX_ASYNC":
                newStatus = "processing";
                return newStatus;
            case "TRX_SCHED":
                newStatus = "queued";
                return newStatus;
            case "TRX_INSUFFICIENT_BALANCE":
                return "Insufficient Funds";
            default:
                return "failed";
        }

    }

    private String pendingStatus(String status) {
        if (status.equals("TRX_OK")) {
            return "0";
        }
        if (status.equals("TRX_SCHED")) {
            return "1";
        } else {
            return "0";
        }

    }

    private String checkForTypeOfProduct(String product) {

        switch (product) {
            case "MPESA_B2C":
            case "TKASH_B2C":
            case "AIRTEL_B2C":
            case "SAF_ATP":
            case "AIRTEL_ATP":
            case "TKASH_ATP":
                return "phone";
            case "WALLET_XFER":
            case "BANK_XFER":
                return "transfers";
            case "KPLC_VOUCHER":
            case "KPLC_BILLPAY":
                return "electricity";
            case "ZUKU_BILLPAY":
                return "internet";
            case "STARTIMES_BILLPAY":
            case "DSTV_BILLPAY":
            case "GOTV_BILLPAY":
                return "tv";
            case "NCWSC_BILLPAY":
            case "KIWASCO_BILLPAY":
                return "water";
            default:
                return "normal";
        }
    }
}