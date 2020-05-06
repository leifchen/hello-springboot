package com.chen.service;

import com.chen.model.Test;

import java.util.List;

/**
 * 测试服务接口
 * <p>
 * @Author LeifChen
 * @Date 2020-05-06
 */
public interface TestService {

    /**
     * 查询master
     * @return
     */
    List<Test> findAllWithMaster();

    /**
     * 查询slave
     * @return
     */
    List<Test> findAllWithSlave();
}
