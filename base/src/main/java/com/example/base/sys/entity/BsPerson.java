package com.example.base.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BsPerson extends Model<BsPerson> implements Serializable{


    private int id;

    private static final long serialVersionUID = 1L;

    private String begindate;

    private String enddate;

    private String begintime;

    private String userName;

    private String enName;

    /**
     * 男，女，其他，在字典表定义，在字典列表：user@gender
     */
    private Integer userGender;

    /**
     * 在sys_file_info表中记录
     */
    private Integer photoId;

    private String userBirthday;

    private Integer nationality;

    /**
     * 单独的籍贯表定义
     */
    private String nativePlace;

    /**
     * 在字典表定义，字典列表：user@nation
     */
    private Integer userNation;

    /**
     * 在字典表定义，字典列表：user@health
     */
    private Integer userHealth;

    /**
     * 在字典表定义，字典列表：user@marriage
     */
    private Integer userMarriage;

    /**
     * 在字典表定义，字典列表：user@polity
     */
    private Integer userPolity;

    /**
     * 在字典表定义，字典列表：user@education
     */
    private Integer userEducation;

    private Integer idType;

    private String idCard;

    private String mobile;

    private String address;

    private String homeAddr;

    private String email;

    private String zipcode;

    private String telephone;

    private String emergName;

    private String emergPhone;

    private String workCode;

    private String workDuty;

    private String workStationseq;

    private String workDutylevel;

    private String startDate;

    private Integer orgId;

    /**
     * 成本中心
     */
    private String costcenter;

    /**
     * 所属公司
     */
    private Integer corpId;

    private String tid;

    private String workStation;

    private Integer workType;

    private Integer workStatus;

    /**
     * 0标准工时，1特殊工时
     */
    private Integer workHours;

    private Integer leaderId;

    /**
     * 组长id
     */
    private Integer grouperId;

    /**
     * D删除，U使用
     */
    private String delFlag;

    /**
     * 当前登录人的用户id号，非员工id
     */
    private Integer createUser;

    private String createTime;

    /**
     * 当前登录人的用户id号，非员工id
     */
    private Integer modifyUser;

    private String modifyTime;

    private String remark;

    private String adLinked;

    @TableField("contractType")
    private String contractType;

    @TableField("workInfo_change_time")
    private String workinfoChangeTime;

    private String def01;

    private String def02;

    private String def03;

    private String def04;

    private String def05;

    private String def06;

    private String def07;

    private String def08;

    private String def09;

    private String def10;

    private String def11;

    private String def12;

    private String def13;

    private String def14;

    private String def15;

    private String def16;

    private String def17;

    private String def18;

    private String def19;

    private String def20;

    private String def21;

    private String def22;

    private String def23;

    private String def24;

    private String def25;

    private String def26;

    private String def27;


}
