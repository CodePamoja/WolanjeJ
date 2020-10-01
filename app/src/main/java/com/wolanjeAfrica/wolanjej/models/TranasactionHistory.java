package com.wolanjeAfrica.wolanjej.models;

import com.google.gson.annotations.SerializedName;

public class TranasactionHistory {

    @SerializedName("id")
    private String id;
    @SerializedName("created_on")
    private String created_on;
    @SerializedName("created_by")
    private String Created_by;
    @SerializedName("created_by_id")
    private String created_by_id;
    @SerializedName("req_id")
    private String req_id;
    @SerializedName("trx_id")
    private String trx_id;
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
    @SerializedName("supplier_ac_id")
    private String supplier_ac_id;
    @SerializedName("supplier_ac_uname")
    private String supplier_ac_uname;
    @SerializedName("ref")
    private String ref;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
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
    private String mMONTHS;
    private String mDATE;
    @SerializedName("amount")
    private String mTOP_UP_AMOUNT ;
    @SerializedName("fee")
    private String mTRANSACTION_FEE;
    @SerializedName("status")
    private String mSTATUS;
    private String mPENDING;
    private int imageDrawable;

    public TranasactionHistory() {

    }

    public TranasactionHistory(String mMONTHS, String mDATE, String mTOP_UP_AMOUNT, String mTRANSACTION_FEE, String mSTATUS, String mPENDING, String product_name) {
        this.mMONTHS = mMONTHS;
        this.mDATE = mDATE;
        this.mTOP_UP_AMOUNT = mTOP_UP_AMOUNT;
        this.mTRANSACTION_FEE = mTRANSACTION_FEE;
        this.mSTATUS = mSTATUS;
        this.mPENDING = mPENDING;
        this.product_name = product_name;
    }



    public String getmMONTHS() {
        return mMONTHS;
    }

    public String getmDATE() {
        return mDATE;
    }

    public String getmTOP_UP_AMOUNT() {
        return mTOP_UP_AMOUNT;
    }

    public String getmTRANSACTION_FEE() {
        return mTRANSACTION_FEE;
    }

    public String getmSTATUS() {
        return mSTATUS;
    }

    public String getmPENDING() {
        return mPENDING;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_by() {
        return Created_by;
    }

    public void setCreated_by(String created_by) {
        Created_by = created_by;
    }

    public String getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(String created_by_id) {
        this.created_by_id = created_by_id;
    }

    public String getReq_id() {
        return req_id;
    }

    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }

    public String getTrx_id() {
        return trx_id;
    }

    public void setTrx_id(String trx_id) {
        this.trx_id = trx_id;
    }

    public String getAuth_ac_id() {
        return auth_ac_id;
    }

    public void setAuth_ac_id(String auth_ac_id) {
        this.auth_ac_id = auth_ac_id;
    }

    public String getAuth_ac_uname() {
        return auth_ac_uname;
    }

    public void setAuth_ac_uname(String auth_ac_uname) {
        this.auth_ac_uname = auth_ac_uname;
    }

    public String getAuth_phone() {
        return auth_phone;
    }

    public void setAuth_phone(String auth_phone) {
        this.auth_phone = auth_phone;
    }

    public String getAuth_email() {
        return auth_email;
    }

    public void setAuth_email(String auth_email) {
        this.auth_email = auth_email;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_ve() {
        return product_ve;
    }

    public void setProduct_ve(String product_ve) {
        this.product_ve = product_ve;
    }

    public String getSupplier_ac_id() {
        return supplier_ac_id;
    }

    public void setSupplier_ac_id(String supplier_ac_id) {
        this.supplier_ac_id = supplier_ac_id;
    }

    public String getSupplier_ac_uname() {
        return supplier_ac_uname;
    }

    public void setSupplier_ac_uname(String supplier_ac_uname) {
        this.supplier_ac_uname = supplier_ac_uname;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getActivity_count() {
        return activity_count;
    }

    public void setActivity_count(String activity_count) {
        this.activity_count = activity_count;
    }

    public String getActivity_ids() {
        return activity_ids;
    }

    public void setActivity_ids(String activity_ids) {
        this.activity_ids = activity_ids;
    }

    public String getActivity_id_last() {
        return activity_id_last;
    }

    public void setActivity_id_last(String activity_id_last) {
        this.activity_id_last = activity_id_last;
    }

    public String getReceipt_id() {
        return receipt_id;
    }

    public void setReceipt_id(String receipt_id) {
        this.receipt_id = receipt_id;
    }

    public void setmMONTHS(String mMONTHS) {
        this.mMONTHS = mMONTHS;
    }

    public void setmDATE(String mDATE) {
        this.mDATE = mDATE;
    }

    public void setmTOP_UP_AMOUNT(String mTOP_UP_AMOUNT) {
        this.mTOP_UP_AMOUNT = mTOP_UP_AMOUNT;
    }

    public void setmTRANSACTION_FEE(String mTRANSACTION_FEE) {
        this.mTRANSACTION_FEE = mTRANSACTION_FEE;
    }

    public void setmSTATUS(String mSTATUS) {
        this.mSTATUS = mSTATUS;
    }

    public void setmPENDING(String mPENDING) {
        this.mPENDING = mPENDING;
    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(int imageDrawable) {
        this.imageDrawable = imageDrawable;
    }
}
