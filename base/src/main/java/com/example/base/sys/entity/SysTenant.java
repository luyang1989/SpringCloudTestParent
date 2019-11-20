package com.example.base.sys.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysTenant extends Model<SysTenant> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tid;
    private String tname;
    private String create_time;
    private String status;
}
