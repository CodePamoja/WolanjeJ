package com.example.wolanjej.models;

import com.google.gson.annotations.SerializedName;

public class ModelUserDetails {
    @SerializedName("firstname")
    private String FIRST_NAME;

    @SerializedName("lastname")
    private String LAST_NAME;

    @SerializedName("othernames")
    private String OTHER_NAME;

    @SerializedName("address1")
    private String ADDRESS1;

    @SerializedName("address2")
    private String ADDRESS2;

    @SerializedName("town")
    private String TOWN;

    @SerializedName("state")
    private String STATE;

    @SerializedName("city")
    private String CITY;

    @SerializedName("district")
    private String DISTRICT;

    @SerializedName("county")
    private String COUNTY;

    @SerializedName("province")
    private String PROVINCE;

    @SerializedName("country")
    private String COUNTRY;

    @SerializedName("dob")
    private String DATE_OF_BIRTH;

    @SerializedName("gender")
    private String GENDER;

    @SerializedName("passport_no")
    private String PASSPORT;

    @SerializedName("national_id_no")
    private String NATIONAL_ID;

    @SerializedName("facebook")
    private String FACEBOOK;

    @SerializedName("twitter")
    private String TWITTER;


    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public void setOTHER_NAME(String OTHER_NAME) {
        this.OTHER_NAME = OTHER_NAME;
    }

    public void setADDRESS1(String ADDRESS1) {
        this.ADDRESS1 = ADDRESS1;
    }

    public void setADDRESS2(String ADDRESS2) {
        this.ADDRESS2 = ADDRESS2;
    }

    public void setTOWN(String TOWN) {
        this.TOWN = TOWN;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public void setDISTRICT(String DISTRICT) {
        this.DISTRICT = DISTRICT;
    }

    public void setCOUNTY(String COUNTY) {
        this.COUNTY = COUNTY;
    }

    public void setPROVINCE(String PROVINCE) {
        this.PROVINCE = PROVINCE;
    }

    public void setCOUNTRY(String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }

    public void setDATE_OF_BIRTH(String DATE_OF_BIRTH) {
        this.DATE_OF_BIRTH = DATE_OF_BIRTH;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public void setPASSPORT(String PASSPORT) {
        this.PASSPORT = PASSPORT;
    }

    public void setNATIONAL_ID(String NATIONAL_ID) {
        this.NATIONAL_ID = NATIONAL_ID;
    }

    public void setFACEBOOK(String FACEBOOK) {
        this.FACEBOOK = FACEBOOK;
    }

    public void setTWITTER(String TWITTER) {
        this.TWITTER = TWITTER;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public String getOTHER_NAME() {
        return OTHER_NAME;
    }

    public String getADDRESS1() {
        return ADDRESS1;
    }

    public String getADDRESS2() {
        return ADDRESS2;
    }

    public String getTOWN() {
        return TOWN;
    }

    public String getSTATE() {
        return STATE;
    }

    public String getCITY() {
        return CITY;
    }

    public String getDISTRICT() {
        return DISTRICT;
    }

    public String getCOUNTY() {
        return COUNTY;
    }

    public String getPROVINCE() {
        return PROVINCE;
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public String getDATE_OF_BIRTH() {
        return DATE_OF_BIRTH;
    }

    public String getGENDER() {
        return GENDER;
    }

    public String getPASSPORT() {
        return PASSPORT;
    }

    public String getNATIONAL_ID() {
        return NATIONAL_ID;
    }

    public String getFACEBOOK() {
        return FACEBOOK;
    }

    public String getTWITTER() {
        return TWITTER;
    }
}
