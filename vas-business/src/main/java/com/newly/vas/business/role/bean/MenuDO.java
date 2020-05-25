package com.newly.vas.business.role.bean;

import com.newly.common.bean.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * menuDO
 */
@Data
public class MenuDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = -1;

    private String id;
    private String name;
    private String menuCode;
    private String pathName;
    private String icon;
    private String appName;
    private String appCode;
    private String type;
    private String parentId;
}
