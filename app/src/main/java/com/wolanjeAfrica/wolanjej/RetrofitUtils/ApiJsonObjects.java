package com.wolanjeAfrica.wolanjej.RetrofitUtils;

import com.wolanjeAfrica.wolanjej.models.BalanceModel;
import com.wolanjeAfrica.wolanjej.models.ListUserBills;
import com.wolanjeAfrica.wolanjej.models.ModelUserDetails;
import com.wolanjeAfrica.wolanjej.models.TranasactionHistory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiJsonObjects {
    @SerializedName("balance")
    @Expose
    private List<BalanceModel> balances;

    @SerializedName("profiles")
    @Expose
    private List<ModelUserDetails> userDetails;

    @SerializedName("services")
    @Expose
    private List<TranasactionHistory> tranasactionHistories;

    @SerializedName("bills")
    @Expose
    private List<ListUserBills> listUserBills;

    public ApiJsonObjects(List<ListUserBills> listUserBills,List<BalanceModel> balances, List<ModelUserDetails> userDetails, List<TranasactionHistory> tranasactionHistories) {
        this.balances = balances;
        this.userDetails = userDetails;
        this.tranasactionHistories = tranasactionHistories;
        this.listUserBills = listUserBills;
    }

    public List<BalanceModel> getBalances() {
        return balances;
    }

    public List<ModelUserDetails> getUserDetails() {
        return userDetails;
    }

    public List<TranasactionHistory> getHistory(){
        return tranasactionHistories;
    }

    public List<ListUserBills> getListUserBills(){
        return listUserBills;
    }
}
