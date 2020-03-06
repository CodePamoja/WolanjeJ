package com.example.wolanjej;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;

public class MyHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
    Context context = itemView.getContext();
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
        itemView.setClickable(true);
        itemView.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        final Intent intent;
        switch (getAdapterPosition()){
            case 0:
                intent =  new Intent(context, screen18.class);
                break;

            case 1:
                intent =  new Intent(context, Registration06.class);
                break;
            case 2:
                intent = new Intent(context, Home.class);
                break;

            default:
                intent =  new Intent(context, Registration07.class);
                break;
        }
        context.startActivity(intent);
    }
}
