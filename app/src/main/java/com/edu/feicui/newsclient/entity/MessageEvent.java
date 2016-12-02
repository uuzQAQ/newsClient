package com.edu.feicui.newsclient.entity;

/**
 * Created by user on 2016/12/2.
 */

public class MessageEvent {
    public static final int TYPE_LOGIN_FRAGMENT = 1;
    public static final int TYPE_REGISTER_FRAGMENT = 2;
    public static final int TYPE_FORGOT_FRAGMENT = 3;
    public static final int TYPE_MAIN_FRAGMENT = 4;
    private int type;
    private String fragmentFullName;

    public MessageEvent() {
    }

    public MessageEvent(int type, String fragmentFullName) {
        this.type = type;
        this.fragmentFullName = fragmentFullName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFragmentFullName() {
        return fragmentFullName;
    }

    public void setFragmentFullName(String fragmentFullName) {
        this.fragmentFullName = fragmentFullName;
    }
}
