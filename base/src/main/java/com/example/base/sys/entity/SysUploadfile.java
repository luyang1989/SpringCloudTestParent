package com.example.base.sys.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author luyang
 * @since 2019-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUploadfile implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private String id;

    /**
     * 显示文件名
     */
    private String filename;

    /**
     * 保存到服务器的文件名
     */
    private String savename;

    /**
     * 务必是相对路径！
     */
    private String filepath;

    private Integer filelenth;

    private String createts;

    private Integer createuser;

    private LocalDateTime updatets;

    private Integer updateuser;


}
