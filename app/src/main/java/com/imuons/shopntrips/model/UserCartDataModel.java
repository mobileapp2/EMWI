package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserCartDataModel {

    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("srno")
    @Expose
    private Integer srno;
    @SerializedName("pin_request_id")
    @Expose
    private Object pinRequestId;
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
    private Integer requestQuantity;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("request_time")
    @Expose
    private Object requestTime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("pin")
    @Expose
    private Object pin;
    @SerializedName("sum_request_quantity")
    @Expose
    private String sumRequestQuantity;
    @SerializedName("sum_total_price")
    @Expose
    private String sumTotalPrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSrno() {
        return srno;
    }

    public void setSrno(Integer srno) {
        this.srno = srno;
    }

    public Object getPinRequestId() {
        return pinRequestId;
    }

    public void setPinRequestId(Object pinRequestId) {
        this.pinRequestId = pinRequestId;
    }

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

    public Integer getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(Integer requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public Object getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Object requestTime) {
        this.requestTime = requestTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getPin() {
        return pin;
    }

    public void setPin(Object pin) {
        this.pin = pin;
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
