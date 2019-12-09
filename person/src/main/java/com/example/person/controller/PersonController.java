package com.example.person.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.base.controller.BaseController;
import com.example.base.service.SysUploadService;
import com.example.base.sys.config.HttpResultCodeEnum;
import com.example.base.sys.entity.BsPerson;
import com.example.base.sys.entity.SecUser;
import com.example.base.sys.entity.SysUploadfile;
import com.example.base.util.FastDfsUtil;
import com.example.base.util.SendMsg;
import com.example.person.service.BsPersonService;
import com.example.person.service.SecUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Wrapper;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@EnableEurekaClient
@RestController
@RequestMapping("/person")
public class PersonController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    public BsPersonService bsPersonService;
    @Autowired
    private SysUploadService sysUploadService;
    @Autowired
    private SecUserService secUserService;

//    测试从本项目的yml文件中获取参数
    @Value("${server.port}")
    String port;
//    测试从base项目中的yml文件中获取参数，如果是所有项目通用的配置可以放在base项目中，方便修改的时候改一处就可以
    @Value("${base}")
    String base;

    /**
     * 例子1
     * 调用mp的service的基本方法
     * @param
     * @return
     */
    @RequestMapping(value = "/getPersonDetail",method = RequestMethod.POST)
    public Object getPersonDetail(@RequestParam(value = "id") String id) {
        BsPerson bsPerson = bsPersonService.getById(id);
        return buildSuccessResult(JSONObject.toJSON(bsPerson));
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
     * @param current
     * @return
     */
    @RequestMapping(value = "/getAllPerson",method = RequestMethod.POST)
    public Object getAllPerson(@RequestParam(value = "current", defaultValue = "1") int current,
                                @RequestParam(value = "size", defaultValue = "10") int size,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "tid") String tid,
                                @RequestParam(value = "orgId") String orgId) {
        Page<BsPerson> bsPersonPage = new Page<BsPerson>(current,size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("user_name",name);
        queryWrapper.eq("tid",tid);
        queryWrapper.eq("org_id",orgId);
        bsPersonPage = (Page<BsPerson>) bsPersonService.page(bsPersonPage,queryWrapper);
        return buildSuccessResult(bsPersonPage);
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

    /**
     * 例子9
     *发短信
     * @param
     * @return
     */
    @RequestMapping("/sendMsg")
    public String sendMsg(@RequestParam(value = "mobile") String mobile,@RequestParam(value = "content") String content) {
        SendMsg.send("15201671951","ceshi");
        return buildSuccessResult("发送短信成功").toString();
    }


    /**
     * 上传头像
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadFileToFast")
    public Object uoloadFileToFast(@RequestParam("file")MultipartFile multipartFile,@RequestParam("userId")String userId) throws IOException {
        if(multipartFile.isEmpty()){
            LOGGER.info("文件不存在");
        }
        SysUploadfile sysUploadfile = sysUploadService.uploadFile(multipartFile,userId);
        SecUser user = new SecUser();
        user.setId(userId);
        user.setFid(Integer.parseInt(sysUploadfile.getId()));
        secUserService.updateById(user);
        LOGGER.info("上传文件成功");
        return buildResult(HttpResultCodeEnum.SUCCESS.getValue(),sysUploadfile,"上传成功");
    }

    /**
     * 下载文件
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping("/downloadFileToFast")
    public ResponseEntity<byte[]>  downloadFileToFast(@RequestParam("fileName")String  fileName) throws IOException {
//        byte[] bytes = fastDfsUtil.downloadFile(fileName);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment;filename=" + fileName);
//        HttpStatus status = HttpStatus.OK;
//        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, status);
        ResponseEntity<byte[]> responseEntity = null;
        return responseEntity;
    }

    /**
     *根据userID获取头像
     * @param
     * @return
     */
    @RequestMapping(value = "/getPhotoByUserID",method = RequestMethod.POST)
    public Object getPhotoByUserID(@RequestParam(value = "userId") String userId) {
        Map map = secUserService.getPhotoByUserID(userId);
        return buildResult(HttpResultCodeEnum.SUCCESS.getValue(),JSONObject.toJSON(map),"");
    }
}
