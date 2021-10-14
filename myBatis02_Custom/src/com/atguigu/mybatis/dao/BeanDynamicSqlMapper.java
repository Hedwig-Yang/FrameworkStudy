package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.beans.Major;
import com.atguigu.mybatis.beans.PublicTestBean;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Z
 * @Data:2021/10/12 18:24
 * @Description: 动态SQL测试类
 * @Version:1.0
 */
public interface BeanDynamicSqlMapper {

    //MyBatis动态SQL中<if>和<where>标签测试
    public List<PublicTestBean> getBeansByConditionIfWhere(PublicTestBean beanCondition);

    //MyBatis动态SQL中<trim>标签测试
    public List<PublicTestBean> getBeansByConditionTrim(PublicTestBean beanCondition);

    //MyBatis动态SQL中<set>标签测试
    public Integer updateBeansBySet(PublicTestBean beanCondition);

    //MyBatis动态SQL中<choose>标签测试
    public List<PublicTestBean> getBeansByChoose(PublicTestBean beanCondition);

    //MyBatis动态SQL中<foreach>标签测试
    public List<PublicTestBean> getBeansByForeach(@Param("ids") List<Integer> idList);

    //MyBatis动态SQL中<foreach>标签测试
    public Integer BatchInsertByForeach(@Param("majors") ArrayList<Major> majors);
}
