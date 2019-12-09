package com.example.person.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.base.controller.BaseController;
import com.example.base.sys.entity.BsOrg;
import com.example.base.sys.entity.BsPerson;
import com.example.person.service.OrgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableEurekaClient
@RestController
@RequestMapping("/org")
public class OrgController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrgController.class);

    @Autowired
    private OrgService orgService;

    /**
     * 获取组织
     * @param tid
     * @return
     */
    @RequestMapping(value = "/getOrg",method = RequestMethod.POST)
    public Object getOrg(@RequestParam(value = "tid") String tid,@RequestParam(value = "parentId",defaultValue = "0") String parentId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tid",tid);
        queryWrapper.eq("parent_id",parentId);
        List<BsOrg> list = orgService.list(queryWrapper);
        return buildSuccessResult(JSONObject.toJSON(list));
    }
}
