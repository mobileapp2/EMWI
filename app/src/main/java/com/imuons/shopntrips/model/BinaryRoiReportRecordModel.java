package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BinaryRoiReportRecordModel {
    @SerializedName("sr_no")
    @Expose
    private Integer srNo;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("daily_percentage")
    @Expose
    private Integer dailyPercentage;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("net_amount")
    @Expose
    private String netAmount;
    @SerializedName("tax_amount")
    @Expose
    private Integer taxAmount;
    @SerializedName("amt_pin")
    @Expose
    private String amtPin;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer getDailyPercentage() {
        return dailyPercentage;
    }

    public void setDailyPercentage(Integer dailyPercentage) {
        this.dailyPercentage = dailyPercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public Integer getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Integer taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getAmtPin() {
        return amtPin;
    }

    public void setAmtPin(String amtPin) {
        this.amtPin = amtPin;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }
}
