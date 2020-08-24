package com.wolanjeAfrica.wolanjej.recyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.models.Contacts;
import com.wolanjeAfrica.wolanjej.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReferralsAdapter extends RecyclerView.Adapter<ReferralsAdapter.MyContactListViewHolder> {

    List<Contacts> mainInfo;
    private ArrayList<Contacts> arraylist;
    Context context;


    public ReferralsAdapter(Context context, List<Contacts> mainInfo) {
        this.mainInfo = mainInfo;
        this.context = context;
        this.arraylist = new ArrayList<Contacts>();
        this.arraylist.addAll(mainInfo);
    }

    public class MyContactListViewHolder extends RecyclerView.ViewHolder {

        de.hdodenhof.circleimageview.CircleImageView imageViewUserImage;
        TextView textViewShowName;
        TextView textViewPhoneNumber;

        public MyContactListViewHolder(View itemView) {
            super(itemView);

            textViewShowName = (TextView) itemView.findViewById(R.id.referralsName);
            textViewPhoneNumber = (TextView) itemView.findViewById(R.id.phoneNumber);
            imageViewUserImage = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.referralsImage);
        }
    }

    @Override
    public MyContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        MyContactListViewHolder holder = new MyContactListViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyContactListViewHolder holder, int position) {
        String imagepath = mainInfo.get(position).getImage();
        if (imagepath == null) {
            Picasso.get().load(R.drawable.image).into(holder.imageViewUserImage);
        }else {
            Picasso.get().load(imagepath).into(holder.imageViewUserImage);
        }
        holder.textViewShowName.setText(mainInfo.get(position).getName());
        holder.textViewPhoneNumber.setText(mainInfo.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return mainInfo.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mainInfo.clear();
        if (charText.length() == 0) {
            mainInfo.addAll(arraylist);
        } else {
            for (Contacts wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    mainInfo.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}