package com.oven.oven.model;

/**
 * Created by sung9 on 2018-01-21.
 */

public class UserInfo {
    private int uid;
    private String email;
    private String password;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJoint_date() {
        return joint_date;
    }

    public void setJoint_date(String joint_date) {
        this.joint_date = joint_date;
    }

    private String uname;
    private String hp;
    private String cname;
    private String address;
    private String joint_date;
    private String de_addr;
    private String zipcode;

    public String getDe_addr() {
        return de_addr;
    }

    public void setDe_addr(String de_addr) {
        this.de_addr = de_addr;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
