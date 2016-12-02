package com.edu.feicui.newsclient.entity;

import java.util.List;

/**
 * Created by user on 2016/11/28.
 */

public class NewsType {
    private int gid;//分类编码
    private String group;//分类名
    private List<SubType> subgrp;//子分类

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<SubType> getSubgrp() {
        return subgrp;
    }

    public void setSubgrp(List<SubType> subgrp) {
        this.subgrp = subgrp;
    }

    public NewsType(int gid, String group, List<SubType> subgrp) {
        this.gid = gid;
        this.group = group;
        this.subgrp = subgrp;
    }

    public NewsType() {
    }
}
