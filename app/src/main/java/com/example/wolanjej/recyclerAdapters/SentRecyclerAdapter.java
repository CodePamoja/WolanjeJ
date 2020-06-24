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
import com.example.wolanjej.models.SentTransactionHistory;

import java.util.List;

public class SentRecyclerAdapter extends RecyclerView.Adapter<SentRecyclerAdapter.viewHolder> {

    Context context;
    List<SentTransactionHistory> mData;

    public SentRecyclerAdapter(Context context, List<SentTransactionHistory> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_row_item, parent, false);
        return new SentRecyclerAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.mMONTH.setText(mData.get(position).getmMonth());
        holder.mDATE.setText(mData.get(position).getmDate());
        holder.mImageView.setImageResource(mData.get(position).getmPic());
        holder.mNAME.setText(mData.get(position).getmName());
        holder.mPAYMENT_METHOD.setText(mData.get(position).getmPaymentMethod());
        holder.mTRANSACTION_AMOUNT.setText(mData.get(position).getmTransactionAmount());
        holder.mTRANSACTION_FEES.setText(mData.get(position).getmTransactionFees());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        private TextView mMONTH ;
        private TextView mDATE;
        private ImageView mImageView;
        private TextView mNAME;
        private TextView mPAYMENT_METHOD;
        private TextView mTRANSACTION_AMOUNT;
        private TextView mTRANSACTION_FEES;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            mMONTH = itemView.findViewById(R.id.SentTransMonth);
            mDATE = itemView.findViewById(R.id.SentTransDate);
            mImageView = itemView.findViewById(R.id.pic_trans);
            mNAME = itemView.findViewById(R.id.TransName);
            mPAYMENT_METHOD = itemView.findViewById(R.id.TransPayMethod);
            mTRANSACTION_AMOUNT = itemView.findViewById(R.id.TransactionAmount);
            mTRANSACTION_FEES = itemView.findViewById(R.id.TransactionFees);
        }
    }

}
