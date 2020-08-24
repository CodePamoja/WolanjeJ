package com.wolanjeAfrica.wolanjej.models;

import com.google.gson.annotations.SerializedName;

public class ListUserBills {
    @SerializedName("id")
    private String id;
    @SerializedName("created_on")
    private String created_on;
    @SerializedName("created_by")
    private String created_by;
    @SerializedName("created_by_id")
    private String created_by_id;
    @SerializedName("auth_ac_id")
    private String auth_ac_id;
    @SerializedName("auth_ac_uname")
    private String auth_ac_uname;
    @SerializedName("auth_phone")
    private String auth_phone;
    @SerializedName("auth_email")
    private String auth_email;
    @SerializedName("product_id")
    private String product_id;
    @SerializedName("product_name")
    private String product_name;
    @SerializedName("product_ve")
    private String product_ve;
    @SerializedName("account_no")
    private String account_no;
    @SerializedName("due_date")
    private String due_date;
    @SerializedName("billing_cycle")
    private String billing_cycle;
    @SerializedName("amount")
    private String amount;

    private int imageDrawable;

    public String getId() {
        return id;
    }

    public String getCreated_on() {
        return created_on;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getCreated_by_id() {
        return created_by_id;
    }

    public String getAuth_ac_id() {
        return auth_ac_id;
    }

    public String getAuth_ac_uname() {
        return auth_ac_uname;
    }

    public String getAuth_phone() {
        return auth_phone;
    }

    public String getAuth_email() {
        return auth_email;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_ve() {
        return product_ve;
    }

    public String getAccount_no() {
        return account_no;
    }

    public String getDue_date() {
        return due_date;
    }

    public String getBilling_cycle() {
        return billing_cycle;
    }

    public String getAmount() {
        return amount;
    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public void setImageDrawable(int imageDrawable) {
        this.imageDrawable = imageDrawable;
    }
}
