package com.newly.vas.business.role.bean;

import lombok.Data;

@Data
public class RoleMenuDO {

    public RoleMenuDO() {
    }

    public RoleMenuDO(String roleId, String appCode, String menuId) {
        this.roleId = roleId;
        this.appCode = appCode;
        this.menuId = menuId;
    }

    private String roleId;
    private String appCode;
    private String menuId;

}
