package com.example.zuul.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.base.sys.entity.SecUser;

import java.util.List;
import java.util.Map;

public interface LoginService extends IService<SecUser> {

    /**
     * 根据username查询用户
     * @return
     */
    public SecUser findByName(Wrapper wrapper);


}
