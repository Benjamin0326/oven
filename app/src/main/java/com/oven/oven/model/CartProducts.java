package com.oven.oven.model;

import java.util.List;

/**
 * Created by sung9 on 2018-01-07.
 */

public class CartProducts {
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

    public List<CartProduct> getProduct_list() {
        return product_list;
    }

    public void setProduct_list(List<CartProduct> product_list) {
        this.product_list = product_list;
    }

    private List<CartProduct> product_list;
}
