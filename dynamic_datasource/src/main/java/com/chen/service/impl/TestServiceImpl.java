package com.chen.service.impl;

import com.chen.annotation.DataSource;
import com.chen.mapper.TestMapper;
import com.chen.model.Test;
import com.chen.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试服务实现类
 * <p>
 * @Author LeifChen
 * @Date 2020-05-06
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @DataSource(name = "master")
    @Override
    public List<Test> findAllWithMaster() {
        return testMapper.findAll();
    }

    @DataSource(name = "slave")
    @Override
    public List<Test> findAllWithSlave() {
        return testMapper.findAll();
    }
}
