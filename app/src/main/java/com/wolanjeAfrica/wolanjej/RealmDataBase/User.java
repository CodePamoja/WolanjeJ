package com.wolanjeAfrica.wolanjej.RealmDataBase;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.RealmField;

@RealmClass("user")
public class User extends RealmObject {
    @PrimaryKey
    private int id;
    @RealmField(name = "first_name")
    private String FirstName;
    @RealmField(name = "last_name")
    private String LastName;
    @RealmField(name = "email")
    private String email;
    @RealmField(name = "Gender")
    private String Gender;
    @RealmField(name = "phoneNumber")
    private String phoneNumber;
    @RealmField(name = "password")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

