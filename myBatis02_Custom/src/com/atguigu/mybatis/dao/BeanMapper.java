package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.beans.Major;
import com.atguigu.mybatis.beans.PublicTestBean;

/**
 * @Author:Z
 * @Data:2021/10/9 10:03
 * @Description: 测试Mapper映射文件的自定义返回类型
 * @Version:1.0
 */
public interface BeanMapper {

    public PublicTestBean getBeanById(Integer id);

    public PublicTestBean getBeanAndMajor(Integer id);

    public PublicTestBean getBeanAndMajorByStep(Integer id);

    public Major getMajorAndBeans(Integer id);

    public Major getMajorAndBeansByStep(Integer id);

}
