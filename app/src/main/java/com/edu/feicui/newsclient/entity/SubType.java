package com.edu.feicui.newsclient.entity;

/**
 * Created by user on 2016/11/28.
 */

public class SubType {

    private int subid;
    private String subgroup;

    public SubType() {
    }

    public SubType(int subid, String subgroup) {

        this.subid = subid;
        this.subgroup = subgroup;
    }

    public int getSubid() {
        return subid;
    }

    public void setSubid(int subid) {
        this.subid = subid;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }
}
