package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AwardReportRecordModel {
    @SerializedName("win_id")
    @Expose
    private Integer winId;
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
    @SerializedName("left_bv_for_award")
    @Expose
    private String leftBvForAward;
    @SerializedName("right_bv_for_award")
    @Expose
    private Integer rightBvForAward;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("won_on_date")
    @Expose
    private String wonOnDate;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("user_l_bv")
    @Expose
    private Integer userLBv;
    @SerializedName("user_r_bv")
    @Expose
    private Integer userRBv;

    public Integer getWinId() {
        return winId;
    }

    public void setWinId(Integer winId) {
        this.winId = winId;
    }

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

    public String getLeftBvForAward() {
        return leftBvForAward;
    }

    public void setLeftBvForAward(String leftBvForAward) {
        this.leftBvForAward = leftBvForAward;
    }

    public Integer getRightBvForAward() {
        return rightBvForAward;
    }

    public void setRightBvForAward(Integer rightBvForAward) {
        this.rightBvForAward = rightBvForAward;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getWonOnDate() {
        return wonOnDate;
    }

    public void setWonOnDate(String wonOnDate) {
        this.wonOnDate = wonOnDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
