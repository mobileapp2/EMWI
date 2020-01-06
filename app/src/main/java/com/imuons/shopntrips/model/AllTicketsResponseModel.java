
package com.imuons.shopntrips.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.imuons.shopntrips.views.SuportActivity;

public class AllTicketsResponseModel {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<AllTicketsDataModel> data = null;

    public AllTicketsResponseModel(SuportActivity suportActivity, List<AllTicketsDataModel> teList) {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AllTicketsDataModel> getData() {
        return data;
    }

    public void setData(List<AllTicketsDataModel> data) {
        this.data = data;
    }

}
