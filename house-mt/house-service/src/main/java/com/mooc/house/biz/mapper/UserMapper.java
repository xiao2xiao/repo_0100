package com.mooc.house.biz.mapper;

import com.mooc.house.common.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: root
 * @Date: 2019/2/13 23:37
 */
@Mapper
@Repository
public interface UserMapper {
    List<User> selectUsers();
}
