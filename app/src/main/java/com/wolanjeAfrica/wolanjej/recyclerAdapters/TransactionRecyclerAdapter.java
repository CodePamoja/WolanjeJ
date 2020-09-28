package com.wolanjeAfrica.wolanjej.recyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.R;
import com.wolanjeAfrica.wolanjej.models.TranasactionHistory;

import java.util.Collections;
import java.util.List;

public class TransactionRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<TranasactionHistory> mData = Collections.emptyList();
    private static int TYPE_PHONE = 1;
    private static int TYPE_ELECTRICITY = 2;
    private static int TYPE_TRANSFER = 3;
    private static int TYPE_WATER = 4;
    private static int TYPE_TV = 5;
    private static int TYPE_INTERNET = 6;

    public TransactionRecyclerAdapter(Context mContext, List<TranasactionHistory> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType == TYPE_TRANSFER || viewType == TYPE_TV) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_history_items, parent, false);
            return new TransactionRecyclerAdapter.viewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_row_electrcbill_item, parent, false);
            return new TransactionRecyclerAdapter.BillviewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_TRANSFER || getItemViewType(position) == TYPE_TV) {
            TranasactionHistory current = mData.get(position);
            ((viewHolder) holder).Tmonth.setText(current.getmMONTHS());
            ((viewHolder) holder).Tdate.setText(current.getmDATE());
            ((viewHolder) holder).Ttop_up_amount.setText(current.getmTOP_UP_AMOUNT());
            ((viewHolder) holder).Ttransaction_fee.setText(current.getmTRANSACTION_FEE());
            ((viewHolder) holder).Tstatus.setText(current.getmSTATUS());
            ((viewHolder) holder).Tpending.setText(current.getmPENDING());

        } else if (getItemViewType(position) == TYPE_WATER) {
            TranasactionHistory current = mData.get(position);

            ((BillviewHolder) holder).mMONTH.setText(current.getmMONTHS());
            ((BillviewHolder) holder).mDATE.setText(current.getmDATE());
            ((BillviewHolder) holder).mImageView.setImageResource(R.drawable.ic_drops);
            ((BillviewHolder) holder).mNAME.setText("Water Bill");
            ((BillviewHolder) holder).mPAYMENT_METHOD.setText("Mastercard");
            ((BillviewHolder) holder).mTRANSACTION_AMOUNT.setText(current.getmTOP_UP_AMOUNT());
        } else if (getItemViewType(position) == TYPE_ELECTRICITY) {
            TranasactionHistory current = mData.get(position);

            ((BillviewHolder) holder).mMONTH.setText(current.getmMONTHS());
            ((BillviewHolder) holder).mDATE.setText(current.getmDATE());
            ((BillviewHolder) holder).mImageView.setImageResource(R.drawable.ic_bulb);
            ((BillviewHolder) holder).mNAME.setText("Electric Bill");
            ((BillviewHolder) holder).mPAYMENT_METHOD.setText("Mastercard");
            ((BillviewHolder) holder).mTRANSACTION_AMOUNT.setText(current.getmTOP_UP_AMOUNT());
        } else if (getItemViewType(position) == TYPE_INTERNET) {
            TranasactionHistory current = mData.get(position);

            ((BillviewHolder) holder).mMONTH.setText(current.getmMONTHS());
            ((BillviewHolder) holder).mDATE.setText(current.getmDATE());
            ((BillviewHolder) holder).mImageView.setImageResource(R.drawable.ic_wifi);
            ((BillviewHolder) holder).mNAME.setText("Internet Bill");
            ((BillviewHolder) holder).mPAYMENT_METHOD.setText("Mastercard");
            ((BillviewHolder) holder).mTRANSACTION_AMOUNT.setText(current.getmTOP_UP_AMOUNT());
        } else {
            TranasactionHistory current = mData.get(position);

            ((BillviewHolder) holder).mMONTH.setText(current.getmMONTHS());
            ((BillviewHolder) holder).mDATE.setText(current.getmDATE());
            ((BillviewHolder) holder).mImageView.setImageResource(R.drawable.ic_phone);
            ((BillviewHolder) holder).mNAME.setText("Phone Bill");
            ((BillviewHolder) holder).mPAYMENT_METHOD.setText("Mastercard");
            ((BillviewHolder) holder).mTRANSACTION_AMOUNT.setText(current.getmTOP_UP_AMOUNT());

        }


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (mData.get(position).getProduct_name()) {
            case "phone":
                return TYPE_PHONE;
            case "electricity":
                return TYPE_ELECTRICITY;
            case "tv":
                return TYPE_TV;
            case "water":
                return TYPE_WATER;
            case "internet":
                return TYPE_INTERNET;
            default:
                return TYPE_TRANSFER;
        }
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        TextView Tmonth;
        TextView Tdate;
        TextView Ttop_up_amount;
        TextView Ttransaction_fee;
        TextView Tstatus;
        TextView Tpending;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            Tmonth = itemView.findViewById(R.id.transaction_month);
            Tdate = itemView.findViewById(R.id.transaction_date);
            Ttop_up_amount = itemView.findViewById(R.id.transaction_top_up_amount);
            Ttransaction_fee = itemView.findViewById(R.id.transaction_feeAmount);
            Tstatus = itemView.findViewById(R.id.txt_transaction_status);
            Tpending = itemView.findViewById(R.id.txt_transaction_pending);
        }
    }


    public static class BillviewHolder extends RecyclerView.ViewHolder {

        private TextView mMONTH;
        private TextView mDATE;
        private ImageView mImageView;
        private TextView mNAME;
        private TextView mPAYMENT_METHOD;
        private TextView mTRANSACTION_AMOUNT;

        public BillviewHolder(@NonNull View itemView) {
            super(itemView);

            mMONTH = itemView.findViewById(R.id.SentElectTransMonth);
            mDATE = itemView.findViewById(R.id.SentElectTransDate);
            mImageView = itemView.findViewById(R.id.Elect_pic_trans);
            mNAME = itemView.findViewById(R.id.ElectTransName);
            mPAYMENT_METHOD = itemView.findViewById(R.id.ElectTransPayMethod);
            mTRANSACTION_AMOUNT = itemView.findViewById(R.id.ElectTransactionAmount);
        }
    }
}
