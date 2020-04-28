package com.chen.security.repository;

import com.chen.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户服务
 * <p>
 * @Author LeifChen
 * @Date 2020-04-28
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User findByUserName(String userName);
}
