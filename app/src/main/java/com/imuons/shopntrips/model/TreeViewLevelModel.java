package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TreeViewLevelModel {
    @SerializedName("id")
    @Expose
    private String id;
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
    private String lCCount;
    @SerializedName("r_c_count")
    @Expose
    private String rCCount;
    @SerializedName("l_bv")
    @Expose
    private String lBv;
    @SerializedName("r_bv")
    @Expose
    private String rBv;
    @SerializedName("left_bv")
    @Expose
    private String leftBv;
    @SerializedName("right_bv")
    @Expose
    private String rightBv;
    @SerializedName("left_bv_rep")
    @Expose
    private String leftBvRep;
    @SerializedName("right_bv_rep")
    @Expose
    private String rightBvRep;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("act_time")
    @Expose
    private Object actTime;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("left_amount")
    @Expose
    private String leftAmount;
    @SerializedName("right_amount")
    @Expose
    private String rightAmount;
    @SerializedName("virtual_parent_id")
    @Expose
    private String virtualParentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getLCCount() {
        return lCCount;
    }

    public void setLCCount(String lCCount) {
        this.lCCount = lCCount;
    }

    public String getRCCount() {
        return rCCount;
    }

    public void setRCCount(String rCCount) {
        this.rCCount = rCCount;
    }

    public String getLBv() {
        return lBv;
    }

    public void setLBv(String lBv) {
        this.lBv = lBv;
    }

    public String getRBv() {
        return rBv;
    }

    public void setRBv(String rBv) {
        this.rBv = rBv;
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

    public String getLeftBvRep() {
        return leftBvRep;
    }

    public void setLeftBvRep(String leftBvRep) {
        this.leftBvRep = leftBvRep;
    }

    public String getRightBvRep() {
        return rightBvRep;
    }

    public void setRightBvRep(String rightBvRep) {
        this.rightBvRep = rightBvRep;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
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

    public Object getActTime() {
        return actTime;
    }

    public void setActTime(Object actTime) {
        this.actTime = actTime;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(String leftAmount) {
        this.leftAmount = leftAmount;
    }

    public String getRightAmount() {
        return rightAmount;
    }

    public void setRightAmount(String rightAmount) {
        this.rightAmount = rightAmount;
    }

    public String getVirtualParentId() {
        return virtualParentId;
    }

    public void setVirtualParentId(String virtualParentId) {
        this.virtualParentId = virtualParentId;
    }

}
