package com.edu.feicui.newsclient.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by user on 2016/12/2.
 */
@Entity(nameInDb = "lovenews")
public class Newsgreen extends News{
    @Id(autoincrement = true)
    private long _id;
    private int type;
    private int nid;
    private String stamp;
    private String icon;
    private String title;
    private String summary;
    private String link;

    @Generated(hash = 898316226)
    public Newsgreen(long _id, int type, int nid, String stamp, String icon,
            String title, String summary, String link) {
        this._id = _id;
        this.type = type;
        this.nid = nid;
        this.stamp = stamp;
        this.icon = icon;
        this.title = title;
        this.summary = summary;
        this.link = link;
    }

    @Generated(hash = 2110345302)
    public Newsgreen() {
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getNid() {
        return nid;
    }

    @Override
    public void setNid(int nid) {
        this.nid = nid;
    }

    @Override
    public String getStamp() {
        return stamp;
    }

    @Override
    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getSummary() {
        return summary;
    }

    @Override
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public void setLink(String link) {
        this.link = link;
    }
}
