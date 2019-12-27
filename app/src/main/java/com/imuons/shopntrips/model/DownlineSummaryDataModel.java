package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DownlineSummaryDataModel {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("l_c_count")
    @Expose
    private Integer lCCount;
    @SerializedName("r_c_count")
    @Expose
    private Integer rCCount;
    @SerializedName("r_bv")
    @Expose
    private Integer rBv;
    @SerializedName("l_bv")
    @Expose
    private Integer lBv;
    @SerializedName("active_l")
    @Expose
    private Integer activeL;
    @SerializedName("active_r")
    @Expose
    private Integer activeR;
    @SerializedName("carry_left_bv")
    @Expose
    private Integer carryLeftBv;
    @SerializedName("carry_right_bv")
    @Expose
    private Integer carryRightBv;

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

    public Integer getLCCount() {
        return lCCount;
    }

    public void setLCCount(Integer lCCount) {
        this.lCCount = lCCount;
    }

    public Integer getRCCount() {
        return rCCount;
    }

    public void setRCCount(Integer rCCount) {
        this.rCCount = rCCount;
    }

    public Integer getRBv() {
        return rBv;
    }

    public void setRBv(Integer rBv) {
        this.rBv = rBv;
    }

    public Integer getLBv() {
        return lBv;
    }

    public void setLBv(Integer lBv) {
        this.lBv = lBv;
    }

    public Integer getActiveL() {
        return activeL;
    }

    public void setActiveL(Integer activeL) {
        this.activeL = activeL;
    }

    public Integer getActiveR() {
        return activeR;
    }

    public void setActiveR(Integer activeR) {
        this.activeR = activeR;
    }

    public Integer getCarryLeftBv() {
        return carryLeftBv;
    }

    public void setCarryLeftBv(Integer carryLeftBv) {
        this.carryLeftBv = carryLeftBv;
    }

    public Integer getCarryRightBv() {
        return carryRightBv;
    }

    public void setCarryRightBv(Integer carryRightBv) {
        this.carryRightBv = carryRightBv;
    }

}
