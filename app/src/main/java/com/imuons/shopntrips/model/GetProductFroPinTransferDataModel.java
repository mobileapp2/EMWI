package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProductFroPinTransferDataModel {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sns")
    @Expose
    private Integer sns;
    @SerializedName("joining_count")
    @Expose
    private String joiningCount;
    @SerializedName("no_of_pins_available")
    @Expose
    private Integer noOfPinsAvailable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSns() {
        return sns;
    }

    public void setSns(Integer sns) {
        this.sns = sns;
    }

    public String getJoiningCount() {
        return joiningCount;
    }

    public void setJoiningCount(String joiningCount) {
        this.joiningCount = joiningCount;
    }

    public Integer getNoOfPinsAvailable() {
        return noOfPinsAvailable;
    }

    public void setNoOfPinsAvailable(Integer noOfPinsAvailable) {
        this.noOfPinsAvailable = noOfPinsAvailable;
    }

}
