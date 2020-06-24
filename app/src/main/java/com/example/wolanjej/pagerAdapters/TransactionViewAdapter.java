package com.example.wolanjej.pagerAdapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wolanjej.fragments.HistoryFragment;
import com.example.wolanjej.fragments.SentTransactionFragment;

public class TransactionViewAdapter extends FragmentPagerAdapter {
    private Context context;
    int     totalTabs;

    public TransactionViewAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                SentTransactionFragment sentTransactionFragment = new SentTransactionFragment();
                return sentTransactionFragment;
            case 1:
                HistoryFragment historyFragment = new HistoryFragment();
                return historyFragment;

        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
