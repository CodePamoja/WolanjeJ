package com.wolanjeAfrica.wolanjej.models;

public class Merchant {

    private String name;
    private String phoneNumber;
    private String Email;
    private String IdNumber;
    private String CompanyCeoName;
    private String CompanyCeoEmail;
    private String CompanyCeoIdNumber;

    public Merchant() {
    }

    public Merchant(String name, String phoneNumber, String email, String idNumber, String companyCeoName, String companyCeoEmail, String companyCeoIdNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        Email = email;
        IdNumber = idNumber;
        CompanyCeoName = companyCeoName;
        CompanyCeoEmail = companyCeoEmail;
        CompanyCeoIdNumber = companyCeoIdNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getCompanyCeoName() {
        return CompanyCeoName;
    }

    public void setCompanyCeoName(String companyCeoName) {
        CompanyCeoName = companyCeoName;
    }

    public String getCompanyCeoEmail() {
        return CompanyCeoEmail;
    }

    public void setCompanyCeoEmail(String companyCeoEmail) {
        CompanyCeoEmail = companyCeoEmail;
    }

    public String getCompanyCeoIdNumber() {
        return CompanyCeoIdNumber;
    }

    public void setCompanyCeoIdNumber(String companyCeoIdNumber) {
        CompanyCeoIdNumber = companyCeoIdNumber;
    }
}
