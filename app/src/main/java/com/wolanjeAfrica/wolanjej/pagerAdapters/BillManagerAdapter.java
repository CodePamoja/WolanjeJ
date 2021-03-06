package com.wolanjeAfrica.wolanjej.pagerAdapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wolanjeAfrica.wolanjej.fragments.AllBillsFragment;
import com.wolanjeAfrica.wolanjej.fragments.PaidFragment;
import com.wolanjeAfrica.wolanjej.fragments.UnpaidFragment;

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
                return new AllBillsFragment();
            case 1:
                return new UnpaidFragment();
            case 2:
                return new PaidFragment();
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
