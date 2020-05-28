package com.example.wolanjej;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private List<TranasactionHistory> historyList;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_history);
        TransactionRecyclerAdapter transactionRecyclerAdapter = new TransactionRecyclerAdapter(getContext(), historyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(transactionRecyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        historyList = new ArrayList<>();
        historyList.add(new TranasactionHistory("Aug", "33", "100", "30", "Status: Successful", "pending: 0" ));
        historyList.add(new TranasactionHistory("Aug", "33", "100", "30", "Status: Successful", "pending: 0" ));
        historyList.add(new TranasactionHistory("Aug", "33", "100", "30", "Status: Successful", "pending: 0" ));
    }
}