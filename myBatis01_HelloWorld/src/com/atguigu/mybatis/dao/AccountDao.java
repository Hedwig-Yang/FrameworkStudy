package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.beans.Account;
import com.atguigu.mybatis.beans.Book;

/**
 * @Author:KUN
 * @Data:2021/7/20 16:44
 * @Description: 写dao接口,MyBatis中会根据代理的方式自动生成实现类
 * @Version:1.0
 */
public interface AccountDao {
    //定义CRUD相关的方法

    //根据username查询用户余额
    public Account getBalanceByUsername(String username);
}
