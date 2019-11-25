package com.example.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.base.mapper.SysUploadMapper;
import com.example.base.service.SysUploadService;
import com.example.base.sys.entity.BsPerson;
import com.example.base.sys.entity.SysUploadfile;
import com.example.base.util.DateUtils;
import com.example.base.util.FastDfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author lu.yang
 */
@Service
public class SysUploadServiceImpl extends ServiceImpl<SysUploadMapper,SysUploadfile> implements SysUploadService{
    @Autowired
    private FastDfsUtil fastDfsUtil;

    @Override
    public SysUploadfile uploadFile(MultipartFile file,String userId) {
        SysUploadfile sysUploadfile = new SysUploadfile();
        try {
            String path = fastDfsUtil.uploadFile(file);
            sysUploadfile.setFilename(file.getOriginalFilename());
            sysUploadfile.setFilepath(path);
            sysUploadfile.setCreatets(DateUtils.getCurrentTime());
            sysUploadfile.setCreateuser(Integer.parseInt(userId));
            baseMapper.insert(sysUploadfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sysUploadfile;
    }
}
