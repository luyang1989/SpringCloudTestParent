package com.example.person.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.base.sys.entity.BsPerson;
import com.example.person.mapper.BsPersonMapper;
import com.example.person.service.BsPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@EnableEurekaClient
@RestController
public class PersonController {

    @Autowired
    public BsPersonService bsPersonService;

//    测试从本项目的yml文件中获取参数
    @Value("${server.port}")
    String port;
//    测试从base项目中的yml文件中获取参数，如果是所有项目通用的配置可以放在base项目中，方便修改的时候改一处就可以
    @Value("${base}")
    String base;

    /**
     * 例子1
     * 调用mp的service的基本方法
     * @param name
     * @return
     */
    @RequestMapping("/test1")
    public String test1(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        BsPerson bsPerson = bsPersonService.getById(17656);
        return bsPerson.toString();
    }
    /**
     * 例子2
     * 调用mp的service的自定义sql
     * @param name
     * @return
     */
    @RequestMapping("/test2")
    public String test2(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name","王飙");
        List<BsPerson> bsPerson = bsPersonService.findByName(queryWrapper);
        return bsPerson.toString();
    }

    /**
     * 例子3
     * 使用自定义的sql(mybaties原生方式)
     * @param name
     * @return
     */
    @RequestMapping("/test3")
    public String test3(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        BsPerson bsPerson = new BsPerson();
        bsPerson.setEnName("Wang Biao");
        return bsPersonService.findByEnNameMybaties(bsPerson).toString();
    }
    /**
     * 例子4
     * 使用mp分分页
     * @param name
     * @return
     */
    @RequestMapping("/test4")
    public String test4(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        Page<BsPerson> bsPersonPage = new Page<BsPerson>(1,2);
        return bsPersonService.page(bsPersonPage).getRecords().toString();
    }
    /**
     * 例子5
     * 使用mp分分页带查询条件
     * @param name
     * @return
     */
    @RequestMapping("/test5")
    public String test5(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.likeRight("user_name","王");
        Page<BsPerson> bsPersonPage = new Page<BsPerson>(1,2);
        return bsPersonService.page(bsPersonPage,queryWrapper).getRecords().toString();
    }

    /**
     * 例子6
     * 使用mybaties自定义分页带查询条件
     * @param name
     * @return
     */
    @RequestMapping("/test6")
    public String test6(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        BsPerson bsPerson = new BsPerson();
        bsPerson.setUserName("王飙");
        Page<BsPerson> bsPersonPage = new Page<BsPerson>(1,2);
        return bsPersonService.selectPageVoMybaties(bsPersonPage,bsPerson).getRecords().toString();
    }

    /**
     * 例子7
     *使用mp自定义分页带查询条件
     * @param name
     * @return
     */
    @RequestMapping("/test7")
    public String test7(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.likeRight("user_name","张");
        Page<BsPerson> bsPersonPage = new Page<BsPerson>(1,2);
        return bsPersonService.selectPersonPage(bsPersonPage,queryWrapper).getRecords().toString();
    }

    /**
     * 例子8
     *使用mp的ar模式
     * @param name
     * @return
     */
    @RequestMapping("/test8")
    public String test8(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        BsPerson bsPerson = new BsPerson();
        bsPerson.setId(17657);
        return bsPersonService.getPersonById(bsPerson).toString();
    }
}
