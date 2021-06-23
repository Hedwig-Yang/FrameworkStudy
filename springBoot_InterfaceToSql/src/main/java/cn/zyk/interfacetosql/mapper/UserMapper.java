package cn.zyk.interfacetosql.mapper;

import cn.zyk.interfacetosql.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author:KUN
 * @Data:2021/6/7 14:54
 * @Description: MyBatis框架的Mapper类
 * @Version:1.0
 */

@Mapper
public interface UserMapper {

    @Select("select * from user")
    public List<User> findAll();
}
