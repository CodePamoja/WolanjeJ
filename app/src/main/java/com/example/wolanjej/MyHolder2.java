package com.example.wolanjej;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder2 extends RecyclerView.ViewHolder implements  View.OnClickListener{
    public static CardView mColor;
    public View View;
    Context context = itemView.getContext();
    public static TextView mtitle;
    public static TextView mtitle2;
    public static TextView mtitle3;
    public static TextView mtitle4;
    public  static ImageView mImageView;

    public MyHolder2(@NonNull View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.item_image1);
        mtitle=itemView.findViewById(R.id.item_title);
        mColor = itemView.findViewById(R.id.carditem2);
        mtitle2 = itemView.findViewById(R.id.item_title2);
        mtitle3 = itemView.findViewById(R.id.item_title3);
        mtitle4 = itemView.findViewById(R.id.item_title4);
        itemView.setClickable(true);
        itemView.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        final Intent intent;
        switch (getAdapterPosition()){
            case 0:
                intent =  new Intent(context, registration08.class);
                break;

            case 1:
                intent =  new Intent(context, Registration06.class);
                break;
            case 2:
                intent =  new Intent(context, Home.class);
                break;
            default:
                intent =  new Intent(context, Registration07.class);
                break;
        }
        context.startActivity(intent);

    }
};