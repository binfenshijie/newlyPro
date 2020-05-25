package com.newly.vas.business.role.bean;

import com.newly.common.bean.BaseDO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * roleDO
 */
@Data
public class RoleDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = -1;

    private String id;
    private String name;
    private String createUserId;
    private int isSystem;
    private Date createTime;
    private Date updateTime;
    private String remark;
    private String userCode;
}
