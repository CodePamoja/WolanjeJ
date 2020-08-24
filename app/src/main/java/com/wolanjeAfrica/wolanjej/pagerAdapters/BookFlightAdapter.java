package com.wolanjeAfrica.wolanjej.pagerAdapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wolanjeAfrica.wolanjej.fragments.BookFragment;
import com.wolanjeAfrica.wolanjej.fragments.MyBookingsFragment;

public class BookFlightAdapter extends FragmentPagerAdapter {
    private Context context;
    int     totalTabs;

    public BookFlightAdapter(@NonNull Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                BookFragment bookFragment = new BookFragment();
                return bookFragment;
            case 1:
                MyBookingsFragment myBookingsFragment = new MyBookingsFragment();
                return myBookingsFragment;

        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
