package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FundTransferReportRecordModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("to_user_id")
    @Expose
    private String toUserId;
    @SerializedName("from_user_id")
    @Expose
    private String fromUserId;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("from_fullname")
    @Expose
    private String fromFullname;
    @SerializedName("to_fullname")
    @Expose
    private String toFullname;
    @SerializedName("fund_status")
    @Expose
    private String fundStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFromFullname() {
        return fromFullname;
    }

    public void setFromFullname(String fromFullname) {
        this.fromFullname = fromFullname;
    }

    public String getToFullname() {
        return toFullname;
    }

    public void setToFullname(String toFullname) {
        this.toFullname = toFullname;
    }

    public String getFundStatus() {
        return fundStatus;
    }

    public void setFundStatus(String fundStatus) {
        this.fundStatus = fundStatus;
    }

}
