package com.atguigu.tx.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:KUN
 * @Data:2021/4/23 15:04
 * @Description: 一次购买多本书并结账
 * @Version:1.0
 */
@Service
public class CashierImpl implements Cashier{

    @Autowired
    private BookShopService bookShopService;

    @Override
    @Transactional
    public void checkOut(String username, List<String> isbns) {
        for(String isbn: isbns){
            bookShopService.buyBook(username,isbn);
        }
    }

    /*问题：在一次购买多本书时，因为结账过程没有事务管理导致结账过程执行到一半，出现余额不足或者库存不足的情况
     *解决方法：给结账过程加事务管理
     */
}
