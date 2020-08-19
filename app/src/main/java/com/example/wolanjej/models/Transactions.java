package com.example.wolanjej.models;

import com.google.gson.annotations.SerializedName;

public class Transactions {
    @SerializedName("product_name")
    private String product_name;

    @SerializedName("amount")
    private String Amount;

    @SerializedName("phone")
    private String phone;

    @SerializedName("ref")
    private String ref;

    public Transactions(String product_name, String amount, String phone, String ref) {
        this.product_name = product_name;
        Amount = amount;
        this.phone = phone;
        this.ref = ref;
    }

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
}
