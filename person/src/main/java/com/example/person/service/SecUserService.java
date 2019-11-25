package com.example.person.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.base.sys.entity.SecUser;

import java.util.Map;

public interface SecUserService extends IService<SecUser> {
    public Map getPhotoByUserID(String user_id);
}
