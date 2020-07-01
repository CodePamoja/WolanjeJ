package com.example.wolanjej.RetrofitUtils;

import com.example.wolanjej.models.BalanceModel;
import com.example.wolanjej.models.ModelUserDetails;
import com.example.wolanjej.models.TranasactionHistory;
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

    public ApiJsonObjects(List<BalanceModel> balances, List<ModelUserDetails> userDetails, List<TranasactionHistory> tranasactionHistories) {
        this.balances = balances;
        this.userDetails = userDetails;
        this.tranasactionHistories = tranasactionHistories;
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
}
