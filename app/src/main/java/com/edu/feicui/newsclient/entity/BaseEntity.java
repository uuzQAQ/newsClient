package com.edu.feicui.newsclient.entity;

/**
 * Created by user on 2016/11/28.
 */

public class BaseEntity<T> {
    private String status;
    private String message;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseEntity(String message, T data, String status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public BaseEntity() {
    }
}
