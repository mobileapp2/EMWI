package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoyalityIncomeReportRecordModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("on_amount")
    @Expose
    private Integer onAmount;
    @SerializedName("percentage")
    @Expose
    private Integer percentage;
    @SerializedName("qualified_users")
    @Expose
    private Integer qualifiedUsers;
    @SerializedName("rank_id")
    @Expose
    private Integer rankId;
    @SerializedName("income_date")
    @Expose
    private String incomeDate;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("rank")
    @Expose
    private String rank;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getOnAmount() {
        return onAmount;
    }

    public void setOnAmount(Integer onAmount) {
        this.onAmount = onAmount;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getQualifiedUsers() {
        return qualifiedUsers;
    }

    public void setQualifiedUsers(Integer qualifiedUsers) {
        this.qualifiedUsers = qualifiedUsers;
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public String getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(String incomeDate) {
        this.incomeDate = incomeDate;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
