package com.neno.repository;

import com.neno.JpaApplicationTests;
import com.neno.model.User;
import com.neno.vo.UserVo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: root
 * @Date: 2019/3/26 23:43
 */
public class UserRepositoryTests extends JpaApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTests.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserVoRepository userVoRepository;

    @Test
    public void testSave() {
        User user = new User("bbb", 20, "杭州");
        userRepository.save(user);
    }

    @Test
    public void testFindOne() {
        List<User> users = userRepository.findAllUsers();
        users.stream().forEach(System.out::println);
    }

    @Test
    public void testFindAll() {
        List<UserVo> users = userVoRepository.findAll();
        users.stream().forEach(System.out::println);
    }

}
