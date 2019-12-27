package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopUpReportRecordModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("percentage")
    @Expose
    private String percentage;
    @SerializedName("topup_no")
    @Expose
    private Integer topupNo;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("to_entry_time")
    @Expose
    private String toEntryTime;
    @SerializedName("today_date")
    @Expose
    private String todayDate;
    @SerializedName("roi_qualify")
    @Expose
    private Integer roiQualify;

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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Integer getTopupNo() {
        return topupNo;
    }

    public void setTopupNo(Integer topupNo) {
        this.topupNo = topupNo;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getToEntryTime() {
        return toEntryTime;
    }

    public void setToEntryTime(String toEntryTime) {
        this.toEntryTime = toEntryTime;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public Integer getRoiQualify() {
        return roiQualify;
    }

    public void setRoiQualify(Integer roiQualify) {
        this.roiQualify = roiQualify;
    }
}
