package com.example.person.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.base.controller.BaseController;
import com.example.base.sys.entity.SysTenant;
import com.example.person.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 租户controller
 */
@EnableEurekaClient
@RestController
@RequestMapping("/tenant")
public class TenantController extends BaseController {

    @Autowired
    private TenantService tenantService;

    /**
     * 获取租户列表
     * @return
     */
    @RequestMapping(value = "/getTidList",method = RequestMethod.POST)
    public Object getTidList(@RequestParam(value = "current", defaultValue = "1") int current,
                             @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<SysTenant> sysTenantPage = new Page<SysTenant>(current,size);
        sysTenantPage = (Page<SysTenant>) tenantService.page(sysTenantPage);
        return buildSuccessResult(sysTenantPage);
    }
}
