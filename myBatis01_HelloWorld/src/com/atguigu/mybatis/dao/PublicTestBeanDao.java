package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.beans.PublicTestBean;

/**
 * @Author:KUN
 * @Data:2021/7/22 10:55
 * @Description: 数据库操作类
 * @Version:1.0
 */
public interface PublicTestBeanDao {

    //增加
    public int addPublicTestBean(PublicTestBean testBean);

    //删除
    public int deletePublicTestBeanById(int id);

    //修改
    public int updatePublicTestBean(PublicTestBean testBean);

    //查询
    public PublicTestBean getPublicTestBeanById(int id);
}
