package com.example.wolanjej;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionRecyclerAdapter extends RecyclerView.Adapter<TransactionRecyclerAdapter.viewHolder> {


    Context mContext;
    List<TranasactionHistory> mData;

    public TransactionRecyclerAdapter(Context mContext, List<TranasactionHistory> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_history_items, parent, false);
        return new TransactionRecyclerAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.month.setText(mData.get(position).getmMONTHS());
        holder.date.setText(mData.get(position).getmDATE());
        holder.top_up_amount.setText(mData.get(position).getmTOP_UP_AMOUNT());
        holder.transaction_fee.setText(mData.get(position).getmTRANSACTION_FEE());
        holder.status.setText(mData.get(position).getmSTATUS());
        holder.pending.setText(mData.get(position).getmPENDING());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class  viewHolder extends RecyclerView.ViewHolder{

        TextView month;
        TextView date;
        TextView top_up_amount;
        TextView transaction_fee;
        TextView status;
        TextView pending;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            month = itemView.findViewById(R.id.transaction_month);
            date = itemView.findViewById(R.id.transaction_date);
            top_up_amount = itemView.findViewById(R.id.transaction_top_up_amount);
            transaction_fee = itemView.findViewById(R.id.transaction_feeAmount);
            status = itemView.findViewById(R.id.txt_transaction_status);
            pending = itemView.findViewById(R.id.txt_transaction_pending);
        }
    }
}
