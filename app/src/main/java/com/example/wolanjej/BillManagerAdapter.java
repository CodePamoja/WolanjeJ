package com.example.wolanjej;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BillManagerAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public BillManagerAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllFragment allFragment = new AllFragment();
                return allFragment;
            case 1:
                UnpaidFragment unpaidFragment = new UnpaidFragment();
                return unpaidFragment;
            case 2:
                PaidFragment paidFragment = new PaidFragment();
                return paidFragment;
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
