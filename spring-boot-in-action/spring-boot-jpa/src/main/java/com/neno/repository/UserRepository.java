package com.neno.repository;

import com.neno.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: root
 * @Date: 2019/3/26 23:39
 */
@Repository
public interface UserRepository extends JpaRepository<User, Object> {

    @Query(value = "select new User (u.name, u.age) from User u")
    List<User> findAllUsers();

}
