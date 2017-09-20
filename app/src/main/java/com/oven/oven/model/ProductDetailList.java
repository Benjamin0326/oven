package com.oven.oven.model;

import java.util.List;

/**
 * Created by sung9 on 2017-09-09.
 */

public class ProductDetailList {
    private int code, min, max, toggle;
    private String msg, pname, image, price, arrival_date, notice, description;
    /*
    private List<ProductDetail> product_detail;
    public void setProduct_detail(List<ProductDetail> _product_detail){
        product_detail = _product_detail;
    }
    public List<ProductDetail> getProduct_detail(){
        return product_detail;
    }
     */

    public void setCode(int _code){
        code = _code;
    }
    public void setMsg(String _msg){
        msg = _msg;
    }
    public void setMin(int _min){
        min = _min;
    }
    public void setMax(int _max){
        max = _max;
    }
    public void setToggle(int _toggle){
        toggle = _toggle;
    }
    public void setPname(String _pname){
        pname = _pname;
    }
    public void setImage(String _image){
        image = _image;
    }
    public void setPrice(String _price){
        price = _price;
    }
    public void setArrival_date(String _arrival_date){
        arrival_date = _arrival_date;
    }
    public void setNotice(String _notice){
        notice = _notice;
    }
    public void setDescription(String _description){
        description = _description;
    }

    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
    public int getMin(){
        return min;
    }
    public int getMax(){
        return max;
    }
    public int getToggle(){
        return toggle;
    }
    public String getPname(){
        return pname;
    }
    public String getImage(){
        return image;
    }
    public String getPrice(){
        return price;
    }
    public String getArrival_date(){
        return arrival_date;
    }
    public String getNotice(){
        return notice;
    }
    public String getDescription(){
        return description;
    }

}
