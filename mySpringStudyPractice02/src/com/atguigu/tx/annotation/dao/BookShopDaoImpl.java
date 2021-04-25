package com.atguigu.tx.annotation.dao;

import com.atguigu.tx.annotation.dao.BookShopDao;
import com.atguigu.tx.annotation.exception.AccountException;
import com.atguigu.tx.annotation.exception.BookStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author:KUN
 * @Data:2021/4/22 18:36
 * @Description: 数据库操作实现类
 * @Version:1.0
 */
@Repository
public class BookShopDaoImpl implements BookShopDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int findPriceByIsbn(String isbn) {
        //查询价格
        String sql = "select price from book where isbn = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,isbn);
    }

    @Override
    public void updateStock(String isbn) {
        //更新库存（先判断库存是否足够）
        String sql = "select stock from book_stock where isbn = ?";
        Integer stock = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
        if(stock < 0){
            //抛出自定义异常
            throw new BookStockException("库存不足.......");
        }
        sql = "update book_stock set stock = stock-1 where isbn = ?";
        jdbcTemplate.update(sql,isbn);
    }

    @Override
    public void updateUserAccount(String username, Integer price) {
        //判断余额是否足够
        String sql = "select balance from account where username = ?";
        Integer balance = jdbcTemplate.queryForObject(sql, Integer.class, username);
        if(balance < price){
            throw new AccountException("用户余额不足.......");
        }
        sql = "update account set balance = balance - ? where username = ?";
        jdbcTemplate.update(sql,price,username);
    }
}
