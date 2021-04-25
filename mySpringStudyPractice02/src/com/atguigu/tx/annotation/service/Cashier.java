package com.atguigu.tx.annotation.service;

import java.util.List;

/**
 * @Author:KUN
 * @Data:2021/4/23 15:00
 * @Description: 一次购买多本书并结账
 * @Version:1.0
 */
public interface Cashier {

    public void checkOut(String username, List<String> isbn);

}
