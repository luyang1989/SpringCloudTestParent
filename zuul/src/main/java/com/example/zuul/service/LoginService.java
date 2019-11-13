package com.example.zuul.service;

import com.example.base.sys.config.NumConstant;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
@Service
public class LoginService {

    /**
     * 验证登录service
     * @param user_name
     * @param pass_word
     * @return
     */
    public int doLogin(String user_name,String pass_word){
        if(!StringUtils.isNotBlank(user_name)){
            return NumConstant.ONE;
        }
        if(!StringUtils.isNotBlank(pass_word)){
            return NumConstant.TWO;
        }
        return NumConstant.THREE;
    }
}
