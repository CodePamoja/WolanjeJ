package com.example.wolanjej;

import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    private static final String TAG = "CustomAdapter";
    Context context;
    ArrayList<Model>models;

    public MyAdapter(Context context,ArrayList<Model>models){
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        MyHolder.mTitle.setText(models.get(position).getTitle());
        MyHolder.mImageView.setImageResource(models.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
