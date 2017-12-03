package com.oven.oven.model;

/**
 * Created by sung9 on 2017-11-21.
 */

public class SideUserInfo {
    private String msg, uname, email;
    private int code, delivery_cnt, favorite_cnt, view_cnt;
    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
    public String getUname(){
        return uname;
    }
    public String getEmail(){
        return email;
    }
    public int getDelivery_cnt(){
        return delivery_cnt;
    }
    public int getFavorite_cnt(){
        return favorite_cnt;
    }
    public int getView_cnt(){
        return view_cnt;
    }

    public void setCode(int code){
        this.code = code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public void setUname(String uname){
        this.uname = uname;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setDelivery_cnt(int delivery_cnt){
        this.delivery_cnt = delivery_cnt;
    }
    public void setFavorite_cnt(int favorite_cnt){
        this.favorite_cnt = favorite_cnt;
    }
    public void setView_cnt(int view_cnt){
        this.view_cnt = view_cnt;
    }
}
