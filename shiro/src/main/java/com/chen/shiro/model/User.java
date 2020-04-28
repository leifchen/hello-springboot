package com.chen.shiro.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 用户
 * <p>
 * @Author LeifChen
 * @Date 2020-04-27
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer userId;
    private String userName;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserRole",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roleList;
}
