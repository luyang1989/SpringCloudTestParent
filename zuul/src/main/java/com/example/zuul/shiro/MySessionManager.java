package com.example.zuul.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class MySessionManager extends DefaultWebSessionManager{
    

    @Override
    public Serializable getSessionId(ServletRequest servletRequest, ServletResponse servletResponse) {
        String token = WebUtils.toHttp(servletRequest).getHeader("token");
        if(StringUtils.isEmpty(token)){
            return super.getSessionId(servletRequest,servletResponse);
        }else{
            servletRequest.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
            servletRequest.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            servletRequest.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        }
    }
}
