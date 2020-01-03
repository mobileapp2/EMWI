package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LevelViewRecordModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("down_id")
    @Expose
    private Integer downId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("down_user_id")
    @Expose
    private String downUserId;
    @SerializedName("sponser_id")
    @Expose
    private Integer sponserId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("coin")
    @Expose
    private Integer coin;
    @SerializedName("btc")
    @Expose
    private Integer btc;
    @SerializedName("total_investment")
    @Expose
    private Integer totalInvestment;
    @SerializedName("total_withdraw")
    @Expose
    private Integer totalWithdraw;
    @SerializedName("total_profit")
    @Expose
    private Integer totalProfit;
    @SerializedName("level")
    @Expose
    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDownId() {
        return downId;
    }

    public void setDownId(Integer downId) {
        this.downId = downId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDownUserId() {
        return downUserId;
    }

    public void setDownUserId(String downUserId) {
        this.downUserId = downUserId;
    }

    public Integer getSponserId() {
        return sponserId;
    }

    public void setSponserId(Integer sponserId) {
        this.sponserId = sponserId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getBtc() {
        return btc;
    }

    public void setBtc(Integer btc) {
        this.btc = btc;
    }

    public Integer getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(Integer totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public Integer getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(Integer totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }

    public Integer getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Integer totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
