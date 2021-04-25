package com.atguigu.jdbc;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.text.ParseException;
import java.util.*;

/**
 * @Author:KUN
 * @Data:2021/4/21 17:55
 * @Description: 测试JdbcTemplet
 * @Version:1.0
 */
public class TestJdbc {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate npjt;
    private UserDao userDao;

    @Before  //每次在测试方法执行前执行
    public void init(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        npjt = ctx.getBean("namedParameterJdbcTemplate", NamedParameterJdbcTemplate.class);
        userDao = ctx.getBean("userDao", UserDao.class);
    }

    /**
     * update():增删改查
     */
    @Test
    public void test(){
        String sql = "insert into user (username,birthday,sex,address) value(?,?,?,?)";
        jdbcTemplate.update(sql,"小吴","1994-04-18","男","武汉武昌");
    }

    /**
     * batUpdate():测试批量增删改
     */
    @Test
    public void testBatUpdate(){
        String sql = "insert into user (username,birthday,sex,address) value(?,?,?,?)";
        List<Object[]> batchArgs = new ArrayList<Object[]>();
        batchArgs.add(new Object[] {"小陈","2001-05-19","男","黑龙江"});
        batchArgs.add(new Object[] {"小杨","1994-05-15","男","浙江"});
        batchArgs.add(new Object[] {"小其","2001-08-13","女","呼和浩特"});
        jdbcTemplate.batchUpdate(sql,batchArgs);
    }

    /**
     * queryForObject():
     * 1、查询单行数据，返回一个对象
     * 2、查询单个值，返回单个值
     */
    @Test
    public void testQueryForObject(){
        //1、查询单行数据，返回一个对象
        String sql = "select id,username,birthday,sex,address from user where id = ?";
        //rowMapper:行映射 将结果集的一条数据映射成具体的一个java对象。
        RowMapper<User> rowMapper = new BeanPropertyRowMapper(User.class);
        User user = jdbcTemplate.queryForObject(sql, rowMapper,52);
        System.out.println(user.toString());
    }

    @Test
    public void testQueryForObjectReturnValue(){
        //2、查询单个值，返回单个值
        String sql = "select count(id) from user"; //注意：sql语句中尽可能不出现*，count函数不统计空值
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(integer);
    }

    /**
     * auery():查询多条数据返回多个对象
     */
    @Test
    public void testQuery(){
        String sql = "select id,username,birthday,sex,address from user";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        List<User> users = jdbcTemplate.query(sql, rowMapper);
        System.out.println(users);
    }

    /**
     * 测试具名参数模板类
     */
    @Test
    public void testNpjt(){
        String sql = "insert into user (username,birthday,sex,address) value(:un,:bd,:sx,:ad)";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("un","小乐");
        paramMap.put("bd","1994-08-09");
        paramMap.put("sx","女");
        paramMap.put("ad","浙江");
        int i = npjt.update(sql, paramMap);
        System.out.println("成功插入"+i+"条数据");

    }

    /**
     * 模拟Service层直接传递给Dao层一个具体的对象
     */
    @Test
    public void testnn() throws ParseException {
        String sql = "insert into user (username,birthday,sex,address) value(:username,:birthday,:sex,:address)";
        Date date = DateUtils.parseDate("2019-09-02", "yyyy-MM-dd");
        java.sql.Date bd = new java.sql.Date(date.getTime());
        User user = new User(null,"小陆",bd,"男","甘肃");
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        int i = npjt.update(sql, sqlParameterSource);
        System.out.println("成功插入"+i+"条数据");
    }

    /**
     * 测试UserDao
     */
    @Test
    public void testUserDao() throws ParseException {
        Date date = DateUtils.parseDate("1994-05-02", "yyyy-MM-dd");
        java.sql.Date bd = new java.sql.Date(date.getTime());
        User user = new User(null,"小周",bd,"男","江西");
        userDao.insertUser(user);
    }

}














