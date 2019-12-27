package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPDataModel {

    @SerializedName("remember_token")
    @Expose
    private String rememberToken;
    @SerializedName("mailverification")
    @Expose
    private String mailverification;
    @SerializedName("google2faauth")
    @Expose
    private String google2faauth;
    @SerializedName("mailotp")
    @Expose
    private String mailotp;
    @SerializedName("mobileverification")
    @Expose
    private String mobileverification;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("otpmode")
    @Expose
    private String otpmode;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public String getMailverification() {
        return mailverification;
    }

    public void setMailverification(String mailverification) {
        this.mailverification = mailverification;
    }

    public String getGoogle2faauth() {
        return google2faauth;
    }

    public void setGoogle2faauth(String google2faauth) {
        this.google2faauth = google2faauth;
    }

    public String getMailotp() {
        return mailotp;
    }

    public void setMailotp(String mailotp) {
        this.mailotp = mailotp;
    }

    public String getMobileverification() {
        return mobileverification;
    }

    public void setMobileverification(String mobileverification) {
        this.mobileverification = mobileverification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOtpmode() {
        return otpmode;
    }

    public void setOtpmode(String otpmode) {
        this.otpmode = otpmode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
