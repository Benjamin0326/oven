package com.oven.oven.model;

/**
 * Created by sung9 on 2017-08-12.
 */

public class LoginRes {
    private int code, uid;
    private String msg;

    public void setCode(int _code){
        code = _code;
    }
    public void setMsg(String _msg){
        msg = _msg;
    }
    public void setUid(int _uid){
        uid = _uid;
    }

    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
    public int getUid(){
        return uid;
    }
}
