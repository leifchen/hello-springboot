package com.chen.shiro.repository;

import com.chen.shiro.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 用户服务测试类
 * <p>
 * @Author LeifChen
 * @Date 2020-04-27
 */
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUserName() {
        User test = userRepository.findByUserName("test");
        assertNotNull(test);
    }
}