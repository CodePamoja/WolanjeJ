package com.wolanjeAfrica.wolanjej.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.R;
import com.wolanjeAfrica.wolanjej.ViewModels.ServicesViewModel;
import com.wolanjeAfrica.wolanjej.models.TranasactionHistory;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.TransactionRecyclerAdapter;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;


public class SentHistoryFragment extends Fragment {

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
    private TransactionRecyclerAdapter transactionRecyclerAdapter;

    public SentHistoryFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_history, container, false);
        progressBar = v.findViewById(R.id.progressHistoryFrag);
        textView = v.findViewById(R.id.txtNoHistory);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_history);
        RetrieveHistory();

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = getActivity().getApplication().getSharedPreferences("LogIn", Context.MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");
    }
    private void RetrieveHistory() {
        progressBar.setVisibility(View.VISIBLE);
        ServicesViewModel servicesViewModel = new ViewModelProvider(this).get(ServicesViewModel.class);
        servicesViewModel.getSentTransaction(getContext(), sessionID).observe(this, new Observer<List<TranasactionHistory>>() {
            @Override
            public void onChanged(List<TranasactionHistory> tranasactionHistories) {
                for (TranasactionHistory tranasactionHistory : tranasactionHistories) {
                    String month = gettingMonth(tranasactionHistory.getCreated_on());
                    String Day = gettingDay(tranasactionHistory.getCreated_on());
                    String NewStatus = getTheStatus(tranasactionHistory.getmSTATUS());
                    String pending = pendingStatus(tranasactionHistory.getmSTATUS());
                    String typeOfProduct = checkForTypeOfProduct(tranasactionHistory.getProduct_name());
                    String amount = tranasactionHistory.getmTOP_UP_AMOUNT();
                    historyList.add(new TranasactionHistory(month, Day,amount , tranasactionHistory.getmTRANSACTION_FEE(), "status : " + NewStatus, "pending : " + pending, typeOfProduct));
                    transactionRecyclerAdapter = new TransactionRecyclerAdapter(getContext(), historyList);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(transactionRecyclerAdapter);
                }
            }
        });
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