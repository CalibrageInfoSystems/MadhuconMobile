package com.trst01.locationtracker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseDTO {

    @SerializedName("UserName")
    @Expose
    private String UserName;

    @SerializedName("Token")
    @Expose
    public String Token ;

    @SerializedName("TokenExpire")
    @Expose
    public String TokenExpire ;

    @SerializedName("accessToken")
    @Expose
    public String accessToken ;

    @SerializedName("Role")
    @Expose
    public String Role ;

    @SerializedName("Message")
    @Expose
    public String Message="" ;

    @SerializedName("Status")
    @Expose
    public String Status="" ;

    @SerializedName("Id")
    @Expose
    public Integer Id ;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getTokenExpire() {
        return TokenExpire;
    }

    public void setTokenExpire(String tokenExpire) {
        TokenExpire = tokenExpire;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
