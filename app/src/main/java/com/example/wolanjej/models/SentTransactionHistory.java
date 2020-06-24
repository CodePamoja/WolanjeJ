package com.example.wolanjej.models;

public class SentTransactionHistory {
    private String mMonth;
    private String mDate;
    private int mPic;
    private  String mName;
    private String mPhoneNumber;
    private String mPaymentMethod;
    private String mTransactionAmount;
    private String mTransactionFees;



    public SentTransactionHistory(String mMonth, String mDate, int mPic, String mName, String mPhoneNumber, String mPaymentMethod, String mTransactionAmount, String mTransactionFees) {
        this.mMonth = mMonth;
        this.mDate = mDate;
        this.mPic = mPic;
        this.mName = mName;
        this.mPhoneNumber = mPhoneNumber;
        this.mPaymentMethod = mPaymentMethod;
        this.mTransactionAmount = mTransactionAmount;
        this.mTransactionFees = mTransactionFees;
    }

//    getters

    public String getmMonth() {
        return mMonth;
    }

    public String getmDate() {
        return mDate;
    }

    public int getmPic() {
        return mPic;
    }

    public String getmName() {
        return mName;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public String getmPaymentMethod() {
        return mPaymentMethod;
    }

    public String getmTransactionAmount() {
        return mTransactionAmount;
    }

    public String getmTransactionFees() {
        return mTransactionFees;
    }


//    setters


    public void setmMonth(String mMonth) {
        this.mMonth = mMonth;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public void setmPic(int mPic) {
        this.mPic = mPic;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public void setmPaymentMethod(String mPaymentMethod){
        this.mPaymentMethod = mPaymentMethod;
    }

    public void setmTransactionAmount(String mTransactionAmount) {
        this.mTransactionAmount = mTransactionAmount;
    }

    public void setmTransactionFees(String mTransactionFees) {
        this.mTransactionFees = mTransactionFees;
    }
}
