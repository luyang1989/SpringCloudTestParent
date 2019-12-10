package com.example.base.sys.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
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
public class BsOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String orgName;

    /**
     * 录入，客户录入
     */
    private String orgCode;

    /**
     * 系统生成
     */
    private String orgInnercode;

    /**
     * 租户的id
     */
    private String tid;

    /**
     * 父节点id
     */
    private Integer parentId;

    /**
     * 参照 公司还是部门 org@type
     */
    private Integer orgType;

    private String briefintro;

    /**
     * 预留 参照
     */
    private Integer orgLevel;

    private String beginDate;

    private String endDate;

    private String remark;

    private Integer leader;

    private Integer hrbp;

    /**
     * 停用，启用
     */
    private String status;

    private Integer orgOrder;

    private String createTime;

    private Integer createUser;

    private String begindate;


}
