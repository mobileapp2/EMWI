package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBalanceDataModel {
    @SerializedName("topup")
    @Expose
    private Integer topup;

    public Integer getTopup() {
        return topup;
    }

    public void setTopup(Integer topup) {
        this.topup = topup;
    }

}
