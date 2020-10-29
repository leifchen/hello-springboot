package com.chen.mapper;

import com.chen.model.Test;

import java.util.List;

/**
 * 测试Mapper
 * <p>
 * @Author LeifChen
 * @Date 2020-05-06
 */
public interface TestMapper {

    /**
     * 查询所有
     * @return
     */
    List<Test> findAll();
}
