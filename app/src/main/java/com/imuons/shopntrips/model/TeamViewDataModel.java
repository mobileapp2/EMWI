package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamViewDataModel {
    @SerializedName("recordsTotal")
    @Expose
    private Integer recordsTotal;
    @SerializedName("recordsFiltered")
    @Expose
    private Integer recordsFiltered;
    @SerializedName("user_binary")
    @Expose
    private TeamViewUserBinaryModel userBinary;
    @SerializedName("records")
    @Expose
    private List<TeamViewRecordModel> records = null;

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public TeamViewUserBinaryModel getUserBinary() {
        return userBinary;
    }

    public void setUserBinary(TeamViewUserBinaryModel userBinary) {
        this.userBinary = userBinary;
    }

    public List<TeamViewRecordModel> getRecords() {
        return records;
    }

    public void setRecords(List<TeamViewRecordModel> records) {
        this.records = records;
    }



}
