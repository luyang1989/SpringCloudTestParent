package com.example.zuul.controller;

import com.example.base.sys.config.HttpResultCodeEnum;
import com.example.base.sys.config.NumConstant;
import com.example.zuul.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class LoginController extends BaseController{


    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password) {

        int i = loginService.doLogin(username,password);
        if(i== NumConstant.THREE){
            return buildSuccessResult("登录成功");
        }else{
            return buildResult(HttpResultCodeEnum.ERROR.getValue(),"登录失败","登录失败");
        }
    }


}
