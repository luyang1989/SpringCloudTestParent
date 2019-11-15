package com.example.zuul.controller;

import com.example.base.sys.config.HttpResultCodeEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class LoginController extends BaseController{


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password) {

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return buildResult(HttpResultCodeEnum.ERROR.getValue(),"未知账户","未知账户");
        } catch (IncorrectCredentialsException ice) {
            return buildResult(HttpResultCodeEnum.ERROR.getValue(),"密码不正确","密码不正确");
        } catch (LockedAccountException lae) {
            return buildResult(HttpResultCodeEnum.ERROR.getValue(),"账户已锁定","账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            return buildResult(HttpResultCodeEnum.ERROR.getValue(),"用户名或密码错误次数过多","用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            return buildResult(HttpResultCodeEnum.ERROR.getValue(),"用户名或密码不正确！","用户名或密码不正确！");
        }
        if (subject.isAuthenticated()) {
            String sid = (String) subject.getSession().getId();
            return buildLoginSuccessResult(sid,"登录成功");
        } else {
            token.clear();
            return buildResult(HttpResultCodeEnum.ERROR.getValue(),"登录失败","登录失败");
        }
    }


}
