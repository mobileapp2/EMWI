package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddToCartDataModel {
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("request_quantity")
    @Expose
    private String requestQuantity;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;
    @SerializedName("entry_time")
    @Expose
    private AddToCartEntryTimeModel entryTime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sum_request_quantity")
    @Expose
    private String sumRequestQuantity;
    @SerializedName("sum_total_price")
    @Expose
    private String sumTotalPrice;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(String requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public AddToCartEntryTimeModel getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(AddToCartEntryTimeModel entryTime) {
        this.entryTime = entryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSumRequestQuantity() {
        return sumRequestQuantity;
    }

    public void setSumRequestQuantity(String sumRequestQuantity) {
        this.sumRequestQuantity = sumRequestQuantity;
    }

    public String getSumTotalPrice() {
        return sumTotalPrice;
    }

    public void setSumTotalPrice(String sumTotalPrice) {
        this.sumTotalPrice = sumTotalPrice;
    }

}
