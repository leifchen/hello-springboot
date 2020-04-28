package com.chen.shiro.config;

import com.chen.shiro.model.Menu;
import com.chen.shiro.model.Role;
import com.chen.shiro.model.User;
import com.chen.shiro.repository.UserRepository;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义身份认证
 * <p>
 * @Author LeifChen
 * @Date 2020-04-27
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;

    /**
     * 授权：获取角色的菜单权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        for (Role role : user.getRoleList()) {
            authorizationInfo.addRole(role.getRoleName());
            for (Menu menu : role.getMenuList()) {
                authorizationInfo.addStringPermission(menu.getMenuName());
            }
        }
        return authorizationInfo;
    }

    /**
     * 认证：校验用户名和密码
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        String userName = (String) authenticationToken.getPrincipal();
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), user.getUserName());
    }
}
