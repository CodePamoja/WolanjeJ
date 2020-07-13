package com.example.wolanjej.models;

import com.google.gson.annotations.SerializedName;

public class ServicesModel {
    @SerializedName("id")
    private String Id;

    @SerializedName("created_on")
    private String created_on;

    @SerializedName("created_by")
    private String created_by;

    @SerializedName("created_by_id")
    private String created_by_id;

    @SerializedName("req_id")
    private String req_id;

    @SerializedName("trx_id")
    private String transaction_id;

    @SerializedName("auth_ac_id")
    private String auth_ac_id;

    @SerializedName("auth_ac_uname")
    private String ac_uname;

    @SerializedName("auth_phone")
    private String account_phone;

    @SerializedName("auth_email")
    private String auth_email;

    @SerializedName("product_id")
    private String product_id;

    @SerializedName("product_name")
    private String product_name;

    @SerializedName("product_ve")
    private String product_ve;

    @SerializedName("supplier_ac_id")
    private String supplier_ac_id;

    @SerializedName("supplier_ac_uname")
    private String supplier_ac_name;

    @SerializedName("ref")
    private String ref;

    @SerializedName("phone")
    private String phoneNumber;

    @SerializedName("email")
    private String email;

    @SerializedName("amount")
    private String amountTransaction;

    @SerializedName("fee")
    private String transactionFee;

    @SerializedName("narration")
    private String narration;

    @SerializedName("activity_count")
    private String activity_count;

    @SerializedName("activity_ids")
    private String activity_ids;

    @SerializedName("activity_id_last")
    private String activity_id_last;

    @SerializedName("receipt_id")
    private String receipt_id;

    @SerializedName("status")
    private String last_status;

    public ServicesModel() {
    }

    public String getId() {
        return Id;
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

    public String getReq_id() {
        return req_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public String getAuth_ac_id() {
        return auth_ac_id;
    }

    public String getAc_uname() {
        return ac_uname;
    }

    public String getAccount_phone() {
        return account_phone;
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

    public String getSupplier_ac_id() {
        return supplier_ac_id;
    }

    public String getSupplier_ac_name() {
        return supplier_ac_name;
    }

    public String getRef() {
        return ref;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAmountTransaction() {
        return amountTransaction;
    }

    public String getTransactionFee() {
        return transactionFee;
    }

    public String getNarration() {
        return narration;
    }

    public String getActivity_count() {
        return activity_count;
    }

    public String getActivity_ids() {
        return activity_ids;
    }

    public String getActivity_id_last() {
        return activity_id_last;
    }

    public String getReceipt_id() {
        return receipt_id;
    }

    public String getLast_status() {
        return last_status;
    }
}
