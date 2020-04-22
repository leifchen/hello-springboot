package com.chen.dao.master;

import com.chen.dao.BaseDao;
import com.chen.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserDao
 * <p>
 * @Author LeifChen
 * @Date 2020-04-22
 */
@Mapper
public interface UserDao extends BaseDao<User> {
}
