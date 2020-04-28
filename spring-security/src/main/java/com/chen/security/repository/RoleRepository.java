package com.chen.security.repository;

import com.chen.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色服务
 * <p>
 * @Author LeifChen
 * @Date 2020-04-28
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
