package com.example.wolanjej;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;

public class MyHolder extends RecyclerView.ViewHolder {
    @SuppressLint("StaticFieldLeak")
    public static TextView mTitle;
    @SuppressLint("StaticFieldLeak")
    public static ImageView mImageView;
//    ImageView mImageView;
//    TextView mTitle;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        mImageView = itemView.findViewById(R.id.item_image);
        mTitle=itemView.findViewById(R.id.item_title);
    }
}
