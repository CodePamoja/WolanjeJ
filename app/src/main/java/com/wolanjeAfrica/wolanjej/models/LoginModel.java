package com.wolanjeAfrica.wolanjej.models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("session_token")
    private  String session_token;

    @SerializedName("id")
    private String id;

    @SerializedName("role")
    private String role;

    @SerializedName("user_name")
    private String user_name;

    @SerializedName("agentno")
    private String agentno;

    public LoginModel(String session_token, String id, String role, String user_name, String agentno) {
        this.session_token = session_token;
        this.id = id;
        this.role = role;
        this.user_name = user_name;
        this.agentno = agentno;
    }


    public String getSession_token() {
        return session_token;
    }

    public void setSession_token(String session_token) {
        this.session_token = session_token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAgentno() {
        return agentno;
    }

    public void setAgentno(String agentno) {
        this.agentno = agentno;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "session_token='" + session_token + '\'' +
                ", id='" + id + '\'' +
                ", role='" + role + '\'' +
                ", user_name='" + user_name + '\'' +
                ", agentno='" + agentno + '\'' +
                '}';
    }
}
