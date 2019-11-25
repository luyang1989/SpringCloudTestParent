package com.example.person.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.base.sys.entity.BsPerson;


import java.util.List;
import java.util.Map;

/**
 * 使用mp的通用service接口
 */
public interface BsPersonService extends IService<BsPerson>{

    /**
     * 使用自定义的sql
     * @return
     */
    public List<BsPerson> findByName(Wrapper wrapper);
    /**
     * 使用自定义的sql(mybaties原生方式)
     * @return
     */
    public List<BsPerson> findByEnNameMybaties(BsPerson bsPerson);

    /**
     * 自定义sql分页mp版本
     * @param page
     * @param wrapper
     * @return
     */
    public IPage<BsPerson> selectPersonPage(Page<BsPerson> page, Wrapper wrapper);
    /**
     * 自定义sql分页mybaties版本
     * @param page
     * @param wrapper
     * @return
     */
    public IPage<BsPerson> selectPageVoMybaties(Page<BsPerson> page, BsPerson bsPerson);

    /**
     * mp的ar模式操作数据库
     * @param bsPerson
     * @return
     */
    public BsPerson getPersonById(BsPerson bsPerson);



}
