package com.example.entry.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.base.controller.BaseController;
import com.example.base.sys.entity.BsPerson;
import com.example.base.sys.entity.TaDimHandover;
import com.example.entry.service.ITaDimHandoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 精简离职交接设置表 前端控制器
 * </p>
 *
 * @author luyang
 * @since 2019-12-09
 */
@RestController
@RequestMapping("/taDimHandover")
public class TaDimHandoverController extends BaseController{

    @Autowired
    ITaDimHandoverService taDimHandoverService;

    @RequestMapping(value = "/getAllHandover",method = RequestMethod.POST)
    public Object getAllHandover(@RequestParam(value = "current", defaultValue = "1") int current,
                               @RequestParam(value = "size", defaultValue = "10") int size,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "tid") String tid) {
        Page<TaDimHandover> bsPersonPage = new Page<TaDimHandover>(current,size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("ho_conter",name);
        queryWrapper.eq("tid",tid);
        bsPersonPage = (Page<TaDimHandover>) taDimHandoverService.page(bsPersonPage,queryWrapper);
        return buildSuccessResult(bsPersonPage);
    }
}
