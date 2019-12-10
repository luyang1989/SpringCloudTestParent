package com.example.base.sys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 精简离职交接设置表
 * </p>
 *
 * @author luyang
 * @since 2019-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TaDimHandover implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tid;

    private String hoType;

    private String hoCode;

    private String hoConter;

    private String useFlg;

    private String delFlg;

    private String creadeId;

    private String creadeDate;

    private String updateId;

    private String updateDate;

    private Integer hoNum;


}
