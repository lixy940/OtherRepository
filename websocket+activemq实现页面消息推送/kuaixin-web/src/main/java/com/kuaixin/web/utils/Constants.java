package com.kuaixin.web.utils;

/**
 * Created by zhm on 2015/7/14.
 */
public enum Constants {
    SESSION_USERNAME("USERNAME"),
    SESSION_USERID("USERID");
    private String name;
    Constants(String name) {
        this.name=name;
    }
    public String value(){
        return this.name;
    }
}
