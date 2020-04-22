package com.chen.service.impl;

import com.chen.dao.BaseDao;
import com.chen.dao.cluster.StudentDao;
import com.chen.model.Student;
import com.chen.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentServiceImpl
 * <p>
 * @Author LeifChen
 * @Date 2020-04-22
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    protected BaseDao<Student> getMapper() {
        return studentDao;
    }
}
