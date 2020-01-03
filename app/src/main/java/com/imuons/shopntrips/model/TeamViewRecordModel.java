package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamViewRecordModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("sponser_id")
    @Expose
    private String sponserId;
    @SerializedName("upline_id")
    @Expose
    private String uplineId;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("total_business")
    @Expose
    private Integer totalBusiness;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("left_id")
    @Expose
    private Integer leftId;
    @SerializedName("right_id")
    @Expose
    private Integer rightId;
    @SerializedName("left_bv")
    @Expose
    private Integer leftBv;
    @SerializedName("right_bv")
    @Expose
    private Integer rightBv;
    @SerializedName("left_bv_rep")
    @Expose
    private Integer leftBvRep;
    @SerializedName("right_bv_rep")
    @Expose
    private Integer rightBvRep;
    @SerializedName("pin_number")
    @Expose
    private String pinNumber;
    @SerializedName("joining_date")
    @Expose
    private String joiningDate;
    @SerializedName("total_investment")
    @Expose
    private Integer totalInvestment;
    @SerializedName("product")
    @Expose
    private TeamViewProductModel product;

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

    public String getSponserId() {
        return sponserId;
    }

    public void setSponserId(String sponserId) {
        this.sponserId = sponserId;
    }

    public String getUplineId() {
        return uplineId;
    }

    public void setUplineId(String uplineId) {
        this.uplineId = uplineId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getLeftId() {
        return leftId;
    }

    public void setLeftId(Integer leftId) {
        this.leftId = leftId;
    }

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    public Integer getLeftBv() {
        return leftBv;
    }

    public void setLeftBv(Integer leftBv) {
        this.leftBv = leftBv;
    }

    public Integer getRightBv() {
        return rightBv;
    }

    public void setRightBv(Integer rightBv) {
        this.rightBv = rightBv;
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

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Integer getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(Integer totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public TeamViewProductModel getProduct() {
        return product;
    }

    public void setProduct(TeamViewProductModel product) {
        this.product = product;
    }

}

