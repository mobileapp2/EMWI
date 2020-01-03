package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DirectUserListRecordModel
{
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("total_business")
    @Expose
    private Integer totalBusiness;
    @SerializedName("position")
    @Expose
    private String position;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTotalBusiness() {
        return totalBusiness;
    }

    public void setTotalBusiness(Integer totalBusiness) {
        this.totalBusiness = totalBusiness;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
