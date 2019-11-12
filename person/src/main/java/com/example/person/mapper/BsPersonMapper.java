package com.example.person.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.base.sys.entity.BsPerson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BsPersonMapper extends BaseMapper<BsPerson>{

    /**
     * 自定义sql
     * @param wrapper
     * @return
     */
    List<BsPerson> findByName(@Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 自定义sql(mybaties形式)
     * @param
     * @return
     */
    List<BsPerson> findByEnNameMybaties(BsPerson bsPerson);


    /**自定义分页
     * <p>
     * 查询 : 根据state状态查询用户列表，分页显示
     * 注意!!: 如果入参是有多个,需要加注解指定参数名才能在xml中取值
     * </p>
     *
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @param state 状态
     * @return 分页对象
     */
    IPage<BsPerson> selectPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 自定义sql语句分页mybaties版本
     * @param page
     * @param bsPerson
     * @return
     * 注意：多参数时必须加@Param注解
     */
    IPage<BsPerson> selectPageVoMybaties(Page page,@Param("bsPerson") BsPerson bsPerson);
}
