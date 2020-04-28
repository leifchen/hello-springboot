package com.chen.security.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 菜单
 * <p>
 * @Author LeifChen
 * @Date 2020-04-27
 */
@Data
@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Integer menuId;
    private String menuName;
}
