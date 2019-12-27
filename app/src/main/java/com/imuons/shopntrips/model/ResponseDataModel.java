package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseDataModel implements Serializable {
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("unique_user_id")
    @Expose
    public String uniqueUserId;
    @SerializedName("unique_ref_id")
    @Expose
    public Integer uniqueRefId;
    @SerializedName("remember_token")
    @Expose
    public String rememberToken;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUniqueUserId() {
        return uniqueUserId;
    }

    public void setUniqueUserId(String uniqueUserId) {
        this.uniqueUserId = uniqueUserId;
    }

    public Integer getUniqueRefId() {
        return uniqueRefId;
    }

    public void setUniqueRefId(Integer uniqueRefId) {
        this.uniqueRefId = uniqueRefId;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

}
