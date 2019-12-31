package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BinaryIncomeReportRecordModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("net_amount")
    @Expose
    private Object netAmount;
    @SerializedName("tax_amount")
    @Expose
    private String taxAmount;
    @SerializedName("amt_pin")
    @Expose
    private String amtPin;
    @SerializedName("left_bv")
    @Expose
    private Integer leftBv;
    @SerializedName("right_bv")
    @Expose
    private Integer rightBv;
    @SerializedName("match_bv")
    @Expose
    private String matchBv;
    @SerializedName("laps_bv")
    @Expose
    private Integer lapsBv;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("pin")
    @Expose
    private Object pin;
    @SerializedName("remark")
    @Expose
    private Object remark;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("pair_no")
    @Expose
    private Object pairNo;
    @SerializedName("total_pair")
    @Expose
    private String totalPair;
    @SerializedName("paid_pair")
    @Expose
    private String paidPair;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("withdraw_date")
    @Expose
    private Object withdrawDate;
    @SerializedName("left_bv_carry")
    @Expose
    private Integer leftBvCarry;
    @SerializedName("right_bv_carry")
    @Expose
    private Integer rightBvCarry;
    @SerializedName("left_bv_before")
    @Expose
    private Integer leftBvBefore;
    @SerializedName("right_bv_before")
    @Expose
    private Integer rightBvBefore;
    @SerializedName("payout_no")
    @Expose
    private Integer payoutNo;
    @SerializedName("binary_roi")
    @Expose
    private String binaryRoi;
    @SerializedName("withdrawl_payout_no")
    @Expose
    private Object withdrawlPayoutNo;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("capping")
    @Expose
    private Integer capping;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Object getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Object netAmount) {
        this.netAmount = netAmount;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getAmtPin() {
        return amtPin;
    }

    public void setAmtPin(String amtPin) {
        this.amtPin = amtPin;
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

    public String getMatchBv() {
        return matchBv;
    }

    public void setMatchBv(String matchBv) {
        this.matchBv = matchBv;
    }

    public Integer getLapsBv() {
        return lapsBv;
    }

    public void setLapsBv(Integer lapsBv) {
        this.lapsBv = lapsBv;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public Object getPin() {
        return pin;
    }

    public void setPin(Object pin) {
        this.pin = pin;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getPairNo() {
        return pairNo;
    }

    public void setPairNo(Object pairNo) {
        this.pairNo = pairNo;
    }

    public String getTotalPair() {
        return totalPair;
    }

    public void setTotalPair(String totalPair) {
        this.totalPair = totalPair;
    }

    public String getPaidPair() {
        return paidPair;
    }

    public void setPaidPair(String paidPair) {
        this.paidPair = paidPair;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Object getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(Object withdrawDate) {
        this.withdrawDate = withdrawDate;
    }

    public Integer getLeftBvCarry() {
        return leftBvCarry;
    }

    public void setLeftBvCarry(Integer leftBvCarry) {
        this.leftBvCarry = leftBvCarry;
    }

    public Integer getRightBvCarry() {
        return rightBvCarry;
    }

    public void setRightBvCarry(Integer rightBvCarry) {
        this.rightBvCarry = rightBvCarry;
    }

    public Integer getLeftBvBefore() {
        return leftBvBefore;
    }

    public void setLeftBvBefore(Integer leftBvBefore) {
        this.leftBvBefore = leftBvBefore;
    }

    public Integer getRightBvBefore() {
        return rightBvBefore;
    }

    public void setRightBvBefore(Integer rightBvBefore) {
        this.rightBvBefore = rightBvBefore;
    }

    public Integer getPayoutNo() {
        return payoutNo;
    }

    public void setPayoutNo(Integer payoutNo) {
        this.payoutNo = payoutNo;
    }

    public String getBinaryRoi() {
        return binaryRoi;
    }

    public void setBinaryRoi(String binaryRoi) {
        this.binaryRoi = binaryRoi;
    }

    public Object getWithdrawlPayoutNo() {
        return withdrawlPayoutNo;
    }

    public void setWithdrawlPayoutNo(Object withdrawlPayoutNo) {
        this.withdrawlPayoutNo = withdrawlPayoutNo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getCapping() {
        return capping;
    }

    public void setCapping(Integer capping) {
        this.capping = capping;
    }
}
