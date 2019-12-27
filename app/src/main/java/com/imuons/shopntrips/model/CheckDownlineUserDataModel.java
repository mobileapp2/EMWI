package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckDownlineUserDataModel {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("total_investment")
    @Expose
    private Object totalInvestment;
    @SerializedName("product")
    @Expose
    private CheckDownlineUserProductModel product;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Object getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(Object totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public CheckDownlineUserProductModel getProduct() {
        return product;
    }

    public void setProduct(CheckDownlineUserProductModel product) {
        this.product = product;
    }

}
