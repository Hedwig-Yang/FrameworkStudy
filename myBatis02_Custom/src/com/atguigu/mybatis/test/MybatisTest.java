package com.atguigu.mybatis.test;

import com.atguigu.mybatis.beans.PublicTestBean;
import com.atguigu.mybatis.dao.PublicTestBeanDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
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
        PublicTestBeanDao dao = sqlSession.getMapper(PublicTestBeanDao.class);
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


