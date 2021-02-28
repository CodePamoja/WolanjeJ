package com.wolanjeAfrica.wolanjej.recyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.R;
import com.wolanjeAfrica.wolanjej.models.SelectUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MultipleUsersTransactionsAdapter extends RecyclerView.Adapter<MultipleUsersTransactionsAdapter.viewholderMultipleUsers> {
    private static final String TAG = "MultipleUsersTransactionsAdapter";
    private Context context;
    private List<SelectUser> userTransactionModels;


    public MultipleUsersTransactionsAdapter(@NonNull Context context, List<SelectUser> userTransactionModels) {
        this.context = context;
        this.userTransactionModels = userTransactionModels;
    }

    @NonNull
    @Override
    public viewholderMultipleUsers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_transfer_list, parent, false);
        return new MultipleUsersTransactionsAdapter.viewholderMultipleUsers(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderMultipleUsers holder, int position) {
        SelectUser current =userTransactionModels.get(position);
//        Picasso.get().load(current.getImage())
//                .into(holder.circleImageView);
        holder.textView.setText(current.getName());
    }

    @Override
    public int getItemCount() {
        return userTransactionModels.size();
    }

    public class viewholderMultipleUsers extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView textView;

        public viewholderMultipleUsers(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.image_transfer_list);
            textView = itemView.findViewById(R.id.name_transfer_list);
        }
    }
}
