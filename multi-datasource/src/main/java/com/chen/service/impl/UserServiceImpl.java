package com.chen.service.impl;

import com.chen.dao.BaseDao;
import com.chen.dao.master.UserDao;
import com.chen.model.User;
import com.chen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 * <p>
 * @Author LeifChen
 * @Date 2020-04-22
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    protected BaseDao<User> getMapper() {
        return userDao;
    }
}
