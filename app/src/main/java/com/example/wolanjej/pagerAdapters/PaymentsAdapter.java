package com.example.wolanjej.pagerAdapters;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wolanjej.fragments.BulkPaymentFragment;
import com.example.wolanjej.fragments.PaymentsFragment;

public class PaymentsAdapter extends FragmentPagerAdapter {

    private Context context;
    int     totalTabs;

    public PaymentsAdapter(@NonNull Context context,FragmentManager fm,int totalTabs) {
        super(fm);
        context = context;
        this.totalTabs=totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                BulkPaymentFragment bulkPaymentFragment = new BulkPaymentFragment();
                return bulkPaymentFragment;
            case 1:
                PaymentsFragment paymentsFragment = new PaymentsFragment();
                return  paymentsFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
