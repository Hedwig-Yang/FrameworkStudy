package com.atguigu.tx.annotation.service;

/**
 * @Author:KUN
 * @Data:2021/4/23 10:10
 * @Description: 书店服务接口，提供购书服务
 * @Version:1.0
 */
public interface BookShopService {
    public  void buyBook(String username,String isbn);
}
