package com.example.wolanjej.pagerAdapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wolanjej.fragments.AllBillsFragment;
import com.example.wolanjej.fragments.UnpaidFragment;

public class TransactionMangerAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public TransactionMangerAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllBillsFragment allBillsFragment = new AllBillsFragment();
                return allBillsFragment;
            case 1:
                UnpaidFragment unpaidFragment = new UnpaidFragment();
                return unpaidFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
