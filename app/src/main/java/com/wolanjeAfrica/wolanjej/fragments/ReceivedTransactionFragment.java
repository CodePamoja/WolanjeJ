package com.wolanjeAfrica.wolanjej.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.R;
import com.wolanjeAfrica.wolanjej.ViewModels.UserBalanceViewModel;
import com.wolanjeAfrica.wolanjej.models.BalanceModel;
import com.wolanjeAfrica.wolanjej.models.TranasactionHistory;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.ReceivedRecyclerAdapter;
import com.wolanjeAfrica.wolanjej.models.SentTransactionHistory;

import java.util.ArrayList;
import java.util.List;

public class ReceivedTransactionFragment extends Fragment {


    private View v;
    private RecyclerView recyclerView;
    private List<TranasactionHistory> sentList;
    private String sessionID;
    private String AGENTNO;
    private SharedPreferences pref;
    private TextView textView;
    private static String MY_BALANCE;

    public ReceivedTransactionFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.transaction_row_view, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_sent);
        ReceivedRecyclerAdapter receivedRecyclerAdapter = new ReceivedRecyclerAdapter(getContext(), sentList);
        textView = (TextView) v.findViewById(R.id.txt_hist_amount);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(receivedRecyclerAdapter);
        setUserBalance();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = getActivity().getApplication().getSharedPreferences("LogIn", Context.MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");

        TranasactionHistory tranasactionHistory= new TranasactionHistory();
        tranasactionHistory.setmMONTHS("11");
        tranasactionHistory.setmDATE("02");
        tranasactionHistory.setmTOP_UP_AMOUNT("100");
        tranasactionHistory.setmTRANSACTION_FEE("01");
        tranasactionHistory.setmSTATUS("status :"+" success");
        tranasactionHistory.setmPENDING("pending :"+" 0");

        sentList = new ArrayList<>();
        sentList.add(tranasactionHistory);

    }
    private void setUserBalance() {
        UserBalanceViewModel userBalanceViewModel = new ViewModelProvider(this).get(UserBalanceViewModel.class);
        userBalanceViewModel.getUserBalance(v.getContext(), sessionID).observe(this, new Observer<List<BalanceModel>>() {
            @Override
            public void onChanged(List<BalanceModel> balanceModels) {
                for (BalanceModel b : balanceModels) {
                    MY_BALANCE = b.getBalance();
                    textView.setText("KES" + MY_BALANCE);
                }
            }
        });
    }
}
