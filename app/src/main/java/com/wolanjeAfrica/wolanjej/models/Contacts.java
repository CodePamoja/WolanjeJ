package com.wolanjeAfrica.wolanjej.models;

public class Contacts {
    String CName;
    String CPhone;
    String profilePic;

//    public Contacts(String name, String phone, Bitmap image) {
//        this.CName = name;
//        this.CPhone = phone;
//        this.CImage = image;
////    }
//
    public String getName() {
        return CName;
    }

    public void setName(String name) {
        this.CName = name;
    }

    public String getPhone() {
        return CPhone;
    }

    public void setPhone(String phone) {
        this.CPhone = phone;
    }

    public String getImage() {
        return profilePic;
    }

    public void setImage(String image) {
        this.profilePic = image;
    }

}
