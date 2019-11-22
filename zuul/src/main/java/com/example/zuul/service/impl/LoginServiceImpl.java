package com.example.zuul.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.base.sys.entity.SecUser;
import com.example.zuul.mapper.SecUserMapper;
import com.example.zuul.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends ServiceImpl<SecUserMapper,SecUser> implements LoginService{
}
