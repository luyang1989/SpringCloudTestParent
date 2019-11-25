package com.example.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.base.sys.entity.SysUploadfile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface SysUploadService extends IService<SysUploadfile> {

    public SysUploadfile uploadFile(MultipartFile file,String userId);
}
