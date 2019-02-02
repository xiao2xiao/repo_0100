package com.neno.mapper;

import com.neno.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: root
 * @Date: 2019/2/2 16:32
 */
@Repository
@Mapper
public interface UserInfoMapper {
    List<UserInfo> selectUserInfos();

    int insertUserInfo(UserInfo userInfo);

    int updateUserInfo(UserInfo userInfo);

    UserInfo queryUserById(int id);
}
