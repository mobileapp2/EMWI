package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActiveTeamViewDataModel {

    @SerializedName("recordsTotal")
    @Expose
    private Integer recordsTotal;
    @SerializedName("recordsFiltered")
    @Expose
    private Integer recordsFiltered;
    @SerializedName("user_binary")
    @Expose
    private ActiveTeamViewUserBinaryModel userBinary;
    @SerializedName("records")
    @Expose
    private List<ActiveTeamViewRecordModel> records = null;

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

    public ActiveTeamViewUserBinaryModel getUserBinary() {
        return userBinary;
    }

    public void setUserBinary(ActiveTeamViewUserBinaryModel userBinary) {
        this.userBinary = userBinary;
    }

    public List<ActiveTeamViewRecordModel> getRecords() {
        return records;
    }

    public void setRecords(List<ActiveTeamViewRecordModel> records) {
        this.records = records;
    }
}
