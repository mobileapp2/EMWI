package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TreeViewSettings {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("project_name")
    @Expose
    private String projectName;
    @SerializedName("coin_name")
    @Expose
    private String coinName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("domain_name")
    @Expose
    private String domainName;
    @SerializedName("network_type")
    @Expose
    private String networkType;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("exchange_btc_transaction_fees")
    @Expose
    private Double exchangeBtcTransactionFees;
    @SerializedName("exchange_coin_transaction_fees")
    @Expose
    private Double exchangeCoinTransactionFees;
    @SerializedName("withdraw_min_usd")
    @Expose
    private Integer withdrawMinUsd;
    @SerializedName("withdraw_btc_transaction_fee")
    @Expose
    private Double withdrawBtcTransactionFee;
    @SerializedName("withdraw_coin_transaction_fee")
    @Expose
    private Double withdrawCoinTransactionFee;
    @SerializedName("max_supply")
    @Expose
    private Integer maxSupply;
    @SerializedName("no_of_confirmation")
    @Expose
    private Integer noOfConfirmation;
    @SerializedName("registation_plan")
    @Expose
    private String registationPlan;
    @SerializedName("level_plan")
    @Expose
    private String levelPlan;
    @SerializedName("binary_plan")
    @Expose
    private String binaryPlan;
    @SerializedName("direct_plan")
    @Expose
    private String directPlan;
    @SerializedName("otp_status")
    @Expose
    private String otpStatus;
    @SerializedName("sendmail")
    @Expose
    private Integer sendmail;
    @SerializedName("per_pair")
    @Expose
    private String perPair;
    @SerializedName("per_pair_amount")
    @Expose
    private Integer perPairAmount;
    @SerializedName("repurchase_per_pair_amount")
    @Expose
    private Integer repurchasePerPairAmount;
    @SerializedName("tds")
    @Expose
    private Integer tds;
    @SerializedName("tds_invalidpancard")
    @Expose
    private Integer tdsInvalidpancard;
    @SerializedName("admin_charges")
    @Expose
    private Integer adminCharges;
    @SerializedName("admin_panel_status")
    @Expose
    private String adminPanelStatus;
    @SerializedName("matrix_value")
    @Expose
    private String matrixValue;
    @SerializedName("level_show")
    @Expose
    private Integer levelShow;
    @SerializedName("bv_pass_cron_status")
    @Expose
    private String bvPassCronStatus;
    @SerializedName("mp_per_bv")
    @Expose
    private String mpPerBv;
    @SerializedName("multiple_of_amount")
    @Expose
    private Integer multipleOfAmount;
    @SerializedName("loan_deduction")
    @Expose
    private Integer loanDeduction;
    @SerializedName("bv_remove_cron_status")
    @Expose
    private String bvRemoveCronStatus;
    @SerializedName("with_from_date")
    @Expose
    private String withFromDate;
    @SerializedName("with_to_date")
    @Expose
    private String withToDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getExchangeBtcTransactionFees() {
        return exchangeBtcTransactionFees;
    }

    public void setExchangeBtcTransactionFees(Double exchangeBtcTransactionFees) {
        this.exchangeBtcTransactionFees = exchangeBtcTransactionFees;
    }

    public Double getExchangeCoinTransactionFees() {
        return exchangeCoinTransactionFees;
    }

    public void setExchangeCoinTransactionFees(Double exchangeCoinTransactionFees) {
        this.exchangeCoinTransactionFees = exchangeCoinTransactionFees;
    }

    public Integer getWithdrawMinUsd() {
        return withdrawMinUsd;
    }

    public void setWithdrawMinUsd(Integer withdrawMinUsd) {
        this.withdrawMinUsd = withdrawMinUsd;
    }

    public Double getWithdrawBtcTransactionFee() {
        return withdrawBtcTransactionFee;
    }

    public void setWithdrawBtcTransactionFee(Double withdrawBtcTransactionFee) {
        this.withdrawBtcTransactionFee = withdrawBtcTransactionFee;
    }

    public Double getWithdrawCoinTransactionFee() {
        return withdrawCoinTransactionFee;
    }

    public void setWithdrawCoinTransactionFee(Double withdrawCoinTransactionFee) {
        this.withdrawCoinTransactionFee = withdrawCoinTransactionFee;
    }

    public Integer getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(Integer maxSupply) {
        this.maxSupply = maxSupply;
    }

    public Integer getNoOfConfirmation() {
        return noOfConfirmation;
    }

    public void setNoOfConfirmation(Integer noOfConfirmation) {
        this.noOfConfirmation = noOfConfirmation;
    }

    public String getRegistationPlan() {
        return registationPlan;
    }

    public void setRegistationPlan(String registationPlan) {
        this.registationPlan = registationPlan;
    }

    public String getLevelPlan() {
        return levelPlan;
    }

    public void setLevelPlan(String levelPlan) {
        this.levelPlan = levelPlan;
    }

    public String getBinaryPlan() {
        return binaryPlan;
    }

    public void setBinaryPlan(String binaryPlan) {
        this.binaryPlan = binaryPlan;
    }

    public String getDirectPlan() {
        return directPlan;
    }

    public void setDirectPlan(String directPlan) {
        this.directPlan = directPlan;
    }

    public String getOtpStatus() {
        return otpStatus;
    }

    public void setOtpStatus(String otpStatus) {
        this.otpStatus = otpStatus;
    }

    public Integer getSendmail() {
        return sendmail;
    }

    public void setSendmail(Integer sendmail) {
        this.sendmail = sendmail;
    }

    public String getPerPair() {
        return perPair;
    }

    public void setPerPair(String perPair) {
        this.perPair = perPair;
    }

    public Integer getPerPairAmount() {
        return perPairAmount;
    }

    public void setPerPairAmount(Integer perPairAmount) {
        this.perPairAmount = perPairAmount;
    }

    public Integer getRepurchasePerPairAmount() {
        return repurchasePerPairAmount;
    }

    public void setRepurchasePerPairAmount(Integer repurchasePerPairAmount) {
        this.repurchasePerPairAmount = repurchasePerPairAmount;
    }

    public Integer getTds() {
        return tds;
    }

    public void setTds(Integer tds) {
        this.tds = tds;
    }

    public Integer getTdsInvalidpancard() {
        return tdsInvalidpancard;
    }

    public void setTdsInvalidpancard(Integer tdsInvalidpancard) {
        this.tdsInvalidpancard = tdsInvalidpancard;
    }

    public Integer getAdminCharges() {
        return adminCharges;
    }

    public void setAdminCharges(Integer adminCharges) {
        this.adminCharges = adminCharges;
    }

    public String getAdminPanelStatus() {
        return adminPanelStatus;
    }

    public void setAdminPanelStatus(String adminPanelStatus) {
        this.adminPanelStatus = adminPanelStatus;
    }

    public String getMatrixValue() {
        return matrixValue;
    }

    public void setMatrixValue(String matrixValue) {
        this.matrixValue = matrixValue;
    }

    public Integer getLevelShow() {
        return levelShow;
    }

    public void setLevelShow(Integer levelShow) {
        this.levelShow = levelShow;
    }

    public String getBvPassCronStatus() {
        return bvPassCronStatus;
    }

    public void setBvPassCronStatus(String bvPassCronStatus) {
        this.bvPassCronStatus = bvPassCronStatus;
    }

    public String getMpPerBv() {
        return mpPerBv;
    }

    public void setMpPerBv(String mpPerBv) {
        this.mpPerBv = mpPerBv;
    }

    public Integer getMultipleOfAmount() {
        return multipleOfAmount;
    }

    public void setMultipleOfAmount(Integer multipleOfAmount) {
        this.multipleOfAmount = multipleOfAmount;
    }

    public Integer getLoanDeduction() {
        return loanDeduction;
    }

    public void setLoanDeduction(Integer loanDeduction) {
        this.loanDeduction = loanDeduction;
    }

    public String getBvRemoveCronStatus() {
        return bvRemoveCronStatus;
    }

    public void setBvRemoveCronStatus(String bvRemoveCronStatus) {
        this.bvRemoveCronStatus = bvRemoveCronStatus;
    }

    public String getWithFromDate() {
        return withFromDate;
    }

    public void setWithFromDate(String withFromDate) {
        this.withFromDate = withFromDate;
    }

    public String getWithToDate() {
        return withToDate;
    }

    public void setWithToDate(String withToDate) {
        this.withToDate = withToDate;
    }

}
