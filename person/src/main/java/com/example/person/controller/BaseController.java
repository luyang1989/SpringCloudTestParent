package com.example.person.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.base.sys.config.HttpResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);


    /**
     * 返回结果
     * @param statusCode 说明在此枚举 HttpResultCodeEnum
     * @param result 结果集
     * @param msg 失败原因
     * @return
     */
    protected Object buildResult(int statusCode,Object result,String msg) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("statusCode", statusCode);
        map.put("result", result);
        map.put("msg", msg);
        log.info("返回参数:"+ JSONObject.toJSON(map));
        return map;
    }

    protected Object buildSuccessResult(Object result) {
        return buildResult(HttpResultCodeEnum.SUCCESS.getValue(),result,null);
    }
}
