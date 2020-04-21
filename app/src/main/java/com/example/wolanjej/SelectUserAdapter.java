package com.example.wolanjej;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectUserAdapter extends RecyclerView.Adapter<SelectUserAdapter.MyContactListViewHolder> {

    List<SelectUser> mainInfo;
    private ArrayList<SelectUser> arraylist;
    Context context;
    String phoneNumber = "phone1";
    String phoneName;
    private  String EXTRANumber = "phone1";
    private String sessionID;
    private String classType;
    JSONObject jPhoneDetails;
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONE = "com.example.wolanjej.PHONE";
    public static final String EXTRA_NAME = "com.example.wolanjej.NAME";


    public SelectUserAdapter(Context context, List<SelectUser> mainInfo, String sessionID, String classType) {
        this.mainInfo = mainInfo;
        this.classType = classType;
        this.context = context;
        this.sessionID = sessionID;
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(mainInfo);
    }

    public SelectUserAdapter(){}

    public class MyContactListViewHolder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView imageViewUserImage;
        TextView textViewShowName;
        TextView textViewPhoneNumber;

        public MyContactListViewHolder(final View itemView) {
            super(itemView);

            textViewShowName = (TextView) itemView.findViewById(R.id.name);
            textViewPhoneNumber = (TextView) itemView.findViewById(R.id.no);
            imageViewUserImage = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.pic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final SelectUser selectUser = mainInfo.get(getAdapterPosition());
//                    Toast.makeText(itemView.getContext(), "Hi, I'm " + selectUser.getName(), Toast.LENGTH_SHORT).show();
                    phoneNumber = selectUser.getPhone();
                    phoneName = selectUser.getName();
                    Toast.makeText(itemView.getContext(), "Hi,  " + classType, Toast.LENGTH_SHORT).show();
//                    jPhoneDetails = new JSONObject();
//                    try {
//                        jPhoneDetails.put("phone", phoneNumber);
//                        jPhoneDetails.put("name", phoneName);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                    setEXTRANumber(phoneNumber);
                    moversCheck(classType);


                }
            });
        }

    }

    public void setEXTRANumber(String name) {
        this.EXTRANumber = name;
    }

    public String getEXTRANumber() {
        return EXTRA_PHONE;
    }

    public void moversCheck(String name){
        Log.e("session at contact", name);
        if(name.equals("phone")){
            Log.e("phone move", sessionID);
            movebackTotrasfer();
        }else if (name.equals("wallet")){
            Log.e("wallet move", sessionID);
            movebackTowalet();
        }else if (name.equals("bank")){
            Log.e("bank move", sessionID);
            movebackToBank();
        }else if (name.equals("TopUpNumber")){
            Log.e("Top Up Number move", sessionID);
            movebackToTopUpNumber();
        }
    }
    public void movebackTotrasfer(){
        Log.e("session move trasfer", sessionID);
//        Log.e("json at contact", jPhoneDetails.toString());
        Intent move = new Intent(context, TransferToPhone50.class);
        move.putExtra("Class","SelectUserAdapter");
        move.putExtra(EXTRA_NAME, phoneName);
        move.putExtra(EXTRA_PHONE, phoneNumber);
        move.putExtra(EXTRA_SESSION, sessionID);
        context.startActivity(move);
    }

    public void movebackTowalet(){
        Log.e("session at contact", sessionID);
//        Log.e("json at contact", jPhoneDetails.toString());
        Intent move = new Intent(context, TransferToWalletSingle37.class);
        move.putExtra("Class","SelectUserAdapter");
        move.putExtra(EXTRA_NAME, phoneName);
        move.putExtra(EXTRA_PHONE, phoneNumber);
        move.putExtra(EXTRA_SESSION, sessionID);
        context.startActivity(move);
    }

    public void movebackToBank(){
        Log.e("session at contact", sessionID);
//        Log.e("json at contact", jPhoneDetails.toString());
        Intent move = new Intent(context, TransferToBank44.class);
        move.putExtra("Class","SelectUserAdapter");
        move.putExtra(EXTRA_NAME, phoneName);
        move.putExtra(EXTRA_PHONE, phoneNumber);
        move.putExtra(EXTRA_SESSION, sessionID);
        context.startActivity(move);
    }

    public void movebackToTopUpNumber(){
        Log.e("session at contact", sessionID);
//        Log.e("json at contact", jPhoneDetails.toString());
        Intent move = new Intent(context, TopupOtherNumber.class);
        move.putExtra("Class","SelectUserAdapter");
        move.putExtra(EXTRA_NAME, phoneName);
        move.putExtra(EXTRA_PHONE, phoneNumber);
        move.putExtra(EXTRA_SESSION, sessionID);
        context.startActivity(move);
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
            Picasso.with(context).load(R.drawable.image).into(holder.imageViewUserImage);
        }else {
            Picasso.with(context).load(imagepath).into(holder.imageViewUserImage);
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
            for (SelectUser wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    mainInfo.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}