package com.newly.vas.business.role.bean;

import lombok.Data;

@Data
public class RoleBO {

    //角色id
    private String id;
    //角色名称
    private String name;
    //登录用户id
    private String createUserId;
    //备注
    private String remark;
    //功能权限
    private String menu;
    //资源权限
    private String resTree;
    //关联用户
    private String relatedUser;

    private String userCode;

    //网关权限
    private String gateWayTree;
}
