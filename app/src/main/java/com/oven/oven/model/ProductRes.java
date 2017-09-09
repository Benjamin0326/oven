package com.oven.oven.model;

/**
 * Created by sung9 on 2017-09-09.
 */

public class ProductRes {
    private int pid, min;
    private String pname, image, price, arrival_date;

    public void setPid(int _pid){
        pid = _pid;
    }
    public void setMin(int _min){
        min = _min;
    }
    public void setpname(String _pname){
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

    public int getPid(){
        return pid;
    }
    public int getMin(){
        return min;
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
}
