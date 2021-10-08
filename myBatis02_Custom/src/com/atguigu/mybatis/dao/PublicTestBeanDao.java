package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.beans.PublicTestBean;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author:KUN
 * @Data:2021/7/22 10:55
 * @Description: 数据库操作类
 * @Version:1.0
 */
public interface PublicTestBeanDao {

    //day02

    //查询单行数据返回单个对象，见：getPublicTestBeanById(int id)；

    //查询多行数据，返回一个对象的集合
    public List<PublicTestBean> getBeanList();

    //查询单条数据返回一个Map
    public Map<String,Object>getBeanRetrunMap(Integer id);

    //查询多条数据返回一个Map
    @MapKey("id") //指定适用对象的哪个属性作为Map的Key
    public Map<Integer,PublicTestBean>getBeansRetrunMap();

}
