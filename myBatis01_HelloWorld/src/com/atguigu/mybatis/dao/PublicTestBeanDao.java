package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.beans.PublicTestBean;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Author:KUN
 * @Data:2021/7/22 10:55
 * @Description: 数据库操作类
 * @Version:1.0
 */
public interface PublicTestBeanDao {

    /*
     * 增删改时，直接修改Mapper接口的返回值类型，MyBatis会自动返回执行sql语句的影响条数 */

    //增加
    public int addPublicTestBean(PublicTestBean testBean);

    //删除
    public int deletePublicTestBeanById(int id);

    //修改
    public int updatePublicTestBean(PublicTestBean testBean);

    //查询
    public PublicTestBean getPublicTestBeanById(int id);

    //查询2
    public PublicTestBean getBeanByIdAndAge(@Param("id") int id, @Param("age")int age);

    //查询3
    public PublicTestBean getBeanByMap(Map map);
}
