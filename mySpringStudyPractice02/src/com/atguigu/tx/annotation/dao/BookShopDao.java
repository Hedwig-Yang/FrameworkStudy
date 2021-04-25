package com.atguigu.tx.annotation.dao;

/**
 * @Author:KUN
 * @Data:2021/4/22 18:19
 * @Description: 书库数据操作DAO接口
 * @Version:1.0
 */
public interface BookShopDao {
    //根据书号查询价格
    public int findPriceByIsbn(String isbn);

    //更新库存
    public void updateStock(String isbn);

    //更新用户余额
    public void updateUserAccount(String username,Integer price);
}
