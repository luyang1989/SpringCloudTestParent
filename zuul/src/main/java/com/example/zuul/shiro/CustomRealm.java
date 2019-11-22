package com.example.zuul.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.base.sys.config.Constant;
import com.example.base.sys.entity.SecUser;
import com.example.base.util.EncodeUtils;
import com.example.zuul.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 */
public class CustomRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRealm.class);


    @Autowired
    public LoginService loginService;

//    权限认证，即登录过后，每个身份不一定，对应的所能看的页面也不一样
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
     * private UserService userService;
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        LOGGER.info("-------身份认证方法--------");
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        if (username == null) {
            LOGGER.warn("用户名不能为空");
            throw new AccountException("用户名不能为空");
        }

        SecUser user = null;
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("username",username);
            user = loginService.getOne(queryWrapper);
        } catch(Exception ex) {
            LOGGER.warn("获取用户失败\n" + ex.getMessage());
        }
        if (user == null) {
            LOGGER.warn("用户不存在");
            throw new UnknownAccountException("用户不存在");
        }
        if(user.getEnabled() == null || "2".equals(user.getEnabled())) {
            LOGGER.warn("用户被禁止使用");
            throw new UnknownAccountException("用户被禁止使用");
        }
        LOGGER.info("用户【" + username + "】登录成功");
        byte[] salt = EncodeUtils.hexDecode(user.getSalt());
        ShiroPrincipal subject = new ShiroPrincipal(user);
        subject.setAuthorized(true);
        return new SimpleAuthenticationInfo(subject, user.getPassword(), ByteSource.Util.bytes(salt), getName());
    }
}
