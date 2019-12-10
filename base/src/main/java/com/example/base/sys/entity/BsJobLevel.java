package com.example.base.sys.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hyf
 * @since 2019-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BsJobLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 职级编码
     */
    private String jobLevelCod;

    /**
     * 职级名称
     */
    private String jobLevelName;

    /**
     * 租户ID
     */
    private String tid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createUser;


}
