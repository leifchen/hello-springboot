package com.chen.security.service;

import com.chen.security.model.Role;
import com.chen.security.model.User;
import com.chen.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义用户认证
 * <p>
 * @Author LeifChen
 * @Date 2020-04-28
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        for (Role role : user.getRoleList()) {
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                simpleGrantedAuthorityList);
    }
}
