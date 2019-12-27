package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserPhotosDataModel {
    @SerializedName("pancard")
    @Expose
    private Object pancard;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("photo")
    @Expose
    private Object photo;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("pancard_v")
    @Expose
    private String pancardV;
    @SerializedName("address_v")
    @Expose
    private String addressV;
    @SerializedName("photo_v")
    @Expose
    private String photoV;
    @SerializedName("designation")
    @Expose
    private String designation;

    public Object getPancard() {
        return pancard;
    }

    public void setPancard(Object pancard) {
        this.pancard = pancard;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPancardV() {
        return pancardV;
    }

    public void setPancardV(String pancardV) {
        this.pancardV = pancardV;
    }

    public String getAddressV() {
        return addressV;
    }

    public void setAddressV(String addressV) {
        this.addressV = addressV;
    }

    public String getPhotoV() {
        return photoV;
    }

    public void setPhotoV(String photoV) {
        this.photoV = photoV;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
