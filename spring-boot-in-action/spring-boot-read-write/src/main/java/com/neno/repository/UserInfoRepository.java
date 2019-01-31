package com.neno.repository;

import com.neno.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: root
 * @Date: 2019/1/31 12:20
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
}
