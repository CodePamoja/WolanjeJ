package com.example.wolanjej.models;

public class ServicesModel {
    private String Id;
    private int created_on;
    private String created_by;
    private int req_id;
    private int ac_uname;
    private String product_name;
    private String vendor_name;
    private int ref;
    private int phoneNumber;
    private String email;
    private int amountTransaction;
    private int transactionFee;
    private String last_status;
    private int receipt_id;

    public ServicesModel() {
    }

    public String getId() {
        return Id;
    }

    public int getCreated_on() {
        return created_on;
    }

    public String getCreated_by() {
        return created_by;
    }

    public int getReq_id() {
        return req_id;
    }

    public int getAc_uname() {
        return ac_uname;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public int getRef() {
        return ref;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getAmountTransaction() {
        return amountTransaction;
    }

    public int getTransactionFee() {
        return transactionFee;
    }

    public String getLast_status() {
        return last_status;
    }

    public int getReceipt_id() {
        return receipt_id;
    }

}
