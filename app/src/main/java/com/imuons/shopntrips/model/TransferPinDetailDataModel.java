package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransferPinDetailDataModel {
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pins")
    @Expose
    private List<Object> pins = null;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getPins() {
        return pins;
    }

    public void setPins(List<Object> pins) {
        this.pins = pins;
    }

}
