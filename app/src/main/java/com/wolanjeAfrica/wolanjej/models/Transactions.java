package com.wolanjeAfrica.wolanjej.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Transactions implements Parcelable {
    @SerializedName("product_name")
    private String product_name;

    @SerializedName("amount")
    private String Amount;

    @SerializedName("phone")
    private String phone;

    @SerializedName("ref")
    private String ref;

    private String recepient_name;

    private String message;

    private List<Transactions> transactionsList;

    private String TransactionInitializer;

    private String Date;

    public Transactions() {
    }

    public Transactions(String product_name, String amount, String phone, String ref) {
        this.product_name = product_name;
        Amount = amount;
        this.phone = phone;
        this.ref = ref;
    }

    protected Transactions(Parcel in) {
        product_name = in.readString();
        Amount = in.readString();
        phone = in.readString();
        ref = in.readString();
        recepient_name = in.readString();
        message = in.readString();
        transactionsList = in.createTypedArrayList(Transactions.CREATOR);
        TransactionInitializer = in.readString();
        Date = in.readString();
    }

    public static final Creator<Transactions> CREATOR = new Creator<Transactions>() {
        @Override
        public Transactions createFromParcel(Parcel in) {
            return new Transactions(in);
        }

        @Override
        public Transactions[] newArray(int size) {
            return new Transactions[size];
        }
    };

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRecepient_name() {
        return recepient_name;
    }

    public void setRecepient_name(String recepient_name) {
        this.recepient_name = recepient_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public String getTransactionInitializer() {
        return TransactionInitializer;
    }

    public void setTransactionInitializer(String transactionInitializer) {
        TransactionInitializer = transactionInitializer;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(product_name);
        dest.writeString(Amount);
        dest.writeString(phone);
        dest.writeString(ref);
        dest.writeString(recepient_name);
        dest.writeString(message);
        dest.writeTypedList(transactionsList);
        dest.writeString(TransactionInitializer);
        dest.writeString(Date);
    }
}
