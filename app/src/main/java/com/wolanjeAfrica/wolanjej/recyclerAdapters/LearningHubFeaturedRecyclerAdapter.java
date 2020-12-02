package com.wolanjeAfrica.wolanjej.recyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wolanjeAfrica.wolanjej.R;
import com.wolanjeAfrica.wolanjej.models.LearningHubFeatured;

import java.util.List;

public class LearningHubFeaturedRecyclerAdapter extends RecyclerView.Adapter<LearningHubFeaturedRecyclerAdapter.viewholder> {
    private List<LearningHubFeatured> learningHubFeaturedList;
    private Context context;
    private FeaturedItemListener FeaturedItemListener;


    public LearningHubFeaturedRecyclerAdapter(List<LearningHubFeatured> learningHubFeaturedList, Context context, FeaturedItemListener featuredItemListener) {
        this.learningHubFeaturedList = learningHubFeaturedList;
        this.context = context;
        this.FeaturedItemListener = featuredItemListener;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_fragment_item, parent, false);
        return new LearningHubFeaturedRecyclerAdapter.viewholder(view, FeaturedItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        LearningHubFeatured current  = learningHubFeaturedList.get(position);
        Picasso.get().load(current.getFeatured_item_image())
                .into(holder.imageView);
        holder.textView.setText(current.getFeatured_item_title());
        holder.textView2.setText(current.getFeatured_item_description());

    }

    @Override
    public int getItemCount() {
        return learningHubFeaturedList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AppCompatImageView imageView;
        TextView textView;
        TextView textView2;
        FeaturedItemListener featuredItemListener;


        public viewholder(@NonNull View itemView,FeaturedItemListener featuredItemListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewFragFeatured);
            textView = itemView.findViewById(R.id.texttop);
            textView2 = itemView.findViewById(R.id.textCenter);
            this.featuredItemListener = featuredItemListener;
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            featuredItemListener.OnFeaturedItemClickListener(getAdapterPosition());
        }
    }

    public interface FeaturedItemListener{
        void OnFeaturedItemClickListener(int position);
    }
}
