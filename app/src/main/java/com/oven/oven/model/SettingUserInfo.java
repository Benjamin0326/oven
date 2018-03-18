package com.oven.oven.model;

import java.util.List;

/**
 * Created by sung9 on 2018-01-21.
 */

public class SettingUserInfo {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<UserInfo> getUser_info() {
        return user_info;
    }

    public void setUser_info(List<UserInfo> user_info) {
        this.user_info = user_info;
    }

    private List<UserInfo> user_info;
}
