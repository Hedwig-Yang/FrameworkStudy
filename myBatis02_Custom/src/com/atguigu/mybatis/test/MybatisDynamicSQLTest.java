package com.atguigu.mybatis.test;

import com.atguigu.mybatis.beans.Major;
import com.atguigu.mybatis.beans.PublicTestBean;
import com.atguigu.mybatis.dao.BeanDynamicSqlMapper;
import com.atguigu.mybatis.dao.PublicTestBeanMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Z
 * @Data:2021/10/13 14:24
 * @Description: 动态SQL测试
 * @Version:1.0
 */
public class MybatisDynamicSQLTest {

    //MyBatis动态SQL中<if>和<where>标签测试
    @Test
    public void dynamicSqlIfWhereTest() throws Exception{
        SqlSession sqlSession = getSqlSession();
        BeanDynamicSqlMapper dao = sqlSession.getMapper(BeanDynamicSqlMapper.class);
        try{
            PublicTestBean publicTestBean = new PublicTestBean();
            //publicTestBean.setId(3);
            List<PublicTestBean> beansByConditionIfWhere = dao.getBeansByConditionIfWhere(publicTestBean);
            System.out.println(beansByConditionIfWhere);
        }finally{
            sqlSession.close();
        }
    }

    //MyBatis动态SQL中<trim>标签测试
    @Test
    public void dynamicSqlTrimTest() throws Exception{
        SqlSession sqlSession = getSqlSession();
        BeanDynamicSqlMapper dao = sqlSession.getMapper(BeanDynamicSqlMapper.class);
        try{
            PublicTestBean publicTestBean = new PublicTestBean();
            //publicTestBean.setId(3);
            List<PublicTestBean> beansByConditionIfWhere = dao.getBeansByConditionTrim(publicTestBean);
            System.out.println(beansByConditionIfWhere);
        }finally{
            sqlSession.close();
        }
    }

    //MyBatis动态SQL中<set>标签测试
    @Test
    public void dynamicSqlSetTest() throws Exception{
        SqlSession sqlSession = getSqlSession();
        BeanDynamicSqlMapper dao = sqlSession.getMapper(BeanDynamicSqlMapper.class);
        try{
            PublicTestBean publicTestBean = new PublicTestBean();
            publicTestBean.setId(3);
            publicTestBean.setAge(21);
            dao.updateBeansBySet(publicTestBean);
        }finally{
            sqlSession.close();
        }
    }

    //MyBatis动态SQL中<choose>标签测试
    @Test
    public void dynamicSqlChooseTest() throws Exception{
        SqlSession sqlSession = getSqlSession();
        BeanDynamicSqlMapper dao = sqlSession.getMapper(BeanDynamicSqlMapper.class);
        try{
            PublicTestBean publicTestBean = new PublicTestBean();
            //publicTestBean.setId(3);
            //publicTestBean.setAge(21);
            System.out.println(dao.getBeansByChoose(publicTestBean));
        }finally{
            sqlSession.close();
        }
    }

    //MyBatis动态SQL中<foreach>标签测试
    @Test
    public void dynamicSqlForeachTest() throws Exception{
        SqlSession sqlSession = getSqlSession();
        BeanDynamicSqlMapper dao = sqlSession.getMapper(BeanDynamicSqlMapper.class);
        try{
            ArrayList<Integer> ids = new ArrayList<>();
            ids.add(1);
            ids.add(3);
            ids.add(7);
            System.out.println(dao.getBeansByForeach(ids));
        }finally{
            sqlSession.close();
        }
    }

    //MyBatis动态SQL中<foreach>标签批量插入测试
    @Test
    public void ForeachBatchInsertTest() throws Exception{
        SqlSession sqlSession = getSqlSession();
        BeanDynamicSqlMapper dao = sqlSession.getMapper(BeanDynamicSqlMapper.class);
        try{
            ArrayList<Major> majors = new ArrayList<>();
            Major major1 = new Major(8,"math");
            Major major2 = new Major(9,"sports");
            majors.add(major1);
            majors.add(major2);
            System.out.println(dao.BatchInsertByForeach(majors));
        }finally{
            sqlSession.close();
        }
    }





    public SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //打开自动提交。MyBatis内做增删改操作时，需要有一步提交操作，才能生效。也可设置自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        return sqlSession;
    }
}
