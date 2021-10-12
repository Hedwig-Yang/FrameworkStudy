package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.beans.Major;

/**
 * @Author:Z
 * @Data:2021/10/11 11:03
 * @Description: 专业类接口对象
 * @Version:1.0
 */
public interface MajorMapper {

    public Major getMajorById(Integer id);
}
