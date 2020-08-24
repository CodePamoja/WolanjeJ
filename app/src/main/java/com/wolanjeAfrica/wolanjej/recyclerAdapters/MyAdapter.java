package com.wolanjeAfrica.wolanjej.recyclerAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.BillManager;
import com.wolanjeAfrica.wolanjej.Home;
import com.wolanjeAfrica.wolanjej.R;
import com.wolanjeAfrica.wolanjej.Top_up;
import com.wolanjeAfrica.wolanjej.models.Model;

import java.util.ArrayList;

//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;

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
        public TextView mTitle;
        @SuppressLint("StaticFieldLeak")
        public ImageView mImageView;
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
                    intent.putExtra("Class", "MyAdapterCard1");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    break;
                case 1:
                    intent = new Intent(context, BillManager.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    break;
                case 2:
                    intent = new Intent(context, Home.class);
                    intent.putExtra("Class", "MyAdapterCard2");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    break;
                case 3:
                    intent = new Intent(context, Home.class);
                    intent.putExtra("Class", "MyAdapterCard3");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    break;
                case 4:
                    intent = new Intent(context, Top_up.class);
                    intent.putExtra("class", "MyAdapterCard4");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    break;
                default:
                    intent = new Intent(context, Top_up.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    break;
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
