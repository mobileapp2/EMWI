package de.blox.treeview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PositionDataModel implements Serializable {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("user_id")
    @Expose
    public String user_id;
    @SerializedName("fullname")
    @Expose
    public String fullname;
    @SerializedName("virtual_parent_id")
    @Expose
    public String virtual_parent_id;
    @SerializedName("sponser_id")
    @Expose
    public String sponser_id;
    @SerializedName("sponser_name")
    @Expose
    public String sponser_name;
    @SerializedName("sponser_user_id")
    @Expose
    public String sponser_user_id;
    @SerializedName("left_id")
    @Expose
    public String left_id;
    @SerializedName("right_id")
    @Expose
    public String right_id;
    @SerializedName("l_bv")
    @Expose
    public String left_bv;
    @SerializedName("r_bv")
    @Expose
    public String right_bv;
    @SerializedName("left_bv")
    @Expose
    public String binary_left_bv;
    @SerializedName("right_bv")
    @Expose
    public String binary_right_bv;
    @SerializedName("position")
    @Expose
    public String position;
    @SerializedName("binary_left_bv1")
    @Expose
    public String binary_left_bv1;
    @SerializedName("total_investment")
    @Expose
    public String total_investment;
    @SerializedName("lending_binary_right_bv")
    @Expose
    public String lending_binary_right_bv;
    @SerializedName("lending_binary_left_bv")
    @Expose
    public String lending_binary_left_bv;
    @SerializedName("carry_left_bv")
    @Expose
    public String carry_left_bv;
    @SerializedName("carry_right_bv")
    @Expose
    public String carry_right_bv;
    @SerializedName("cost")
    @Expose
    public String status;
    @SerializedName("l_bv_rep")
    @Expose
    public String l_bv_rep;
    @SerializedName("r_bv_rep")
    @Expose
    public String r_bv_rep;
    @SerializedName("entry_time")
    @Expose
    public String dateOfjoining;
    @SerializedName("image")
    @Expose
    public String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getVirtual_parent_id() {
        return virtual_parent_id;
    }

    public void setVirtual_parent_id(String virtual_parent_id) {
        this.virtual_parent_id = virtual_parent_id;
    }

    public String getSponser_id() {
        return sponser_id;
    }

    public void setSponser_id(String sponser_id) {
        this.sponser_id = sponser_id;
    }

    public String getSponser_name() {
        return sponser_name;
    }

    public void setSponser_name(String sponser_name) {
        this.sponser_name = sponser_name;
    }

    public String getSponser_user_id() {
        return sponser_user_id;
    }

    public void setSponser_user_id(String sponser_user_id) {
        this.sponser_user_id = sponser_user_id;
    }

    public String getLeft_id() {
        return left_id;
    }

    public void setLeft_id(String left_id) {
        this.left_id = left_id;
    }

    public String getRight_id() {
        return right_id;
    }

    public void setRight_id(String right_id) {
        this.right_id = right_id;
    }

    public String getLeft_bv() {
        return left_bv;
    }

    public void setLeft_bv(String left_bv) {
        this.left_bv = left_bv;
    }

    public String getRight_bv() {
        return right_bv;
    }

    public void setRight_bv(String right_bv) {
        this.right_bv = right_bv;
    }

    public String getBinary_left_bv() {
        return binary_left_bv;
    }

    public void setBinary_left_bv(String binary_left_bv) {
        this.binary_left_bv = binary_left_bv;
    }

    public String getBinary_right_bv() {
        return binary_right_bv;
    }

    public void setBinary_right_bv(String binary_right_bv) {
        this.binary_right_bv = binary_right_bv;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBinary_left_bv1() {
        return binary_left_bv1;
    }

    public void setBinary_left_bv1(String binary_left_bv1) {
        this.binary_left_bv1 = binary_left_bv1;
    }

    public String getTotal_investment() {
        return total_investment;
    }

    public void setTotal_investment(String total_investment) {
        this.total_investment = total_investment;
    }

    public String getLending_binary_right_bv() {
        return lending_binary_right_bv;
    }

    public void setLending_binary_right_bv(String lending_binary_right_bv) {
        this.lending_binary_right_bv = lending_binary_right_bv;
    }

    public String getLending_binary_left_bv() {
        return lending_binary_left_bv;
    }

    public void setLending_binary_left_bv(String lending_binary_left_bv) {
        this.lending_binary_left_bv = lending_binary_left_bv;
    }

    public String getCarry_left_bv() {
        return carry_left_bv;
    }

    public void setCarry_left_bv(String carry_left_bv) {
        this.carry_left_bv = carry_left_bv;
    }

    public String getCarry_right_bv() {
        return carry_right_bv;
    }

    public void setCarry_right_bv(String carry_right_bv) {
        this.carry_right_bv = carry_right_bv;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getL_bv_rep() {
        return l_bv_rep;
    }

    public void setL_bv_rep(String l_bv_rep) {
        this.l_bv_rep = l_bv_rep;
    }

    public String getR_bv_rep() {
        return r_bv_rep;
    }

    public void setR_bv_rep(String r_bv_rep) {
        this.r_bv_rep = r_bv_rep;
    }

    public String getDateOfjoining() {
        return dateOfjoining;
    }

    public void setDateOfjoining(String dateOfjoining) {
        this.dateOfjoining = dateOfjoining;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
