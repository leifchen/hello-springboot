package com.chen;

import com.chen.bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis单元测试
 * <p>
 * @Author LeifChen
 * @Date 2020-10-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void one() {
        final String key = "redis:template:one:string";
        final String content = "RedisTemplate字符串信息";
        ValueOperations valueOperations = redisTemplate.opsForValue();
        log.info("写入缓存string内容：{}", content);
        valueOperations.set(key, content);
        Object result = valueOperations.get(key);
        log.info("读取缓存string内容：{}", result);
    }

    @Test
    public void two() throws JsonProcessingException {
        User user = new User(1, "LeifChen");
        final String key = "redis:template:two:object";
        final String content = objectMapper.writeValueAsString(user);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        log.info("写入缓存object内容：{}", content);
        valueOperations.set(key, content);
        Object result = valueOperations.get(key);
        if (result != null) {
            User resultUser = objectMapper.readValue(result.toString(), User.class);
            log.info("读取缓存object内容并反序列化结果：{}", resultUser);
        }
    }

    @Test
    public void three() {
        final String key = "string:redis:template:three:string";
        final String content = "StringTemplate字符串信息";
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        log.info("写入缓存string内容：{}", content);
        valueOperations.set(key, content);
        Object result = valueOperations.get(key);
        log.info("读取缓存string内容：{}", result);
    }

    @Test
    public void four() throws JsonProcessingException {
        User user = new User(1, "LeifChen");
        final String key = "string:redis:template:four:object";
        final String content = objectMapper.writeValueAsString(user);
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        log.info("写入缓存object内容：{}", content);
        valueOperations.set(key, content);
        Object result = valueOperations.get(key);
        if (result != null) {
            User resultUser = objectMapper.readValue(result.toString(), User.class);
            log.info("读取缓存object内容并反序列化结果：{}", resultUser);
        }
    }

    @Test
    public void list() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Leif"));
        userList.add(new User(2, "Chen"));
        userList.add(new User(2, "Chen"));
        final String key = "redis:list";
        ListOperations listOperations = redisTemplate.opsForList();
        for (User user : userList) {
            listOperations.leftPush(key, user);
        }

        Object result = listOperations.rightPop(key);
        User resUser;
        while (result != null) {
            resUser = (User) result;
            log.info("读取缓存list当前内容：{}", resUser);
            result = listOperations.rightPop(key);
        }
    }

    @Test
    public void set() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Leif"));
        userList.add(new User(2, "Chen"));
        userList.add(new User(2, "Chen"));
        final String key = "redis:set";
        SetOperations setOperations = redisTemplate.opsForSet();
        for (User user : userList) {
            setOperations.add(key, user);
        }

        Object result = setOperations.pop(key);
        User resUser;
        while (result != null) {
            resUser = (User) result;
            log.info("读取缓存set当前内容：{}", resUser);
            result = setOperations.pop(key);
        }
    }

    @Test
    public void zset() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(2, "Chen"));
        userList.add(new User(1, "Leif"));
        final String key = "redis:zset";
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        for (User user : userList) {
            zSetOperations.add(key, user, user.getId());
        }
        Long size = zSetOperations.size(key);
        Set<User> resSet = zSetOperations.range(key, 0L, size);
        for (User user : resSet) {
            log.info("读取缓存zset当前内容：{}", user);
        }
    }

    @Test
    public void hash() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Leif"));
        userList.add(new User(2, "Chen"));
        final String key = "redis:hash";
        HashOperations hashOperations = redisTemplate.opsForHash();
        for (User user : userList) {
            hashOperations.put(key, String.valueOf(user.getId()), user);
        }
        Map<String, User> userMap = hashOperations.entries(key);
        log.info("读取缓存hssh内容：{}", userMap);
        User user1 = (User) hashOperations.get(key, "1");
        log.info("获取指定用户：{}", user1);
    }

    @Test
    public void expire1() throws InterruptedException {
        final String key = "redis:ttl:one";
        final String content = "expire操作1";
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, content, 5L, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(2);
        Boolean exist = redisTemplate.hasKey(key);
        log.info("等待2秒，判断key是否还存在：{}", exist);
        TimeUnit.SECONDS.sleep(3);
        exist = redisTemplate.hasKey(key);
        log.info("再经过3秒，判断key是否还存在：{}", exist);
    }

    @Test
    public void expire2() throws InterruptedException {
        final String key = "redis:ttl:two";
        final String content = "expire操作2";
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, content);
        redisTemplate.expire(key, 5L, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(2);
        Boolean exist = redisTemplate.hasKey(key);
        log.info("等待2秒，判断key是否还存在：{}", exist);
        TimeUnit.SECONDS.sleep(3);
        exist = redisTemplate.hasKey(key);
        log.info("再经过3秒，判断key是否还存在：{}", exist);
    }
}
