package com.wolanjeAfrica.wolanjej.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wolanjeAfrica.wolanjej.R;

public class FragmentMyBusBookings extends Fragment {
    public FragmentMyBusBookings(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_bus_bookings, container, false);
    }
}
