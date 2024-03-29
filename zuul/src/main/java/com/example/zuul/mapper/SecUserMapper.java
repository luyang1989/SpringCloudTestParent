package com.example.zuul.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.base.sys.entity.SecUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SecUserMapper extends BaseMapper<SecUser> {

    public SecUser findByName(@Param(Constants.WRAPPER) Wrapper wrapper);
}
