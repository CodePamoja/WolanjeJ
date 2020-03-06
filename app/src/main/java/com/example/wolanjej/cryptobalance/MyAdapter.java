package com.example.wolanjej.cryptobalance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wolanjej.Model;
import com.example.wolanjej.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<com.example.wolanjej.MyHolder> {
    Context context;
    ArrayList<Model> models;

    public MyAdapter(Context context,ArrayList<Model>models) {
        this.context = context;
        this.models = models;
    }
    @NonNull
    @Override
    public com.example.wolanjej.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items2,parent,false);
        return new com.example.wolanjej.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.wolanjej.MyHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return models.size();
    }
}
