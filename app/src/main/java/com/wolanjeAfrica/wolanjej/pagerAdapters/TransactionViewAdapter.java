package com.wolanjeAfrica.wolanjej.pagerAdapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wolanjeAfrica.wolanjej.fragments.SentHistoryFragment;
import com.wolanjeAfrica.wolanjej.fragments.ReceivedTransactionFragment;

public class TransactionViewAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public TransactionViewAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SentHistoryFragment();
            case 1:
                return new ReceivedTransactionFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
