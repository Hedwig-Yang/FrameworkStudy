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
    //定义CRUD相关的方法,要获取对数据库的影响条数活着是否操作成功，只需返回Integer / Boolean就好

    //添加一个新的Account
    public void addAcount(Account account);

    //删除一个Account
    public void deleteAccount(String username);

    //修改一个Account
    public void updateAccount(Account account);

    //根据username查询用户余额
    public Account getBalanceByUsername(String username);
}
