package com.example.wolanjej;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SentTransactionFragment extends Fragment {


    View v;
    private RecyclerView recyclerView;
    private List<SentTransactionHistory> sentList;

    public SentTransactionFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.transaction_row_view, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_sent);
        SentRecyclerAdapter sentRecyclerAdapter = new SentRecyclerAdapter(getContext(), sentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(sentRecyclerAdapter);
        return v;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sentList = new ArrayList<>();
        sentList.add(new SentTransactionHistory("Aug", "04", R.mipmap.bulb_63_1, "John", "+254748188544","Credit Card", "1000", "10.00"));
        sentList.add(new SentTransactionHistory("Nov", "24", R.mipmap.bulb_63_1, "Ivy", "+254748188544","Credit Card", "2000", "20.00"));
    }
}
