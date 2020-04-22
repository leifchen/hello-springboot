package com.chen.dao.cluster;

import com.chen.dao.BaseDao;
import com.chen.model.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * StudentDao
 * <p>
 * @Author LeifChen
 * @Date 2020-04-22
 */
@Mapper
public interface StudentDao extends BaseDao<Student> {
}
