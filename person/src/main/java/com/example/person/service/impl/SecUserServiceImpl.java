package com.example.person.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.base.sys.entity.SecUser;
import com.example.person.mapper.SecUserMapper;
import com.example.person.service.SecUserService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SecUserServiceImpl extends ServiceImpl<SecUserMapper,SecUser> implements SecUserService{
    @Override
    public Map getPhotoByUserID(String user_id) {
        return baseMapper.getPhotoByUserID(user_id);
    }
}
