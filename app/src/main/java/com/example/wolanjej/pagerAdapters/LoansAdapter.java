package com.example.wolanjej.pagerAdapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wolanjej.fragments.FailedloansFragment;
import com.example.wolanjej.fragments.HistoryFragment;
import com.example.wolanjej.fragments.LoansHistoryFragment;
import com.example.wolanjej.fragments.PaidLoansFragment;
import com.example.wolanjej.fragments.SentTransactionFragment;

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
