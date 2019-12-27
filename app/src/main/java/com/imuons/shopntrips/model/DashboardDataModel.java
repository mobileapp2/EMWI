package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardDataModel {

    @SerializedName("TotalIncome")
    @Expose
    private Double totalIncome;
    @SerializedName("TotalWithdraw")
    @Expose
    private Double totalWithdraw;
    @SerializedName("TotalProfit")
    @Expose
    private Double totalProfit;
    @SerializedName("DirectIncome")
    @Expose
    private Integer directIncome;
    @SerializedName("BinaryIncome")
    @Expose
    private Double binaryIncome;
    @SerializedName("AwardIncome")
    @Expose
    private Integer awardIncome;
    @SerializedName("RoiIncome")
    @Expose
    private Integer roiIncome;
    @SerializedName("DirectRoiIncome")
    @Expose
    private Integer directRoiIncome;
    @SerializedName("BinaryRoiIncome")
    @Expose
    private Double binaryRoiIncome;
    @SerializedName("RepurchaseIncome")
    @Expose
    private Integer repurchaseIncome;
    @SerializedName("Designation")
    @Expose
    private String designation;
    @SerializedName("TotalDirect")
    @Expose
    private Integer totalDirect;
    @SerializedName("left_bv")
    @Expose
    private Integer leftBv;
    @SerializedName("right_bv")
    @Expose
    private Integer rightBv;

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(Double totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Integer getDirectIncome() {
        return directIncome;
    }

    public void setDirectIncome(Integer directIncome) {
        this.directIncome = directIncome;
    }

    public Double getBinaryIncome() {
        return binaryIncome;
    }

    public void setBinaryIncome(Double binaryIncome) {
        this.binaryIncome = binaryIncome;
    }

    public Integer getAwardIncome() {
        return awardIncome;
    }

    public void setAwardIncome(Integer awardIncome) {
        this.awardIncome = awardIncome;
    }

    public Integer getRoiIncome() {
        return roiIncome;
    }

    public void setRoiIncome(Integer roiIncome) {
        this.roiIncome = roiIncome;
    }

    public Integer getDirectRoiIncome() {
        return directRoiIncome;
    }

    public void setDirectRoiIncome(Integer directRoiIncome) {
        this.directRoiIncome = directRoiIncome;
    }

    public Double getBinaryRoiIncome() {
        return binaryRoiIncome;
    }

    public void setBinaryRoiIncome(Double binaryRoiIncome) {
        this.binaryRoiIncome = binaryRoiIncome;
    }

    public Integer getRepurchaseIncome() {
        return repurchaseIncome;
    }

    public void setRepurchaseIncome(Integer repurchaseIncome) {
        this.repurchaseIncome = repurchaseIncome;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getTotalDirect() {
        return totalDirect;
    }

    public void setTotalDirect(Integer totalDirect) {
        this.totalDirect = totalDirect;
    }

    public Integer getLeftBv() {
        return leftBv;
    }

    public void setLeftBv(Integer leftBv) {
        this.leftBv = leftBv;
    }

    public Integer getRightBv() {
        return rightBv;
    }

    public void setRightBv(Integer rightBv) {
        this.rightBv = rightBv;
    }
}
