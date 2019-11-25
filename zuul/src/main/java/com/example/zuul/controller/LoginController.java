package com.example.zuul.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.base.sys.config.HttpResultCodeEnum;
import com.example.base.sys.entity.SecUser;
import com.example.zuul.shiro.ShiroPrincipal;
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
            ShiroPrincipal s = (ShiroPrincipal) SecurityUtils.getSubject().getPrincipal();
            SecUser secUser = s.getUser();
            return buildLoginSuccessResult(sid, JSONObject.toJSON(secUser),"登录成功");
        } else {
            token.clear();
            return buildResult(HttpResultCodeEnum.ERROR.getValue(),"登录失败","登录失败");
        }
    }


    /**
     * 未登录
     * @return
     */
    @RequestMapping(value = "/unlisted", method = RequestMethod.GET)
    @ResponseBody
    public Object unlisted() {
        return buildResult(HttpResultCodeEnum.UNLISTED.getValue(),"未登录","未登录");
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            return buildLogoutResult("退出成功");
        }else{
            return buildResult(HttpResultCodeEnum.UNLISTED.getValue(),"未登录","未登录");
        }
    }


}
