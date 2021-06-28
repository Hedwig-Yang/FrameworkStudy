package cn.zyk.interfacetosql.service;

import cn.zyk.interfacetosql.entity.User;
import cn.zyk.interfacetosql.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:KUN
 * @Data:2021/6/28 14:17
 * @Description: 服务层实现类
 * @Version:1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getUsers() {
        return userMapper.findAll();
    }
}
