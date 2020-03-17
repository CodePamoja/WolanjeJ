package com.example.wolanjej;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wolanjej.Model;
import com.example.wolanjej.R;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyHolder2> {
    Context context;
    ArrayList<Model2> models;

    public MyAdapter2(Context context, ArrayList<Model2>models) {
        this.context = context;
        this.models = models;
    }
    @NonNull
    @Override
    public com.example.wolanjej.MyHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items2,parent,false);
        return new com.example.wolanjej.MyHolder2(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyHolder2 holder, int position) {

        MyHolder2.mtitle.setText(models.get(position).getTitle());
        MyHolder2.mtitle2.setText(models.get(position).getTitle2());
        MyHolder2.mtitle3.setText(models.get(position).getTitle3());
        MyHolder2.mtitle4.setText(models.get(position).getTitle4());
        MyHolder2.mImageView.setImageResource(models.get(position).getImage());
        MyHolder2.mColor.setCardBackgroundColor(models.get(position).getBackgroundColor());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
