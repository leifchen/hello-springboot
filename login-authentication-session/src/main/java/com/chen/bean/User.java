package com.chen.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * User
 * <p>
 * @Author LeifChen
 * @Date 2020-10-27
 */
@Data
public class User implements Serializable {
    private String username;
    private String password;
}
