package com.example.person.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.base.sys.entity.BsOrg;
import com.example.person.mapper.BsOrgMapper;
import com.example.person.service.OrgService;
import org.springframework.stereotype.Service;

@Service
public class OrgServiceImpl extends ServiceImpl<BsOrgMapper,BsOrg> implements OrgService{
}
