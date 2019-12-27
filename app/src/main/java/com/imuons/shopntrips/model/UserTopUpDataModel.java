package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserTopUpDataModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("from_user_id")
    @Expose
    private Integer fromUserId;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("bvalue")
    @Expose
    private Integer bvalue;
    @SerializedName("percentage")
    @Expose
    private String percentage;
    @SerializedName("product_id")
    @Expose
    private Object productId;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("roi_status")
    @Expose
    private String roiStatus;
    @SerializedName("capping_status")
    @Expose
    private String cappingStatus;
    @SerializedName("bv_pass_status")
    @Expose
    private String bvPassStatus;
    @SerializedName("topup_no")
    @Expose
    private Integer topupNo;
    @SerializedName("roi_number")
    @Expose
    private Integer roiNumber;
    @SerializedName("direct_income_status")
    @Expose
    private Integer directIncomeStatus;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("amount_pass_status")
    @Expose
    private String amountPassStatus;
    @SerializedName("remove_bv")
    @Expose
    private String removeBv;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
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

    public Integer getBvalue() {
        return bvalue;
    }

    public void setBvalue(Integer bvalue) {
        this.bvalue = bvalue;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Object getProductId() {
        return productId;
    }

    public void setProductId(Object productId) {
        this.productId = productId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoiStatus() {
        return roiStatus;
    }

    public void setRoiStatus(String roiStatus) {
        this.roiStatus = roiStatus;
    }

    public String getCappingStatus() {
        return cappingStatus;
    }

    public void setCappingStatus(String cappingStatus) {
        this.cappingStatus = cappingStatus;
    }

    public String getBvPassStatus() {
        return bvPassStatus;
    }

    public void setBvPassStatus(String bvPassStatus) {
        this.bvPassStatus = bvPassStatus;
    }

    public Integer getTopupNo() {
        return topupNo;
    }

    public void setTopupNo(Integer topupNo) {
        this.topupNo = topupNo;
    }

    public Integer getRoiNumber() {
        return roiNumber;
    }

    public void setRoiNumber(Integer roiNumber) {
        this.roiNumber = roiNumber;
    }

    public Integer getDirectIncomeStatus() {
        return directIncomeStatus;
    }

    public void setDirectIncomeStatus(Integer directIncomeStatus) {
        this.directIncomeStatus = directIncomeStatus;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAmountPassStatus() {
        return amountPassStatus;
    }

    public void setAmountPassStatus(String amountPassStatus) {
        this.amountPassStatus = amountPassStatus;
    }

    public String getRemoveBv() {
        return removeBv;
    }

    public void setRemoveBv(String removeBv) {
        this.removeBv = removeBv;
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
