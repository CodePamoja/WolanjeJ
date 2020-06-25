package com.example.wolanjej.models;


public class AvailableFlights  {

    private int mAirwayImg;
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

    public AvailableFlights(int mAirwayImg, String mAirWaysId, String mFlightHour, String mFlightMin, String mFlightStops, String mFlightCosts, String mTicketsLeft, String mFlightFrom, String mFlightDepTime, String mFlightTo, String mFlightArrivalTime) {
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

    public int getmAirwayImg() {
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
}
