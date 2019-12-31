package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoyalityQualifiedReportRecordModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("rank_id")
    @Expose
    private Integer rankId;
    @SerializedName("self_bv")
    @Expose
    private Integer selfBv;
    @SerializedName("rep_match_bv")
    @Expose
    private Integer repMatchBv;
    @SerializedName("status")
    @Expose
    private String status;
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

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public Integer getSelfBv() {
        return selfBv;
    }

    public void setSelfBv(Integer selfBv) {
        this.selfBv = selfBv;
    }

    public Integer getRepMatchBv() {
        return repMatchBv;
    }

    public void setRepMatchBv(Integer repMatchBv) {
        this.repMatchBv = repMatchBv;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
