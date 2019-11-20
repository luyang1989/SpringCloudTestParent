package com.example.person.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.base.sys.entity.SysTenant;
import com.example.person.mapper.TenantMapper;
import com.example.person.service.TenantService;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper,SysTenant> implements TenantService{
}
