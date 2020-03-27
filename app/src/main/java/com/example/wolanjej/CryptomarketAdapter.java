package com.example.wolanjej;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CryptomarketAdapter extends FragmentPagerAdapter {
    private Context context;
    int     totalTabs;

    public CryptomarketAdapter(@NonNull Context context,FragmentManager fm,int totalTabs) {
        super(fm);
        context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
//    fragments of tabs
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CryptoMarketFragment cryptoMarketFragment = new CryptoMarketFragment();
                return  cryptoMarketFragment;
            case 1:
                LiveRateFragment liveRateFragment = new LiveRateFragment();
                return liveRateFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
