package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DirectRoiReportRecordModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fromUserId")
    @Expose
    private String fromUserId;
    @SerializedName("toUserId")
    @Expose
    private String toUserId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("tds")
    @Expose
    private String tds;
    @SerializedName("admin_charges")
    @Expose
    private String adminCharges;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("net_amount")
    @Expose
    private String netAmount;
    @SerializedName("invoice_id")
    @Expose
    private String invoiceId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTds() {
        return tds;
    }

    public void setTds(String tds) {
        this.tds = tds;
    }

    public String getAdminCharges() {
        return adminCharges;
    }

    public void setAdminCharges(String adminCharges) {
        this.adminCharges = adminCharges;
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

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
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
}
