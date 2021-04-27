package com.atguigu.tx.annotation.test;

import com.atguigu.tx.annotation.dao.BookShopDao;
import com.atguigu.tx.annotation.service.BookShopService;
import com.atguigu.tx.annotation.service.Cashier;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @Author:KUN
 * @Data:2021/4/23 10:29
 * @Description: 测试事务
 * @Version:1.0
 */

public class testTransaction {

    //测试类中无法正常使用@Autowired进行依赖注入,原因待定
    private BookShopService bookShopService;
    private BookShopDao bookShopDao;
    private Cashier cashier;

    @Before
    public void init(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-tx.xml");
        bookShopService = ctx.getBean("bookShopServiceImpl", BookShopService.class);
        bookShopDao = ctx.getBean("bookShopDaoImpl", BookShopDao.class);
        //接受事务管理以后实际接收到的bookShopService是一个由事务管理器生成的一个代理对象
        System.out.println(bookShopService.getClass().getName());

        cashier = ctx.getBean("cashierImpl",Cashier.class);

    }

    /**
     * 测试事务
     */
    @Test
    public void testTx(){
        bookShopService.buyBook("Tom", "ISBN-001");
    }

    /**
     * 测试事务的传播行为
     */
    @Test
    public void testCheckout(){
        ArrayList<String> isbns = new ArrayList<>();
        isbns.add("ISBN-001");
        isbns.add("ISBN-002");
        cashier.checkOut("Tom",isbns);

    }
}
