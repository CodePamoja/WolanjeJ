package com.example.wolanjej.recyclerAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wolanjej.Home;
import com.example.wolanjej.models.Model;
import com.example.wolanjej.R;
import com.example.wolanjej.Registration06;
import com.example.wolanjej.Registration07;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private static final String TAG = "CustomAdapter";
    Context context;
    ArrayList<Model> models;

    public MyAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.mTitle.setText(models.get(position).getTitle());
        holder.mImageView.setImageResource(models.get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context context = itemView.getContext();
        @SuppressLint("StaticFieldLeak")
        public  TextView mTitle;
        @SuppressLint("StaticFieldLeak")
        public  ImageView mImageView;
//    ImageView mImageView;
//    TextView mTitle;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.item_image);
            mTitle = itemView.findViewById(R.id.item_title);
            itemView.setClickable(true);
            itemView.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            switch (getAdapterPosition()) {
                case 0:
                    intent = new Intent(context, Home.class);
                    break;

                case 1:
                    intent = new Intent(context, Registration06.class);
                    break;
                case 2:
                    intent = new Intent(context, Home.class);
                    break;

                default:
                    intent = new Intent(context, Registration07.class);
                    break;
            }
            context.startActivity(intent);
        }

    }
}
