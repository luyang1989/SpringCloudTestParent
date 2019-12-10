package com.example.person.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.base.sys.entity.BsJobLevel;
import com.example.person.service.IBsJobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@RestController
@RequestMapping("/position")
public class PositionController extends BaseController {

    @Autowired
    private IBsJobLevelService jobLevelService;

    /**
     * 获取租户列表
     * @return
     */
    @RequestMapping(value = "/getPositionList",method = RequestMethod.POST)
    public Object getTidList(@RequestParam(value = "current", defaultValue = "1") int current,
                             @RequestParam(value = "size", defaultValue = "10") int size,
                             @RequestParam(value = "jobLevelName") String jobLevelName,
                             @RequestParam(value = "tid") String tid) {
        Page<BsJobLevel> jobLevelPage = new Page<BsJobLevel>(current,size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("job_level_name",jobLevelName.trim());
        queryWrapper.eq("tid",tid);
        jobLevelPage = (Page<BsJobLevel>) jobLevelService.page(jobLevelPage,queryWrapper);
        return buildSuccessResult(jobLevelPage);
    }
}
