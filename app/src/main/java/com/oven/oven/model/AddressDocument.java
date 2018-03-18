package com.oven.oven.model;

/**
 * Created by sung9 on 2018-01-29.
 */

public class AddressDocument {
    private RoadAddress road_address;
    private Address address;
    private String address_name, x, y;

    public RoadAddress getRoad_address() {
        return road_address;
    }

    public void setRoad_address(RoadAddress road_address) {
        this.road_address = road_address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
