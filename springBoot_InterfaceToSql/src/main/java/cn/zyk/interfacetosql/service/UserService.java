package cn.zyk.interfacetosql.service;

import cn.zyk.interfacetosql.entity.User;

import java.util.List;

/**
 * @Author:KUN
 * @Data:2021/6/28 14:14
 * @Description: 服务层接口
 * @Version:1.0
 */
public interface UserService {

    /**
     * 获取表内信息
     */
    public List<User>getUsers();
}
