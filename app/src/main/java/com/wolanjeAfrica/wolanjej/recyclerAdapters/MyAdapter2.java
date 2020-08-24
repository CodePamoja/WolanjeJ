package com.wolanjeAfrica.wolanjej.recyclerAdapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.R;
import com.wolanjeAfrica.wolanjej.Registration07;
import com.wolanjeAfrica.wolanjej.cryptomarket;
import com.wolanjeAfrica.wolanjej.models.Model2;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyHolder2> {
    Context context;
    ArrayList<Model2> models;

    public MyAdapter2(Context context, ArrayList<Model2> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items2, parent, false);
        return new MyHolder2(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyHolder2 holder, int position) {

        holder.mtitle.setText(models.get(position).getTitle());
        holder.mtitle2.setText(models.get(position).getTitle2());
        holder.mtitle3.setText(models.get(position).getTitle3());
        holder.mtitle4.setText(models.get(position).getTitle4());
        holder.mImageView.setImageResource(models.get(position).getImage());
        holder.mColor.setCardBackgroundColor(models.get(position).getBackgroundColor());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView mColor;
        public View View;
        Context context = itemView.getContext();
        public TextView mtitle;
        public TextView mtitle2;
        public TextView mtitle3;
        public TextView mtitle4;
        public ImageView mImageView;

        public MyHolder2(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_image1);
            mtitle = itemView.findViewById(R.id.item_title);
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
            switch (getAdapterPosition()) {
                case 0:
                    intent = new Intent(context, cryptomarket.class);
                    break;

                case 1:
                    intent = new Intent(context, cryptomarket.class);
                    break;
                case 2:
                    intent = new Intent(context, cryptomarket.class);
                    break;
                default:
                    intent = new Intent(context, Registration07.class);
                    break;
            }
            context.startActivity(intent);

        }
    }
}
