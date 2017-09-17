package com.oven.oven.model;

import java.util.List;

/**
 * Created by sung9 on 2017-09-17.
 */

public class ProductFavorite {
    private int code, uid, pid, toggle;
    private String msg;

    public void setCode(int _code){
        code = _code;
    }
    public void setUid(int _uid){
        uid = _uid;
    }
    public void setPid(int _pid){
        pid = _pid;
    }
    public void setToggle(int _toggle){
        toggle = _toggle;
    }
    public void setMsg(String _msg){
        msg = _msg;
    }

    public int getCode(){
        return code;
    }
    public int getUid(){
        return uid;
    }
    public int getPid(){
        return pid;
    }
    public int getToggle(){
        return toggle;
    }
    public String getMsg(){
        return msg;
    }

}


