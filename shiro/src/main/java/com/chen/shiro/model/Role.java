package com.chen.shiro.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 角色
 * <p>
 * @Author LeifChen
 * @Date 2020-04-27
 */
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Integer roleId;
    private String roleName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RoleMenu",
            joinColumns = {@JoinColumn(name = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "menuId")})
    private List<Menu> menuList;
}
