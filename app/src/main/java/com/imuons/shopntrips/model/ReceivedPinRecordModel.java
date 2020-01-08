package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReceivedPinRecordModel {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("noofpin")
    @Expose
    private Integer noofpin;
    @SerializedName("from_user_id")
    @Expose
    private Integer fromUserId;
    @SerializedName("history_id")
    @Expose
    private Integer historyId;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("pins")
    @Expose
    private List<Object> pins = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getNoofpin() {
        return noofpin;
    }

    public void setNoofpin(Integer noofpin) {
        this.noofpin = noofpin;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public List<Object> getPins() {
        return pins;
    }

    public void setPins(List<Object> pins) {
        this.pins = pins;
    }

}
