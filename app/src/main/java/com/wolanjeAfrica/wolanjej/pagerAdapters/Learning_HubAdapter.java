package com.wolanjeAfrica.wolanjej.pagerAdapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wolanjeAfrica.wolanjej.fragments.CategoryFragment;
import com.wolanjeAfrica.wolanjej.fragments.FeaturedFragment;
import com.wolanjeAfrica.wolanjej.fragments.FinanceFragment;
import com.wolanjeAfrica.wolanjej.fragments.InnovationFragment;

public class Learning_HubAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public Learning_HubAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FeaturedFragment();

            case 1:
                return new InnovationFragment();
            case 2:
                return new FinanceFragment();
            case 3:
                return new CategoryFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
