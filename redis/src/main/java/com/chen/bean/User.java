package com.chen.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户
 * <p>
 * @Author LeifChen
 * @Date 2020-10-25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {

    private Integer id;
    private String name;
}
