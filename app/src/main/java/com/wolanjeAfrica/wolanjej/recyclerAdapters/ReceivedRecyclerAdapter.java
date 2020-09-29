package com.wolanjeAfrica.wolanjej.recyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.R;
import com.wolanjeAfrica.wolanjej.models.SentTransactionHistory;
import com.wolanjeAfrica.wolanjej.models.TranasactionHistory;

import java.util.List;

public class ReceivedRecyclerAdapter extends RecyclerView.Adapter<ReceivedRecyclerAdapter.viewHolder> {

    Context context;
    List<TranasactionHistory> mData;

    public ReceivedRecyclerAdapter(Context context, List<TranasactionHistory> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_history_items, parent, false);
        return new ReceivedRecyclerAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.Tmonth.setText(mData.get(position).getmMONTHS());
        holder.Tdate.setText(mData.get(position).getmDATE());
        holder.Ttop_up_amount.setText(mData.get(position).getmTOP_UP_AMOUNT());
        holder.Tstatus.setText(mData.get(position).getmSTATUS());
        holder.Tpending.setText(mData.get(position).getmPENDING());


    }

    @Override
    public int getItemCount() {
        return mData.size();
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

}
