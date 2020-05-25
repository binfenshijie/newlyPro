package com.newly.vas.business.userInfo.bean;

import com.newly.common.bean.BaseDO;
import lombok.Data;
import java.util.*;
import java.io.Serializable;

/**
 * UserInfoDO
 */
@Data
public class UserInfoDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = -1;

    private String id;
    private String groupId;
    private String deptId;
    private String createUserId;
    private String name;
    private String realName;
    private String wxName;
    private String nickName;
    private String password;
    private String phone;
    private String email;
    private String idCard;
    private String image;
    private String headerImg;
    private Integer type;
    private Integer loginTimes;
    private String lastLoginIp;
    private Date lastLoginTime;
    private Date createTime;
    private Date updateTime;
    private String remark;
    private String userCode;
    private String parentId;
    private String iszuhu;
}
