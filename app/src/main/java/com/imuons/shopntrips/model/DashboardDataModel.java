package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardDataModel {
    @SerializedName("TotalIncome")
    @Expose
    private Integer totalIncome;
    @SerializedName("TotalWithdraw")
    @Expose
    private Integer totalWithdraw;
    @SerializedName("TotalProfit")
    @Expose
    private Integer totalProfit;
    @SerializedName("DirectIncome")
    @Expose
    private Integer directIncome;
    @SerializedName("BinaryIncome")
    @Expose
    private Integer binaryIncome;
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
    private Integer binaryRoiIncome;
    @SerializedName("RepurchaseIncome")
    @Expose
    private Integer repurchaseIncome;
    @SerializedName("RoyalityIncome")
    @Expose
    private Integer royalityIncome;
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
    @SerializedName("left_bv_rep")
    @Expose
    private Integer leftBvRep;
    @SerializedName("right_bv_rep")
    @Expose
    private Integer rightBvRep;

    public Integer getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Integer totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Integer getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(Integer totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }

    public Integer getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Integer totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Integer getDirectIncome() {
        return directIncome;
    }

    public void setDirectIncome(Integer directIncome) {
        this.directIncome = directIncome;
    }

    public Integer getBinaryIncome() {
        return binaryIncome;
    }

    public void setBinaryIncome(Integer binaryIncome) {
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

    public Integer getBinaryRoiIncome() {
        return binaryRoiIncome;
    }

    public void setBinaryRoiIncome(Integer binaryRoiIncome) {
        this.binaryRoiIncome = binaryRoiIncome;
    }

    public Integer getRepurchaseIncome() {
        return repurchaseIncome;
    }

    public void setRepurchaseIncome(Integer repurchaseIncome) {
        this.repurchaseIncome = repurchaseIncome;
    }

    public Integer getRoyalityIncome() {
        return royalityIncome;
    }

    public void setRoyalityIncome(Integer royalityIncome) {
        this.royalityIncome = royalityIncome;
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

    public Integer getLeftBvRep() {
        return leftBvRep;
    }

    public void setLeftBvRep(Integer leftBvRep) {
        this.leftBvRep = leftBvRep;
    }

    public Integer getRightBvRep() {
        return rightBvRep;
    }

    public void setRightBvRep(Integer rightBvRep) {
        this.rightBvRep = rightBvRep;
    }

}
