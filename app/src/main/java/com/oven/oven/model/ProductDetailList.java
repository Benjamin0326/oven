package com.oven.oven.model;

import java.util.List;

/**
 * Created by sung9 on 2017-09-09.
 */

public class ProductDetailList {
    private int code;
    private String msg;
    private List<ProductDetail> product_list;

    public void setCode(int _code){
        code = _code;
    }
    public void setMsg(String _msg){
        msg = _msg;
    }
    public void setProduct_list(List<ProductDetail> _product_list){
        product_list = _product_list;
    }
    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
    public List<ProductDetail> getProduct_list(){
        return product_list;
    }
}