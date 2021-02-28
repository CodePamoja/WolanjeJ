package com.wolanjeAfrica.wolanjej.recyclerAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.wolanjeAfrica.wolanjej.ContactsView;
import com.wolanjeAfrica.wolanjej.Home;
import com.wolanjeAfrica.wolanjej.R;
import com.wolanjeAfrica.wolanjej.TransferToWalletMultiple40;
import com.wolanjeAfrica.wolanjej.models.SelectUser;
import com.wolanjeAfrica.wolanjej.TopupOtherNumber;
import com.wolanjeAfrica.wolanjej.TransferToBank44;
import com.wolanjeAfrica.wolanjej.TransferToPhone50;
import com.wolanjeAfrica.wolanjej.TransferToWalletSingle37;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectUserAdapter extends RecyclerView.Adapter<SelectUserAdapter.MyContactListViewHolder> {

    public static final String EXTRA_TRANSACTIONS = "com.example.wolanjej.TRANSACTIONS";
    private static final String TAG = "SelectUserAdapter";
    private List<SelectUser> mainInfo;
    private ArrayList<SelectUser> arraylist;
    public static ArrayList<SelectUser> listOfMultiUser = new ArrayList<>();
    private Context context;
    private String phoneNumber;
    private String phoneName;
    private  View v1;
    private String classType;
    private int activity_contacts_view;
    public static final String EXTRA_PHONE = "com.example.wolanjej.PHONE";
    public static final String EXTRA_NAME = "com.example.wolanjej.NAME";


    public SelectUserAdapter(Context context, int activity_contacts_view,List<SelectUser> mainInfo, String classType) {
        this.mainInfo = mainInfo;
        this.classType = classType;
        this.context = context;
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(mainInfo);
        this.activity_contacts_view=activity_contacts_view;
    }


    public SelectUserAdapter(){}

    public class MyContactListViewHolder extends RecyclerView.ViewHolder {
        private final de.hdodenhof.circleimageview.CircleImageView imageViewUserImage;
        private final TextView textViewShowName;
        private final TextView textViewPhoneNumber;
        private final LinearLayout contact_rowview_layout;
        private final View view;

        public MyContactListViewHolder(final View itemView) {
            super(itemView);
            view = itemView;
            textViewShowName = itemView.findViewById(R.id.name);
            textViewPhoneNumber = itemView.findViewById(R.id.no);
            imageViewUserImage = itemView.findViewById(R.id.pic);
            contact_rowview_layout = itemView.findViewById(R.id.contact_rowview_layout);

            itemView.setOnClickListener(v -> {
                final SelectUser selectUser = mainInfo.get(getAdapterPosition());
                phoneName = selectUser.getName();
                phoneNumber = selectUser.getPhone();

            });
        }

    }


    @Override
    public MyContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowview, parent, false);
        MyContactListViewHolder holder = new MyContactListViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyContactListViewHolder holder, int position) {
        String imagepath = mainInfo.get(position).getImagepath();
        if (imagepath == null) {
            Picasso.get().load(R.drawable.image).into(holder.imageViewUserImage);
        }else {
            Picasso.get().load(imagepath).into(holder.imageViewUserImage);
        }

        holder.textViewShowName.setText(mainInfo.get(position).getName());
        holder.textViewPhoneNumber.setText(mainInfo.get(position).getPhone());
        holder.contact_rowview_layout.setOnClickListener(view -> {
            mainInfo.get(position).setSelected(!mainInfo.get(position).isSelected());
            holder.view.setBackgroundColor(mainInfo.get(position).isSelected() ? Color.parseColor("#268484a9"): Color.WHITE);
            if ("walletMultiple".equals(classType)) {
                listOfMultiUser.add(new SelectUser(mainInfo.get(position).getName(), mainInfo.get(position).getPhone()));
            }else {
                phoneName = mainInfo.get(position).getName();
                phoneNumber = mainInfo.get(position).getPhone();
                moversCheck(classType);
            }
        });
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
            for (SelectUser wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    mainInfo.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


    public void moversCheck(String name){
        switch (name) {
            case "home":
                moveToHome();
                break;
            case "phone":
                movebackToPhone();
                break;
            case "wallet":
                movebackTowalet();
                break;
            case "bank":
                movebackToBank();
                break;
            case "TopUpNumber":
                movebackToTopUpNumber();
                break;
            case  "walletMultiple":
//                moveTomultipleTransfers();
                break;
        }
    }

    private void moveToHome() {
        Intent move = new Intent(context, Home.class);
        move.putExtra("Class","SelectUserAdapter2");
        move.putExtra(EXTRA_NAME, phoneName);
        move.putExtra(EXTRA_PHONE, phoneNumber);
        move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(move);
    }




    public void movebackToPhone(){
        Intent move = new Intent(context, TransferToPhone50.class);
        move.putExtra("Class","SelectUserAdapter");
        move.putExtra(EXTRA_NAME, phoneName);
        move.putExtra(EXTRA_PHONE, phoneNumber);
        move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(move);
    }

    public void movebackTowalet(){
        Intent move = new Intent(context, TransferToWalletSingle37.class);
        move.putExtra("Class","SelectUserAdapter");
        move.putExtra(EXTRA_NAME, phoneName);
        move.putExtra(EXTRA_PHONE, phoneNumber);
        move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(move);
    }

    public void movebackToBank(){
        Intent move = new Intent(context, TransferToBank44.class);
        move.putExtra("Class","SelectUserAdapter");
        move.putExtra(EXTRA_NAME, phoneName);
        move.putExtra(EXTRA_PHONE, phoneNumber);
        move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(move);
    }

    public void movebackToTopUpNumber(){
        Intent move = new Intent(context, TopupOtherNumber.class);
        move.putExtra("Class","SelectUserAdapter");
        move.putExtra(EXTRA_NAME, phoneName);
        move.putExtra(EXTRA_PHONE, phoneNumber);
        move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(move);
    }
    public static List<SelectUser> getListOfSelectedUsers(){
        return listOfMultiUser;
    }

}