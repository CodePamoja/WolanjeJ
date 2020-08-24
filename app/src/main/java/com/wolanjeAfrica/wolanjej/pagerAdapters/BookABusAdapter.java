package com.wolanjeAfrica.wolanjej.pagerAdapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wolanjeAfrica.wolanjej.fragments.FragmentBookABus;
import com.wolanjeAfrica.wolanjej.fragments.FragmentMyBusBookings;

public class BookABusAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public BookABusAdapter(Context context, @NonNull FragmentManager fm, int totalTabs) {
        super(fm);
        context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentBookABus fragmentBookABus = new FragmentBookABus();
                return fragmentBookABus;
            case 1:
                FragmentMyBusBookings fragmentMyBusBookings = new FragmentMyBusBookings();
                return fragmentMyBusBookings;

        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
