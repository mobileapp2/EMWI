package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PinRequestReportRecordModel {
    @SerializedName("product_name")
    @Expose
    private Object productName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("product_price")
    @Expose
    private Integer productPrice;
    @SerializedName("request_quantity")
    @Expose
    private Integer requestQuantity;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;
    @SerializedName("amount_deposited")
    @Expose
    private Integer amountDeposited;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("ref_no")
    @Expose
    private String refNo;
    @SerializedName("request_date")
    @Expose
    private String requestDate;
    @SerializedName("approve_quantity")
    @Expose
    private Integer approveQuantity;
    @SerializedName("approve_date")
    @Expose
    private String approveDate;
    @SerializedName("rejected_date")
    @Expose
    private Object rejectedDate;
    @SerializedName("price_after_approve")
    @Expose
    private Integer priceAfterApprove;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("attachment")
    @Expose
    private String attachment;
    @SerializedName("verify_remark")
    @Expose
    private String verifyRemark;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("deposite_type")
    @Expose
    private String depositeType;
    @SerializedName("deposite_date")
    @Expose
    private Object depositeDate;
    @SerializedName("receipt_no")
    @Expose
    private Object receiptNo;
    @SerializedName("cheque_no")
    @Expose
    private Object chequeNo;
    @SerializedName("bank_deposite_date")
    @Expose
    private Object bankDepositeDate;
    @SerializedName("bank_deposite_amount")
    @Expose
    private String bankDepositeAmount;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("new_status")
    @Expose
    private String newStatus;
    @SerializedName("verify_date")
    @Expose
    private String verifyDate;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("rejected_remark")
    @Expose
    private Object rejectedRemark;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("user_cart")
    @Expose
    private List<PinRequestReportUserCartModel> userCart = null;

    public Object getProductName() {
        return productName;
    }

    public void setProductName(Object productName) {
        this.productName = productName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(Integer requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getAmountDeposited() {
        return amountDeposited;
    }

    public void setAmountDeposited(Integer amountDeposited) {
        this.amountDeposited = amountDeposited;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public Integer getApproveQuantity() {
        return approveQuantity;
    }

    public void setApproveQuantity(Integer approveQuantity) {
        this.approveQuantity = approveQuantity;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public Object getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(Object rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public Integer getPriceAfterApprove() {
        return priceAfterApprove;
    }

    public void setPriceAfterApprove(Integer priceAfterApprove) {
        this.priceAfterApprove = priceAfterApprove;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getVerifyRemark() {
        return verifyRemark;
    }

    public void setVerifyRemark(String verifyRemark) {
        this.verifyRemark = verifyRemark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepositeType() {
        return depositeType;
    }

    public void setDepositeType(String depositeType) {
        this.depositeType = depositeType;
    }

    public Object getDepositeDate() {
        return depositeDate;
    }

    public void setDepositeDate(Object depositeDate) {
        this.depositeDate = depositeDate;
    }

    public Object getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(Object receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Object getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(Object chequeNo) {
        this.chequeNo = chequeNo;
    }

    public Object getBankDepositeDate() {
        return bankDepositeDate;
    }

    public void setBankDepositeDate(Object bankDepositeDate) {
        this.bankDepositeDate = bankDepositeDate;
    }

    public String getBankDepositeAmount() {
        return bankDepositeAmount;
    }

    public void setBankDepositeAmount(String bankDepositeAmount) {
        this.bankDepositeAmount = bankDepositeAmount;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public String getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(String verifyDate) {
        this.verifyDate = verifyDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Object getRejectedRemark() {
        return rejectedRemark;
    }

    public void setRejectedRemark(Object rejectedRemark) {
        this.rejectedRemark = rejectedRemark;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<PinRequestReportUserCartModel> getUserCart() {
        return userCart;
    }

    public void setUserCart(List<PinRequestReportUserCartModel> userCart) {
        this.userCart = userCart;
    }

}


