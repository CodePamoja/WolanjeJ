package com.example.wolanjej.models;

import com.google.gson.annotations.SerializedName;

public class NewBillmodel {

    @SerializedName("product_name")
    private String product_name;

    @SerializedName("account_no")
    private String account_no;


    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }
}
