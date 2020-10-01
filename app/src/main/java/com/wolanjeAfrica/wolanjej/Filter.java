package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.ViewModels.ServicesViewModel;
import com.wolanjeAfrica.wolanjej.models.TranasactionHistory;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.TransactionRecyclerAdapter;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Filter extends Fragment {
    private static final String TAG = "Filter";
    private Toolbar toolbar;
    private ImageView imageView;
    private View v;
    private SharedPreferences pref;
    private String sessionID;
    private RecyclerView recyclerView;
    private TextView textView;
    private List<TranasactionHistory> historyList = new ArrayList<>();
    private TransactionRecyclerAdapter transactionRecyclerAdapter;
    public Filter() {
        // Required empty public constructor
    }

    public static Filter newInstance(String param1, String param2) {
        Filter fragment = new Filter();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = getContext().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_filter, container, false);
        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        imageView = (ImageView) v.findViewById(R.id.toolbarButton);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Home.class);
            startActivity(intent);
        });
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_history);

        RetrieveHistory();

        return v;
    }

    private void RetrieveHistory() {
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