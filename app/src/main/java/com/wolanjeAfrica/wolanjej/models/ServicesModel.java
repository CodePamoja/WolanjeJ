package com.wolanjeAfrica.wolanjej.models;

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

    public void setId(String id) {
        Id = id;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setCreated_by_id(String created_by_id) {
        this.created_by_id = created_by_id;
    }

    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setAuth_ac_id(String auth_ac_id) {
        this.auth_ac_id = auth_ac_id;
    }

    public void setAc_uname(String ac_uname) {
        this.ac_uname = ac_uname;
    }

    public void setAccount_phone(String account_phone) {
        this.account_phone = account_phone;
    }

    public void setAuth_email(String auth_email) {
        this.auth_email = auth_email;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_ve(String product_ve) {
        this.product_ve = product_ve;
    }

    public void setSupplier_ac_id(String supplier_ac_id) {
        this.supplier_ac_id = supplier_ac_id;
    }

    public void setSupplier_ac_name(String supplier_ac_name) {
        this.supplier_ac_name = supplier_ac_name;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAmountTransaction(String amountTransaction) {
        this.amountTransaction = amountTransaction;
    }

    public void setTransactionFee(String transactionFee) {
        this.transactionFee = transactionFee;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public void setActivity_count(String activity_count) {
        this.activity_count = activity_count;
    }

    public void setActivity_ids(String activity_ids) {
        this.activity_ids = activity_ids;
    }

    public void setActivity_id_last(String activity_id_last) {
        this.activity_id_last = activity_id_last;
    }

    public void setReceipt_id(String receipt_id) {
        this.receipt_id = receipt_id;
    }

    public void setLast_status(String last_status) {
        this.last_status = last_status;
    }
}
