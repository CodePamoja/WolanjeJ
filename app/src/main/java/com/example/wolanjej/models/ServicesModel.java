package com.example.wolanjej.models;

public class ServicesModel {
    public String Id;
    public int created_on;
    public String created_by;
    public int req_id;
    public int ac_uname;
    public String product_name;
    public String vendor_name;
    public int ref;
    public int phoneNumber;
    public String email;
    public int amountTransaction;
    public int transactionFee;
    public String last_status;
    public int receipt_id;

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
