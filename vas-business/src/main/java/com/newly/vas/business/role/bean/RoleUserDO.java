package com.newly.vas.business.role.bean;

import lombok.Data;

@Data
public class RoleUserDO {

    public RoleUserDO(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    private String userId;
    private String roleId;

}
