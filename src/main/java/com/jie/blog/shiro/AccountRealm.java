package com.jie.blog.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.jie.blog.pojo.User;
import com.jie.blog.service.UserService;
import com.jie.blog.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof  JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken=(JwtToken) authenticationToken;
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user=userService.getById(Long.valueOf(userId));

        if (user==null){
            throw new UnknownAccountException("用户不存在");
        }
        if (user.getStatus()==-1){
            throw new LockedAccountException("用户被锁定");
        }
        AccountProfile  profile=new AccountProfile();
        BeanUtil.copyProperties(user,profile);

        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}
