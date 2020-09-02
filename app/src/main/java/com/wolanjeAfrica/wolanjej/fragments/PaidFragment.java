package com.wolanjeAfrica.wolanjej.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.R;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.ApiJsonObjects;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.RetrofitClient;
import com.wolanjeAfrica.wolanjej.models.ListUserBills;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.AllBillRecyclerAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class PaidFragment extends Fragment {
    private List<ListUserBills> listUserBills = new ArrayList<>();
    private View v;
    private RecyclerView recyclerView;
    private TextView textView1, textView2;
    private SharedPreferences pref;
    private String sessionID;
    private ProgressBar progressBar;
    private String TAG = "PaidBillsFragment";

    public PaidFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            pref = getActivity().getApplication().getSharedPreferences("LogIn", Context.MODE_PRIVATE);
            this.sessionID = pref.getString("session_token", "");
        }
        getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_paid, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewPaidBills);
        progressBar = (ProgressBar) v.findViewById(R.id.progressIdFragPaid);
        textView1 = (TextView) v.findViewById(R.id.textCreateBill);
        textView2 = (TextView) v.findViewById(R.id.txtNoPaidBill);
        progressBar.setVisibility(View.VISIBLE);
        UserBillsMain();
        return v;

    }

    private void UserBillsMain() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + sessionID + "");

        Retrofit retrofit = RetrofitClient.getInstance();
        JsonPlaceHolders jsonPlaceHolders = retrofit.create(JsonPlaceHolders.class);
        Call<ApiJsonObjects> call = jsonPlaceHolders.getListOfBills(headers);
        call.enqueue(new Callback<ApiJsonObjects>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<ApiJsonObjects> call, retrofit2.Response<ApiJsonObjects> response) {
                progressBar.setVisibility(View.GONE);
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                listUserBills = response.body().getListUserBills();
                if (listUserBills.isEmpty()) {
                    textView1.setVisibility(View.VISIBLE);
                }

                /*
                collect list of paid list
                 */
                List<ListUserBills> unpaidList = listUserBills.stream()
                        .filter(listUserBills1 -> Integer.parseInt(listUserBills1.getAmount()) > 0)
                        .collect(Collectors.toList());

                if (unpaidList.isEmpty()) {
                    textView2.setVisibility(View.VISIBLE);
                }
                for (ListUserBills mListUserBills : unpaidList) {
                    String ProductName = mListUserBills.getProduct_name();
                    String date = mListUserBills.getCreated_on();
                    switch (ProductName) {
                        case "MPESA_B2C":
                        case "AIRTEL_B2C":
                        case "TKASH_B2C":
                            mListUserBills.setProduct_name("Phone Bill");
                            mListUserBills.setImageDrawable(R.drawable.ic_phone);
                            break;
                        case "KPLC_BILLPAY":
                            mListUserBills.setProduct_name("Electricity Bill");
                            mListUserBills.setImageDrawable(R.drawable.ic_bulb);
                            break;
                        case "NCWSC_BILLPAY":
                        case "KIWASCO_BILLPAY":
                            mListUserBills.setProduct_name("Water Bill");
                            mListUserBills.setImageDrawable(R.drawable.ic_drops);
                            break;
                        case "ZUKU_BILLPAY":
                            mListUserBills.setProduct_name("Internet Bill");
                            mListUserBills.setImageDrawable(R.drawable.ic_wifi);
                            break;
                    }

                    Log.d("TAG101", ProductName);
                    mListUserBills.setCreated_on(ChangeDate(date));

                    if (getActivity() != null) {
                        AllBillRecyclerAdapter allBillRecyclerAdapter = new AllBillRecyclerAdapter(getActivity(), listUserBills);
                        int numberOfColumns = 2;
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
                        recyclerView.setAdapter(allBillRecyclerAdapter);
                    }
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