package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class  TreeViewDataModel {
    @SerializedName("user")
    @Expose
    private TreeViewUserModel user;
    @SerializedName("tree_data")
    @Expose
    private List<TreeViewTreeDatumModel> treeData = null;
    @SerializedName("settings")
    @Expose
    private TreeViewSettings settings;

    public TreeViewUserModel getUser() {
        return user;
    }

    public void setUser(TreeViewUserModel user) {
        this.user = user;
    }

    public List<TreeViewTreeDatumModel> getTreeData() {
        return treeData;
    }

    public void setTreeData(List<TreeViewTreeDatumModel> treeData) {
        this.treeData = treeData;
    }

    public TreeViewSettings getSettings() {
        return settings;
    }

    public void setSettings(TreeViewSettings settings) {
        this.settings = settings;
    }


}
