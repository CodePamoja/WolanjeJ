package com.example.wolanjej.models;

import com.google.gson.annotations.SerializedName;

public class BalanceModel {
    @SerializedName("id")
    private String id;

    @SerializedName("created_on")
    private String created_on;

    @SerializedName("created_by")
    private String created_by;

    @SerializedName("created_by_id")
    private String created_by_id;

    @SerializedName("ac_id")
    private String ac_id;

    @SerializedName("ac_uname")
    private String ac_uname;

    @SerializedName("balance")
    private String balance;

    @SerializedName("balance_uncommitted")
    private String balance_uncommitted;


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

    public String getAc_id() {
        return ac_id;
    }

    public String getAc_uname() {
        return ac_uname;
    }

    public String getBalance() {
        return balance;
    }

    public String getBalance_uncommitted() {
        return balance_uncommitted;
    }
}
