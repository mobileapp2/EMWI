package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginDataModel implements Serializable {

    @SerializedName("otpmode")
    @Expose
    public String otpmode;
    @SerializedName("mobileverification")
    @Expose
    public String mobileverification;
    @SerializedName("mailotp")
    @Expose
    public String mailotp;
    @SerializedName("google2faauth")
    @Expose
    public String google2faauth;
    @SerializedName("mailverification")
    @Expose
    public String mailverification;
    @SerializedName("access_token")
    @Expose
    public String access_token;


    public String getOtpmode() {
        return otpmode;
    }

    public void setOtpmode(String otpmode) {
        this.otpmode = otpmode;
    }

    public String getMobileverification() {
        return mobileverification;
    }

    public void setMobileverification(String mobileverification) {
        this.mobileverification = mobileverification;
    }

    public String getMailotp() {
        return mailotp;
    }

    public void setMailotp(String mailotp) {
        this.mailotp = mailotp;
    }

    public String getGoogle2faauth() {
        return google2faauth;
    }

    public void setGoogle2faauth(String google2faauth) {
        this.google2faauth = google2faauth;
    }

    public String getMailverification() {
        return mailverification;
    }

    public void setMailverification(String mailverification) {
        this.mailverification = mailverification;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
