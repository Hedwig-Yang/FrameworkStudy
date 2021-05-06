package com.telecom.springmvc.beans;

/**
 * @Author:KUN
 * @Data:2021/5/6 16:08
 * @Description: 地址类
 * @Version:1.0
 */
public class Address {

    private String province;
    private String city;

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

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
