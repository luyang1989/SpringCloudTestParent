package com.example.person.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.base.sys.entity.SecUser;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SecUserMapper extends BaseMapper<SecUser> {

    /**
     * 通过userID获取用户头像
     * @param
     * @return
     */
    Map getPhotoByUserID(String user_id);
}
