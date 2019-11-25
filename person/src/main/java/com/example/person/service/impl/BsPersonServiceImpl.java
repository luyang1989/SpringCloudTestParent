package com.example.person.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.base.sys.entity.BsPerson;
import com.example.person.mapper.BsPersonMapper;
import com.example.person.service.BsPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 使用mp提供的通用service
 */
@Service
public class BsPersonServiceImpl extends ServiceImpl<BsPersonMapper,BsPerson> implements BsPersonService{

    /**
     * 自定义sql
     * @param wrapper
     * @return
     */
    @Override
    public List<BsPerson> findByName(Wrapper wrapper) {
        return baseMapper.findByName(wrapper);
    }
    /**
     * 使用自定义的sql(mybaties原生方式)
     * @return
     */
    @Override
    public List<BsPerson> findByEnNameMybaties(BsPerson bsPerson) {
        return baseMapper.findByEnNameMybaties(bsPerson);
    }

    /**
     * 自定义sql分页mp版本
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public IPage<BsPerson> selectPersonPage(Page<BsPerson> page, Wrapper wrapper) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        return baseMapper.selectPageVo(page, wrapper);
    }
    /**
     * 自定义sql分页mybaties版本
     * @param page
     * @param
     * @return
     */
    @Override
    public IPage<BsPerson> selectPageVoMybaties(Page<BsPerson> page, BsPerson bsPerson) {
        return baseMapper.selectPageVoMybaties(page,bsPerson);
    }
    /**
     * mp的ar模式操作数据库
     * @param bsPerson
     * @return
     */
    @Override
    public BsPerson getPersonById(BsPerson bsPerson) {
        return bsPerson.selectById();
    }


}
