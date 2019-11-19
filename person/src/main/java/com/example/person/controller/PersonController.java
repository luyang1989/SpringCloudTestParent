package com.example.person.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.base.sys.config.HttpResultCodeEnum;
import com.example.base.sys.entity.BsPerson;
import com.example.base.util.FastDfsUtil;
import com.example.base.util.SendMsg;
import com.example.person.service.BsPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

@EnableEurekaClient
@RestController
public class PersonController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    public BsPersonService bsPersonService;
    @Autowired
    private FastDfsUtil fastDfsUtil;

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
     * @param current
     * @return
     */
    @RequestMapping("/getAllPerson")
    public Object getAllPerson(@RequestParam(value = "current", defaultValue = "1") int current,
                        @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<BsPerson> bsPersonPage = new Page<BsPerson>(current,size);
        return buildSuccessResult(bsPersonService.page(bsPersonPage).getRecords().toString());
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
     * 上传文件
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadFileToFast")
    public String uoloadFileToFast(@RequestParam("file")MultipartFile file) throws IOException {
        if(file.isEmpty()){
            LOGGER.info("文件不存在");
        }
        String path = fastDfsUtil.uploadFile(file);
        LOGGER.info("上传文件成功");
        return buildResult(HttpResultCodeEnum.SUCCESS.getValue(),path,"上传成功").toString();
    }

    /**
     * 下载文件
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping("/downloadFileToFast")
    public ResponseEntity<byte[]>  downloadFileToFast(@RequestParam("fileName")String  fileName) throws IOException {
        byte[] bytes = fastDfsUtil.downloadFile(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + fileName);
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, status);
        return responseEntity;
    }
}
