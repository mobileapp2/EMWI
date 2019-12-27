package com.imuons.shopntrips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TreeViewTreeDatumModel {
    @SerializedName("level")
    @Expose
    private List<TreeViewLevelModel> level = null;

    public List<TreeViewLevelModel> getLevel() {
        return level;
    }

    public void setLevel(List<TreeViewLevelModel> level) {
        this.level = level;
    }

}
