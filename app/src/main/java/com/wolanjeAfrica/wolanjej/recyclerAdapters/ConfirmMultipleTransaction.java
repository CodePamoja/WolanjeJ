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
import com.wolanjeAfrica.wolanjej.models.ListUserBills;
import com.wolanjeAfrica.wolanjej.models.Transactions;

import java.util.List;

public class ConfirmMultipleTransaction extends RecyclerView.Adapter<ConfirmMultipleTransaction.viewholder> {
    private Context context;
    private List<Transactions> transactionsList ;

    public ConfirmMultipleTransaction(@NonNull Context context, List<Transactions> transactionsList) {
        this.context = context;
        this.transactionsList= transactionsList;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.confirm_multiple_transaction_item, parent, false);
        return new ConfirmMultipleTransaction.viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Transactions current = transactionsList.get(position);
        holder.textView1.setText(current.getRecepient_name());
        holder.textView2.setText(current.getDate());
        holder.textView3.setText(current.getTransactionInitializer());
        holder.textView4.setText(current.getPhone());
        holder.textView5.setText(current.getAmount());
        holder.textView6.setText(current.getAmount());
        holder.textView7.setText(current.getMessage());
        holder.textView8.setText("0.00");
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;
        private TextView textView5;
        private TextView textView6;
        private TextView textView7;
        private TextView textView8;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            textView1 =itemView.findViewById(R.id.confirmMultipleName1);
            textView2 = itemView.findViewById(R.id.dateConfirmMultiple1);
            textView3 = itemView.findViewById(R.id.fromConfirmMultiple);
            textView4 = itemView.findViewById(R.id.toConfirmMultiple1);
            textView5 = itemView.findViewById(R.id.amountConfirmMultiple1);
            textView6 = itemView.findViewById(R.id.totalAmountConfirm1);
            textView7 = itemView.findViewById(R.id.usermessage_cmt);
            textView8 = itemView.findViewById(R.id.usertransaction_fee);


        }
    }
}
