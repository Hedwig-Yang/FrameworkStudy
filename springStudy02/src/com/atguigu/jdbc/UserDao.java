package com.atguigu.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * @Author:KUN
 * @Data:2021/4/22 16:11
 * @Description: JdbcTemplete实现DAO
 * @Version:1.0
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate npjt;

    public void insertUser(User user){
        String sql = "insert into user (username,birthday,sex,address) value(:username,:birthday,:sex,:address)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        npjt.update(sql,sqlParameterSource);
    }
}
