package com.example.wolanjej.models;


import android.os.Parcel;
import android.os.Parcelable;

public class AvailableFlights implements Parcelable {

    private String mAirwayImg;
    private String mAirWaysId;
    private String mFlightHour;
    private String mFlightMin;
    private String mFlightStops;
    private String mFlightCosts;
    private String mTicketsLeft;
    private String mFlightFrom;
    private String mFlightDepTime;
    private String mFlightTo;
    private String mFlightArrivalTime;

    public AvailableFlights() {
    }

    public AvailableFlights(String mAirwayImg, String mAirWaysId, String mFlightHour, String mFlightMin, String mFlightStops, String mFlightCosts, String mTicketsLeft, String mFlightFrom, String mFlightDepTime, String mFlightTo, String mFlightArrivalTime) {
        this.mAirwayImg = mAirwayImg;
        this.mAirWaysId = mAirWaysId;
        this.mFlightHour = mFlightHour;
        this.mFlightMin = mFlightMin;
        this.mFlightStops = mFlightStops;
        this.mFlightCosts = mFlightCosts;
        this.mTicketsLeft = mTicketsLeft;
        this.mFlightFrom = mFlightFrom;
        this.mFlightDepTime = mFlightDepTime;
        this.mFlightTo = mFlightTo;
        this.mFlightArrivalTime = mFlightArrivalTime;
    }

    protected AvailableFlights(Parcel in) {
        mAirwayImg = in.readString();
        mAirWaysId = in.readString();
        mFlightHour = in.readString();
        mFlightMin = in.readString();
        mFlightStops = in.readString();
        mFlightCosts = in.readString();
        mTicketsLeft = in.readString();
        mFlightFrom = in.readString();
        mFlightDepTime = in.readString();
        mFlightTo = in.readString();
        mFlightArrivalTime = in.readString();
    }

    public static final Creator<AvailableFlights> CREATOR = new Creator<AvailableFlights>() {
        @Override
        public AvailableFlights createFromParcel(Parcel in) {
            return new AvailableFlights(in);
        }

        @Override
        public AvailableFlights[] newArray(int size) {
            return new AvailableFlights[size];
        }
    };

    public String getmAirwayImg() {
        return mAirwayImg;
    }

    public String getmAirWaysId() {
        return mAirWaysId;
    }

    public String getmFlightHour() {
        return mFlightHour;
    }

    public String getmFlightMin() {
        return mFlightMin;
    }

    public String getmFlightStops() {
        return mFlightStops;
    }

    public String getmFlightCosts() {
        return mFlightCosts;
    }

    public String getmTicketsLeft() {
        return mTicketsLeft;
    }

    public String getmFlightFrom() {
        return mFlightFrom;
    }

    public String getmFlightDepTime() {
        return mFlightDepTime;
    }

    public String getmFlightTo() {
        return mFlightTo;
    }

    public String getmFlightArrivalTime() {
        return mFlightArrivalTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAirwayImg);
        dest.writeString(mAirWaysId);
        dest.writeString(mFlightHour);
        dest.writeString(mFlightMin);
        dest.writeString(mFlightStops);
        dest.writeString(mFlightCosts);
        dest.writeString(mTicketsLeft);
        dest.writeString(mFlightFrom);
        dest.writeString(mFlightDepTime);
        dest.writeString(mFlightTo);
        dest.writeString(mFlightArrivalTime);
    }
}
