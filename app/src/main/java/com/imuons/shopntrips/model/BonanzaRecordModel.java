package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BonanzaRecordModel {
    @SerializedName("winner_id")
    @Expose
    private Integer winnerId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("award_id")
    @Expose
    private Integer awardId;
    @SerializedName("left_count")
    @Expose
    private Integer leftCount;
    @SerializedName("right_count")
    @Expose
    private Integer rightCount;
    @SerializedName("directs")
    @Expose
    private Integer directs;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("award")
    @Expose
    private String award;

    public Integer getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Integer winnerId) {
        this.winnerId = winnerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public Integer getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(Integer leftCount) {
        this.leftCount = leftCount;
    }

    public Integer getRightCount() {
        return rightCount;
    }

    public void setRightCount(Integer rightCount) {
        this.rightCount = rightCount;
    }

    public Integer getDirects() {
        return directs;
    }

    public void setDirects(Integer directs) {
        this.directs = directs;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

}
