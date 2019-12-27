package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfileDataModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("ref_user_id")
    @Expose
    private Integer refUserId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("pin_number")
    @Expose
    private String pinNumber;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("l_c_count")
    @Expose
    private Integer lCCount;
    @SerializedName("r_c_count")
    @Expose
    private Integer rCCount;
    @SerializedName("l_bv")
    @Expose
    private Integer lBv;
    @SerializedName("r_bv")
    @Expose
    private Integer rBv;
    @SerializedName("l_bv_rep")
    @Expose
    private Integer lBvRep;
    @SerializedName("r_bv_rep")
    @Expose
    private Integer rBvRep;
    @SerializedName("virtual_parent_id")
    @Expose
    private Integer virtualParentId;
    @SerializedName("account_no")
    @Expose
    private String accountNo;
    @SerializedName("holder_name")
    @Expose
    private String holderName;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("pan_no")
    @Expose
    private String panNo;
    @SerializedName("ifsc_code")
    @Expose
    private String ifscCode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("pincode")
    @Expose
    private Object pincode;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("about_us")
    @Expose
    private Object aboutUs;
    @SerializedName("nominee_name")
    @Expose
    private String nomineeName;
    @SerializedName("relation")
    @Expose
    private String relation;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
    @SerializedName("sponserId")
    @Expose
    private String sponserId;
    @SerializedName("carry_left_bv")
    @Expose
    private Integer carryLeftBv;
    @SerializedName("carry_right_bv")
    @Expose
    private Integer carryRightBv;
    @SerializedName("carry_left_bv_rep")
    @Expose
    private Integer carryLeftBvRep;
    @SerializedName("carry_right_bv_rep")
    @Expose
    private Integer carryRightBvRep;
    @SerializedName("blood_group")
    @Expose
    private String bloodGroup;
    @SerializedName("designation")
    @Expose
    private Object designation;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("btc_address")
    @Expose
    private Object btcAddress;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;

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

    public Integer getRefUserId() {
        return refUserId;
    }

    public void setRefUserId(Integer refUserId) {
        this.refUserId = refUserId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getLCCount() {
        return lCCount;
    }

    public void setLCCount(Integer lCCount) {
        this.lCCount = lCCount;
    }

    public Integer getRCCount() {
        return rCCount;
    }

    public void setRCCount(Integer rCCount) {
        this.rCCount = rCCount;
    }

    public Integer getLBv() {
        return lBv;
    }

    public void setLBv(Integer lBv) {
        this.lBv = lBv;
    }

    public Integer getRBv() {
        return rBv;
    }

    public void setRBv(Integer rBv) {
        this.rBv = rBv;
    }

    public Integer getLBvRep() {
        return lBvRep;
    }

    public void setLBvRep(Integer lBvRep) {
        this.lBvRep = lBvRep;
    }

    public Integer getRBvRep() {
        return rBvRep;
    }

    public void setRBvRep(Integer rBvRep) {
        this.rBvRep = rBvRep;
    }

    public Integer getVirtualParentId() {
        return virtualParentId;
    }

    public void setVirtualParentId(Integer virtualParentId) {
        this.virtualParentId = virtualParentId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getPincode() {
        return pincode;
    }

    public void setPincode(Object pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(Object aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSponserId() {
        return sponserId;
    }

    public void setSponserId(String sponserId) {
        this.sponserId = sponserId;
    }

    public Integer getCarryLeftBv() {
        return carryLeftBv;
    }

    public void setCarryLeftBv(Integer carryLeftBv) {
        this.carryLeftBv = carryLeftBv;
    }

    public Integer getCarryRightBv() {
        return carryRightBv;
    }

    public void setCarryRightBv(Integer carryRightBv) {
        this.carryRightBv = carryRightBv;
    }

    public Integer getCarryLeftBvRep() {
        return carryLeftBvRep;
    }

    public void setCarryLeftBvRep(Integer carryLeftBvRep) {
        this.carryLeftBvRep = carryLeftBvRep;
    }

    public Integer getCarryRightBvRep() {
        return carryRightBvRep;
    }

    public void setCarryRightBvRep(Integer carryRightBvRep) {
        this.carryRightBvRep = carryRightBvRep;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Object getDesignation() {
        return designation;
    }

    public void setDesignation(Object designation) {
        this.designation = designation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getBtcAddress() {
        return btcAddress;
    }

    public void setBtcAddress(Object btcAddress) {
        this.btcAddress = btcAddress;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }
}
