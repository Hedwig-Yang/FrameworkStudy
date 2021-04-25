package com.atguigu.tx.annotation.service;

import com.atguigu.tx.annotation.dao.BookShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:KUN
 * @Data:2021/4/23 10:11
 * @Description: TODO
 * @Version:1.0
 */

@Service
//@Transactional  //将类下的所有方法加入事务管理
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookShopDao bookShopDao;


    /**
     * 事务属性：
     * 1、事务的传播行为：当前事务方法被另外一个事务方法调用时，当前事务方法如何使用事务
     *         propagation = Propagation.REQUIRE       （默认值）使用调用者的事务
     *         propagation = Propagation.REQUIRES_NEW    将调用者的事务挂起，重新开启事务来使用
     * 2、事务的隔离级别 isolation
     *         1.读未提交：可以读取还没有commit到数据库的数据，导致脏读
     *         2.读已提交：由于修改操作导致连续读取到的数据不一致，因此导致不可重复读  （MySQL默认隔离级别）
     *         3.可重复读：由于插入操作导致连续读取到的数据数量不一致，出现幻读
     *         4.串行化：效率低
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)  //将当前方法加入事务管理,只对当前方法起作用
    public void buyBook(String username, String isbn) {
        //1、找书的价格
        int price = bookShopDao.findPriceByIsbn(isbn);
        //2、更新库存
        bookShopDao.updateStock(isbn);
        //3、更新用户余额
        bookShopDao.updateUserAccount(username,price);
    }

    /*
     * 不使用事务的情况下可能出现库存减了以后发现余额不足的情况
     */
}
