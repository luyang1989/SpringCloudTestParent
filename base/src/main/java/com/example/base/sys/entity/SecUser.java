package com.example.base.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author luyang
 * @since 2019-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SecUser extends Model<SecUser> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String address;

    private Integer age;

    private String email;

    private String enabled;

    private String fullname;

    private String password;

    @TableField("plainPassword")
    private String plainPassword;

    private String salt;

    private String sex;

    private Integer fid;

    private Integer type;

    private String username;

    private Integer org;

    private String userMobile;

    private String tid;

    private String remark;

    private Integer createUser;

    private String createTime;

    private String mNumber;

    /**
     * 密码状态是否有效Y有效，N或者NULL为无效
     */
    private String pwdvalid;

    private String defPwd;

    private Integer startMenuId;

    private String pwdupdatetime;

    /**
     * 员工编号
     */
    private Integer personId;

    /**
     * 是否启用授权部门 U:启用 , D:禁用
     */
    private String ifUserOrgOn;


}
