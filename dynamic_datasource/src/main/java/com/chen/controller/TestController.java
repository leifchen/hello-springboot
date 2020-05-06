package com.chen.controller;

import com.chen.model.Test;
import com.chen.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试控制器
 * <p>
 * @Author LeifChen
 * @Date 2020-05-06
 */

@RestController
@RequestMapping
public class TestController {

    private static final String MASTER = "MASTER";

    @Autowired
    private TestService testService;


    @GetMapping("/{name}/list")
    public List<Test> list(@PathVariable("name") String name) {
        if (MASTER.equals(name)) {
            return testService.findAllWithMaster();
        } else {
            return testService.findAllWithSlave();
        }
    }
}
