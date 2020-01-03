package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TreeViewUserModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("sponsor_id")
    @Expose
    private String sponsorId;
    @SerializedName("sponsor_fullname")
    @Expose
    private String sponsorFullname;
    @SerializedName("virtual_id")
    @Expose
    private String virtualId;
    @SerializedName("virtual_fullname")
    @Expose
    private String virtualFullname;
    @SerializedName("l_c_count")
    @Expose
    private Integer lCCount;
    @SerializedName("r_c_count")
    @Expose
    private Integer rCCount;
    @SerializedName("l_bv")
    @Expose
    private Integer lBv;
    @SerializedName("r_bv")
    @Expose
    private Integer rBv;
    @SerializedName("l_bv_rep")
    @Expose
    private Integer lBvRep;
    @SerializedName("r_bv_rep")
    @Expose
    private Integer rBvRep;
    @SerializedName("left_bv")
    @Expose
    private String leftBv;
    @SerializedName("right_bv")
    @Expose
    private String rightBv;
    @SerializedName("carry_left_bv_rep")
    @Expose
    private String carryLeftBvRep;
    @SerializedName("carry_right_bv_rep")
    @Expose
    private String carryRightBvRep;
    @SerializedName("left_bv_rep")
    @Expose
    private Integer leftBvRep;
    @SerializedName("right_bv_rep")
    @Expose
    private Integer rightBvRep;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("act_time")
    @Expose
    private String actTime;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("left_amount")
    @Expose
    private Integer leftAmount;
    @SerializedName("right_amount")
    @Expose
    private Integer rightAmount;

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

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getSponsorFullname() {
        return sponsorFullname;
    }

    public void setSponsorFullname(String sponsorFullname) {
        this.sponsorFullname = sponsorFullname;
    }

    public String getVirtualId() {
        return virtualId;
    }

    public void setVirtualId(String virtualId) {
        this.virtualId = virtualId;
    }

    public String getVirtualFullname() {
        return virtualFullname;
    }

    public void setVirtualFullname(String virtualFullname) {
        this.virtualFullname = virtualFullname;
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

    public Integer getLBv() {
        return lBv;
    }

    public void setLBv(Integer lBv) {
        this.lBv = lBv;
    }

    public Integer getRBv() {
        return rBv;
    }

    public void setRBv(Integer rBv) {
        this.rBv = rBv;
    }

    public Integer getLBvRep() {
        return lBvRep;
    }

    public void setLBvRep(Integer lBvRep) {
        this.lBvRep = lBvRep;
    }

    public Integer getRBvRep() {
        return rBvRep;
    }

    public void setRBvRep(Integer rBvRep) {
        this.rBvRep = rBvRep;
    }

    public String getLeftBv() {
        return leftBv;
    }

    public void setLeftBv(String leftBv) {
        this.leftBv = leftBv;
    }

    public String getRightBv() {
        return rightBv;
    }

    public void setRightBv(String rightBv) {
        this.rightBv = rightBv;
    }

    public String getCarryLeftBvRep() {
        return carryLeftBvRep;
    }

    public void setCarryLeftBvRep(String carryLeftBvRep) {
        this.carryLeftBvRep = carryLeftBvRep;
    }

    public String getCarryRightBvRep() {
        return carryRightBvRep;
    }

    public void setCarryRightBvRep(String carryRightBvRep) {
        this.carryRightBvRep = carryRightBvRep;
    }

    public Integer getLeftBvRep() {
        return leftBvRep;
    }

    public void setLeftBvRep(Integer leftBvRep) {
        this.leftBvRep = leftBvRep;
    }

    public Integer getRightBvRep() {
        return rightBvRep;
    }

    public void setRightBvRep(Integer rightBvRep) {
        this.rightBvRep = rightBvRep;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getActTime() {
        return actTime;
    }

    public void setActTime(String actTime) {
        this.actTime = actTime;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Integer getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(Integer leftAmount) {
        this.leftAmount = leftAmount;
    }

    public Integer getRightAmount() {
        return rightAmount;
    }

    public void setRightAmount(Integer rightAmount) {
        this.rightAmount = rightAmount;
    }

}

