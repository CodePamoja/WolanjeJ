package com.example.wolanjej;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wolanjej.fragments.FailedloansFragment;
import com.example.wolanjej.fragments.HistoryFragment;
import com.example.wolanjej.fragments.PaidLoansFragment;

public class LoansAdapter extends FragmentStateAdapter {
    public LoansAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HistoryFragment();
            case 1:
                return new PaidLoansFragment();
            default:
                return new FailedloansFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
