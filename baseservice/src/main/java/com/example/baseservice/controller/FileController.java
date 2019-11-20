package com.example.baseservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private FastDfsUtil fastDfsUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/uploadFileToFast")
    public String uoloadFileToFast(@RequestParam("fileName")MultipartFile file) throws IOException {
        if(file.isEmpty()){
            LOGGER.info("文件不存在");
        }
        String path = fastDfsUtil.uploadFile(file);
        System.out.println(path);
        return "success";
    }

}
