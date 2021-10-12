package com.atguigu.mybatis.test;

import com.atguigu.mybatis.beans.Major;
import com.atguigu.mybatis.beans.PublicTestBean;
import com.atguigu.mybatis.dao.BeanMapper;
import com.atguigu.mybatis.dao.PublicTestBeanMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Map;

/**
 * @Author:KUN
 * @Data:2021/7/20 10:25
 * @Description: 测试Mybatis
 * @Version:1.0
 */
public class MybatisTest {

    //day02

    //测试查询单行数据返回单个对象，见：getPublicTestBeanById(int id)；


    @Test
    public void testSelect() throws Exception{
        SqlSession sqlSession = getSqlSession();
        PublicTestBeanMapper dao = sqlSession.getMapper(PublicTestBeanMapper.class);
        try{
            //测试查询多行数据，返回一个对象的集合
            //List<PublicTestBean> beanList = dao.getBeanList();
            //System.out.println(beanList);

            //测试查询单条数据返回一个Map
            //Map<String, Object> beanRetrunMap = dao.getBeanRetrunMap(3);
            //System.out.println(beanRetrunMap);

            //测试查询多条数据返回一个Map
            Map<Integer, PublicTestBean> beansRetrunMap = dao.getBeansRetrunMap();
            System.out.println(beansRetrunMap);
        } finally{
            sqlSession.close();
        }
    }

    //测试自定义映射
    @Test
    public void testResultMap() throws Exception{
        SqlSession sqlSession = getSqlSession();
        BeanMapper dao = sqlSession.getMapper(BeanMapper.class);
        try{
            PublicTestBean beanById = dao.getBeanById(3);
            System.out.println(beanById);
        }finally {
            sqlSession.close();
        }
    }

    //测试自定义映射(级联属性映射和关联属性映射的情况)
    @Test
    public void testResultMapCascade() throws Exception{
        SqlSession sqlSession = getSqlSession();
        BeanMapper dao = sqlSession.getMapper(BeanMapper.class);
        try{
            PublicTestBean beanById = dao.getBeanAndMajor(3);
            System.out.println(beanById);
            System.out.println(beanById.getMajor());
        }finally {
            sqlSession.close();
        }
    }

    //测试association的分步查询,基于分步查询配置懒加载
    //在实际开发中每个实体类都对应有增删改查的操作，因此采用分步查询相比较连表查询更加简洁
    @Test
    public void testResultMapAssociationByStep() throws Exception{
        SqlSession sqlSession = getSqlSession();
        BeanMapper dao = sqlSession.getMapper(BeanMapper.class);
        try{
            PublicTestBean beanById = dao.getBeanAndMajorByStep(3);
            System.out.println(beanById.getUserName());
            System.out.println("--------------------------------------");
            System.out.println(beanById.getMajor().getMajorName());
        }finally {
            sqlSession.close();
        }
    }

    //测试resultMap的级联映射：collection标签标签定义多个对象的封装规则（1对多）
    @Test
    public void testResultMapCollection() throws Exception{
        SqlSession sqlSession = getSqlSession();
        BeanMapper dao = sqlSession.getMapper(BeanMapper.class);
        try{
            Major majorById = dao.getMajorAndBeans(3);
            System.out.println(majorById.toString());
            System.out.println(majorById.getBeans());
        }finally {
            sqlSession.close();
        }
    }

    //测试collection的分步查询,基于分步查询配置懒加载
    @Test
    public void testResultMapCollectionByStep() throws Exception{
        SqlSession sqlSession = getSqlSession();
        BeanMapper dao = sqlSession.getMapper(BeanMapper.class);
        try{
            Major majorById = dao.getMajorAndBeansByStep(3);
            System.out.println(majorById.getMajorName());
            System.out.println("-----------------------------------------");
            System.out.println(majorById.getBeans());
        }finally {
            sqlSession.close();
        }
    }












    public SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //打开自动提交
        //sqlSessionFactory.openSession(true)
        return sqlSessionFactory.openSession();
    }

    public Connection getJdbcConnection(){
        String URL = "jdbc:mysql://127.0.0.1:3306/heima_database?characterEncoding=utf-8";
        String USER = "root";
        String PASSWORD = "root";
        try {
            // 1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获得数据库链接
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }



}


