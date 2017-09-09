package com.oven.oven.model;

/**
 * Created by sung9 on 2017-09-09.
 */

public class ProductDetail {
    private String pname, image, price, arrival_date, storage, expire_date, notice, description;
    private int min, max;

    public void setMin(int _min){
        min = _min;
    }
    public void setMax(int _max){
        max = _max;
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
    public void setStorage(String _storage){
        storage = _storage;
    }
    public void setExpire_date(String _expire_date){
        expire_date = _expire_date;
    }
    public void setNotice(String _notice){
        notice = _notice;
    }
    public void setDescription(String _description){
        description = _description;
    }

    public int getMin(){
        return min;
    }
    public int getMax(){
        return max;
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
    public String getStorage(){
        return storage;
    }
    public String getExpire_date(){
        return expire_date;
    }
    public String getNotice(){
        return notice;
    }
    public String getDescription(){
        return description;
    }
}
