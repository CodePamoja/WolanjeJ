package com.wolanjeAfrica.wolanjej.pagerAdapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wolanjeAfrica.wolanjej.fragments.FailedloansFragment;
import com.wolanjeAfrica.wolanjej.fragments.LoansHistoryFragment;
import com.wolanjeAfrica.wolanjej.fragments.PaidLoansFragment;

public class LoansAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public LoansAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LoansHistoryFragment();
            case 1:
                return new PaidLoansFragment();
            case 2:
                return new FailedloansFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

}
