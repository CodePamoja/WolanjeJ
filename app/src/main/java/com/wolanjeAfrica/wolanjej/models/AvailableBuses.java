package com.wolanjeAfrica.wolanjej.models;

import android.os.Parcel;
import android.os.Parcelable;

public class AvailableBuses implements Parcelable {
    private String mBusCompanyImg;
    private String mBusCompanyId;
    private String mBusFlightHour;
    private String mBusFlightMin;
    private String mBusFlightStops;
    private String mBusFlightCosts;
    private String mBusTicketsLeft;
    private String mBusFlightFrom;
    private String mBusFlightDepTime;
    private String mBusFlightTo;
    private String mBusFlightArrivalTime;

    public AvailableBuses() {
    }

    public AvailableBuses(String mBusCompanyImg, String mBusCompanyId, String mBusFlightHour, String mBusFlightMin, String mBusFlightStops, String mBusFlightCosts, String mBusTicketsLeft, String mBusFlightFrom, String mBusFlightDepTime, String mBusFlightTo, String mBusFlightArrivalTime) {
        this.mBusCompanyImg = mBusCompanyImg;
        this.mBusCompanyId = mBusCompanyId;
        this.mBusFlightHour = mBusFlightHour;
        this.mBusFlightMin = mBusFlightMin;
        this.mBusFlightStops = mBusFlightStops;
        this.mBusFlightCosts = mBusFlightCosts;
        this.mBusTicketsLeft = mBusTicketsLeft;
        this.mBusFlightFrom = mBusFlightFrom;
        this.mBusFlightDepTime = mBusFlightDepTime;
        this.mBusFlightTo = mBusFlightTo;
        this.mBusFlightArrivalTime = mBusFlightArrivalTime;
    }


    protected AvailableBuses(Parcel in) {
        mBusCompanyImg = in.readString();
        mBusCompanyId = in.readString();
        mBusFlightHour = in.readString();
        mBusFlightMin = in.readString();
        mBusFlightStops = in.readString();
        mBusFlightCosts = in.readString();
        mBusTicketsLeft = in.readString();
        mBusFlightFrom = in.readString();
        mBusFlightDepTime = in.readString();
        mBusFlightTo = in.readString();
        mBusFlightArrivalTime = in.readString();
    }

    public static final Creator<AvailableBuses> CREATOR = new Creator<AvailableBuses>() {
        @Override
        public AvailableBuses createFromParcel(Parcel in) {
            return new AvailableBuses(in);
        }

        @Override
        public AvailableBuses[] newArray(int size) {
            return new AvailableBuses[size];
        }
    };

    public String getmBusCompanyImg() {
        return mBusCompanyImg;
    }

    public String getmBusCompanyId() {
        return mBusCompanyId;
    }

    public String getmBusFlightHour() {
        return mBusFlightHour;
    }

    public String getmBusFlightMin() {
        return mBusFlightMin;
    }

    public String getmBusFlightStops() {
        return mBusFlightStops;
    }

    public String getmBusFlightCosts() {
        return mBusFlightCosts;
    }

    public String getmBusTicketsLeft() {
        return mBusTicketsLeft;
    }

    public String getmBusFlightFrom() {
        return mBusFlightFrom;
    }

    public String getmBusFlightDepTime() {
        return mBusFlightDepTime;
    }

    public String getmBusFlightTo() {
        return mBusFlightTo;
    }

    public String getmBusFlightArrivalTime() {
        return mBusFlightArrivalTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mBusCompanyImg);
        dest.writeString(mBusCompanyId);
        dest.writeString(mBusFlightHour);
        dest.writeString(mBusFlightMin);
        dest.writeString(mBusFlightStops);
        dest.writeString(mBusFlightCosts);
        dest.writeString(mBusTicketsLeft);
        dest.writeString(mBusFlightFrom);
        dest.writeString(mBusFlightDepTime);
        dest.writeString(mBusFlightTo);
        dest.writeString(mBusFlightArrivalTime);
    }
}
