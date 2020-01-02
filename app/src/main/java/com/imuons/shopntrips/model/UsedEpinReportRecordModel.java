package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsedEpinReportRecordModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("used_by")
    @Expose
    private Integer usedBy;
    @SerializedName("used_by_userId")
    @Expose
    private String usedByUserId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("used_date")
    @Expose
    private String usedDate;

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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(Integer usedBy) {
        this.usedBy = usedBy;
    }

    public String getUsedByUserId() {
        return usedByUserId;
    }

    public void setUsedByUserId(String usedByUserId) {
        this.usedByUserId = usedByUserId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(String usedDate) {
        this.usedDate = usedDate;
    }
}
