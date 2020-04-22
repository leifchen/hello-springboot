package com.chen.controller;

import com.chen.model.User;
import com.chen.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController
 * <p>
 * @Author LeifChen
 * @Date 2020-04-22
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public boolean insert(@RequestBody User user) {
        log.debug("开始新增...");
        return userService.insert(user);
    }

    @PutMapping("/user")
    public boolean update(@RequestBody User user) {
        log.debug("开始更新...");
        return userService.update(user);
    }

    @DeleteMapping("/user")
    public boolean delete(@RequestBody User user) {
        log.debug("开始删除...");
        return userService.delete(user);
    }

    @GetMapping("/user")
    public List<User> findByUser(User user) {
        log.debug("开始查询...");
        return userService.findByListEntity(user);
    }
}
