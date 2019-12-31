package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AwardReportRecordModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("award")
    @Expose
    private String award;
    @SerializedName("total_l_bv_match")
    @Expose
    private String totalLBvMatch;
    @SerializedName("total_r_bv_match")
    @Expose
    private Integer totalRBvMatch;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("user_l_bv")
    @Expose
    private Integer userLBv;
    @SerializedName("user_r_bv")
    @Expose
    private Integer userRBv;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getTotalLBvMatch() {
        return totalLBvMatch;
    }

    public void setTotalLBvMatch(String totalLBvMatch) {
        this.totalLBvMatch = totalLBvMatch;
    }

    public Integer getTotalRBvMatch() {
        return totalRBvMatch;
    }

    public void setTotalRBvMatch(Integer totalRBvMatch) {
        this.totalRBvMatch = totalRBvMatch;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getUserLBv() {
        return userLBv;
    }

    public void setUserLBv(Integer userLBv) {
        this.userLBv = userLBv;
    }

    public Integer getUserRBv() {
        return userRBv;
    }

    public void setUserRBv(Integer userRBv) {
        this.userRBv = userRBv;
    }
}
