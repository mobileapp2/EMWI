package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProductsPinRequestDataModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("bvalue")
    @Expose
    private String bvalue;
    @SerializedName("usd")
    @Expose
    private Integer usd;
    @SerializedName("mp")
    @Expose
    private Integer mp;
    @SerializedName("pin_prefix")
    @Expose
    private String pinPrefix;
    @SerializedName("direct_income")
    @Expose
    private String directIncome;
    @SerializedName("per_pair_amount")
    @Expose
    private Integer perPairAmount;
    @SerializedName("binary_per")
    @Expose
    private Integer binaryPer;
    @SerializedName("percentage")
    @Expose
    private String percentage;
    @SerializedName("max_percentage")
    @Expose
    private Integer maxPercentage;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("capping")
    @Expose
    private Integer capping;
    @SerializedName("repurchase_limit")
    @Expose
    private String repurchaseLimit;
    @SerializedName("status_product")
    @Expose
    private String statusProduct;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("product_use_type")
    @Expose
    private String productUseType;
    @SerializedName("binary_percentage")
    @Expose
    private String binaryPercentage;
    @SerializedName("binary_status")
    @Expose
    private String binaryStatus;
    @SerializedName("order_by")
    @Expose
    private Integer orderBy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("roi_1")
    @Expose
    private Integer roi1;
    @SerializedName("roi_2")
    @Expose
    private Integer roi2;
    @SerializedName("date_diff")
    @Expose
    private Integer dateDiff;
    @SerializedName("monthly_roi")
    @Expose
    private String monthlyRoi;
    @SerializedName("weekly_roi")
    @Expose
    private String weeklyRoi;
    @SerializedName("direct_roi_percentage")
    @Expose
    private String directRoiPercentage;
    @SerializedName("joining_count")
    @Expose
    private String joiningCount;
    @SerializedName("no_of_pins_available")
    @Expose
    private Integer noOfPinsAvailable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getBvalue() {
        return bvalue;
    }

    public void setBvalue(String bvalue) {
        this.bvalue = bvalue;
    }

    public Integer getUsd() {
        return usd;
    }

    public void setUsd(Integer usd) {
        this.usd = usd;
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public String getPinPrefix() {
        return pinPrefix;
    }

    public void setPinPrefix(String pinPrefix) {
        this.pinPrefix = pinPrefix;
    }

    public String getDirectIncome() {
        return directIncome;
    }

    public void setDirectIncome(String directIncome) {
        this.directIncome = directIncome;
    }

    public Integer getPerPairAmount() {
        return perPairAmount;
    }

    public void setPerPairAmount(Integer perPairAmount) {
        this.perPairAmount = perPairAmount;
    }

    public Integer getBinaryPer() {
        return binaryPer;
    }

    public void setBinaryPer(Integer binaryPer) {
        this.binaryPer = binaryPer;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Integer getMaxPercentage() {
        return maxPercentage;
    }

    public void setMaxPercentage(Integer maxPercentage) {
        this.maxPercentage = maxPercentage;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCapping() {
        return capping;
    }

    public void setCapping(Integer capping) {
        this.capping = capping;
    }

    public String getRepurchaseLimit() {
        return repurchaseLimit;
    }

    public void setRepurchaseLimit(String repurchaseLimit) {
        this.repurchaseLimit = repurchaseLimit;
    }

    public String getStatusProduct() {
        return statusProduct;
    }

    public void setStatusProduct(String statusProduct) {
        this.statusProduct = statusProduct;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductUseType() {
        return productUseType;
    }

    public void setProductUseType(String productUseType) {
        this.productUseType = productUseType;
    }

    public String getBinaryPercentage() {
        return binaryPercentage;
    }

    public void setBinaryPercentage(String binaryPercentage) {
        this.binaryPercentage = binaryPercentage;
    }

    public String getBinaryStatus() {
        return binaryStatus;
    }

    public void setBinaryStatus(String binaryStatus) {
        this.binaryStatus = binaryStatus;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
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

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getRoi1() {
        return roi1;
    }

    public void setRoi1(Integer roi1) {
        this.roi1 = roi1;
    }

    public Integer getRoi2() {
        return roi2;
    }

    public void setRoi2(Integer roi2) {
        this.roi2 = roi2;
    }

    public Integer getDateDiff() {
        return dateDiff;
    }

    public void setDateDiff(Integer dateDiff) {
        this.dateDiff = dateDiff;
    }

    public String getMonthlyRoi() {
        return monthlyRoi;
    }

    public void setMonthlyRoi(String monthlyRoi) {
        this.monthlyRoi = monthlyRoi;
    }

    public String getWeeklyRoi() {
        return weeklyRoi;
    }

    public void setWeeklyRoi(String weeklyRoi) {
        this.weeklyRoi = weeklyRoi;
    }

    public String getDirectRoiPercentage() {
        return directRoiPercentage;
    }

    public void setDirectRoiPercentage(String directRoiPercentage) {
        this.directRoiPercentage = directRoiPercentage;
    }

    public String getJoiningCount() {
        return joiningCount;
    }

    public void setJoiningCount(String joiningCount) {
        this.joiningCount = joiningCount;
    }

    public Integer getNoOfPinsAvailable() {
        return noOfPinsAvailable;
    }

    public void setNoOfPinsAvailable(Integer noOfPinsAvailable) {
        this.noOfPinsAvailable = noOfPinsAvailable;
    }
}
