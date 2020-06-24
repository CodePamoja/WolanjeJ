package com.example.wolanjej.models;

import java.util.ArrayList;

public class TranasactionHistory {

    private String mMONTHS;
    private String mDATE;
    private String mTOP_UP_AMOUNT ;
    private String mTRANSACTION_FEE;
    private String mSTATUS;
    private String mPENDING;

    public TranasactionHistory() {

    }

    public TranasactionHistory(String mMONTHS, String mDATE, String mTOP_UP_AMOUNT, String mTRANSACTION_FEE, String mSTATUS, String mPENDING) {
        this.mMONTHS = mMONTHS;
        this.mDATE = mDATE;
        this.mTOP_UP_AMOUNT = mTOP_UP_AMOUNT;
        this.mTRANSACTION_FEE = mTRANSACTION_FEE;
        this.mSTATUS = mSTATUS;
        this.mPENDING = mPENDING;
    }

    //    GETTERS

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

}
