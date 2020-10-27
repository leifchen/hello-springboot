package com.chen.service;

import com.chen.util.UserContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * UserService
 * <p>
 * @Author LeifChen
 * @Date 2020-10-27
 */
@Slf4j
@Service
public class UserService {

    public void doSomething() {
        String currentUserName = UserContextUtils.getCurrentUserName();
        log.info("service层---当前登录用户对象：{}", currentUserName);
    }
}
