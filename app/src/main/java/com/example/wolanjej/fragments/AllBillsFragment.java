package com.example.wolanjej.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wolanjej.R;
import com.example.wolanjej.RetrofitUtils.ApiJsonObjects;
import com.example.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.example.wolanjej.RetrofitUtils.RetrofitClient;
import com.example.wolanjej.models.ListUserBills;
import com.example.wolanjej.recyclerAdapters.AllBillRecyclerAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class AllBillsFragment extends Fragment {
    private List<ListUserBills> listUserBills = new ArrayList<>();
    private View v;
    private RecyclerView recyclerView;
    private SharedPreferences pref;
    private String sessionID;


    public AllBillsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = getActivity().getApplication().getSharedPreferences("LogIn", Context.MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        UserBillsMain();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_all, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewAllBills);
        return v;

    }

    private void UserBillsMain() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + sessionID + "");

        Retrofit retrofit = RetrofitClient.getInstance();
        JsonPlaceHolders jsonPlaceHolders = retrofit.create(JsonPlaceHolders.class);
        Call<ApiJsonObjects> call = jsonPlaceHolders.getListOfBills(headers);
        call.enqueue(new Callback<ApiJsonObjects>() {
            @Override
            public void onResponse(Call<ApiJsonObjects> call, retrofit2.Response<ApiJsonObjects> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                listUserBills = response.body().getListUserBills();
                for (ListUserBills mListUserBills : listUserBills) {
                    String ProductName = mListUserBills.getProduct_name().toString();
                    String date = mListUserBills.getCreated_on();
                    if (ProductName.equals("MPESA_B2C") || ProductName.equals("AIRTEL_B2C") || ProductName.equals("TKASH_B2C"))
                        mListUserBills.setProduct_name("Phone Bill");
                    mListUserBills.setImageDrawable(R.drawable.ic_phone);
                    Log.d("TAG101", ProductName);
                    mListUserBills.setCreated_on(ChangeDate(date));

                    AllBillRecyclerAdapter allBillRecyclerAdapter = new AllBillRecyclerAdapter(getContext(), listUserBills);
                    int numberOfColumns = 2;
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
                    recyclerView.setAdapter(allBillRecyclerAdapter);
                }

            }

            @Override
            public void onFailure(Call<ApiJsonObjects> call, Throwable t) {
                Toast.makeText(getActivity(), "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String ChangeDate(String date) {
        long date1 = Long.parseLong(date);
        Date date2 = new Date((long) date1 * 1000);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return String.valueOf(dateFormat.format(date2).substring(0, 11));
    }

}
