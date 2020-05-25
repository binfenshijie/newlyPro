package com.newly.vas.business.role.bean;

import com.newly.common.bean.BaseDO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * appDO
 */
@Data
public class AppDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = -1;

    private String id;
    private String appCode;
    private String appName;
    private String appLogoBase64;
    private Date createTime;
    private Date updateTime;

}
