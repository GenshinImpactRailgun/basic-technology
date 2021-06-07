package com.frame.spring.di.pojo;

/**
 * @Author: railgun
 * 2021/6/7 12:18
 * PS: 地址
 **/
public class Address {

    private String province;

    private String city;

    private String details;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
