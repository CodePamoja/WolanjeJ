package com.wolanjeAfrica.wolanjej.pagerAdapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wolanjeAfrica.wolanjej.fragments.fragment_qr_code;
import com.wolanjeAfrica.wolanjej.fragments.fragment_scan_code;

public class Qr_codeAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;
    public Qr_codeAdapter(Context context, @NonNull FragmentManager fm, int totalTabs) {
        super(fm);
        this.context  = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new fragment_qr_code();
            case 1:
                return new fragment_scan_code();

        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
