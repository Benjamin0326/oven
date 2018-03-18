package com.oven.oven.model;

import java.util.List;

/**
 * Created by sung9 on 2017-09-09.
 */

public class ProductResList {
    private int code, cart_cnt;
    private String msg;

    public int getCart_cnt() {
        return cart_cnt;
    }

    public void setCart_cnt(int cart_cnt) {
        this.cart_cnt = cart_cnt;
    }

    private List<ProductRes> product_list;

    public void setCode(int _code){
        code = _code;
    }
    public void setMsg(String _msg){
        msg = _msg;
    }
    public void setProduct_list(List<ProductRes> _product_list){
        product_list = _product_list;
    }
    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
    public List<ProductRes> getProduct_list(){
        return product_list;
    }
}
