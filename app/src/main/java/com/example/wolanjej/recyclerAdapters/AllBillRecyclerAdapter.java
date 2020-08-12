package com.example.wolanjej.recyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wolanjej.R;
import com.example.wolanjej.models.ListUserBills;
import com.example.wolanjej.models.NewBillmodel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AllBillRecyclerAdapter extends RecyclerView.Adapter<AllBillRecyclerAdapter.viewholder> {
    private Context context;
    private List<ListUserBills> listUserBills = new ArrayList<>();

    public AllBillRecyclerAdapter(@NonNull Context context, List<ListUserBills> listUserBills) {
        this.context = context;
        this.listUserBills= listUserBills;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_bill_items, parent, false);
            return new AllBillRecyclerAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        ListUserBills current = listUserBills.get(position);
        holder.imageView.setImageResource(current.getImageDrawable());
        holder.textView.setText(current.getProduct_name());
        holder.textView2.setText(current.getCreated_on());

    }

    @Override
    public int getItemCount() {
        return listUserBills.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.Bill_imageView);
            textView = itemView.findViewById(R.id.BillName101);
            textView2 = itemView.findViewById(R.id.billCreatedDate);

        }
    }
}
